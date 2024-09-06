/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package todoapp.integtests.tests;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.EventObject;
import java.util.List;

import javax.activation.MimeType;
import javax.inject.Inject;

import com.google.common.collect.Iterables;
import com.google.common.eventbus.Subscribe;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.isis.applib.NonRecoverableException;
import org.apache.isis.applib.RecoverableException;
import org.apache.isis.applib.clock.Clock;
import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.eventbus.CollectionDomainEvent;
import org.apache.isis.applib.services.eventbus.EventBusService;
import org.apache.isis.applib.services.eventbus.PropertyDomainEvent;
import org.apache.isis.applib.value.Blob;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.CoreMatchers.containsString;
import todoapp.app.services.demoeventsubscriber.DemoBehaviour;
import todoapp.app.services.demoeventsubscriber.DemoDomainEventSubscriptions;
import todoapp.dom.todoitem.ToDoItem;
import todoapp.dom.todoitem.ToDoItems;
import todoapp.fixture.scenarios.RecreateToDoItemsForCurrentUser;

public class ToDoItemIntegTest extends AbstractToDoIntegTest {

    @Inject
    ToDoItems toDoItems;
    @Inject
    DemoDomainEventSubscriptions toDoItemSubscriptions;

    RecreateToDoItemsForCurrentUser fixtureScript;
    ToDoItem toDoItem;

    @Before
    public void setUp() throws Exception {
        fixtureScript = new RecreateToDoItemsForCurrentUser();
        fixtureScripts.runFixtureScript(fixtureScript, null);

        toDoItemSubscriptions.reset();
        final List<ToDoItem> all = toDoItems.notYetComplete();
        toDoItem = all.get(0);
    }

    public static class Title extends ToDoItemIntegTest {


        @Override
        @Before
        public void setUp() throws Exception {
            super.setUp();

            final List<ToDoItem> notYetComplete = fixtureScript.getNotYetComplete();
            final Iterable<ToDoItem> iter = Iterables.filter(notYetComplete, ToDoItem.Predicates.thoseWithDueByDate());
            ToDoItem toDoItem = wrap(iter.iterator().next());
            assertThat(toDoItem).isNotNull();

            nextTransaction();
        }


        @Test
        public void includesDescription() throws Exception {

            // given
            final String description = wrap(toDoItem).getDescription();
            assertThat(titleService.titleOf(toDoItem)).contains(description);

            // when
            toDoItem.setDescription("Foobar");

            // then
            then(titleService.titleOf(toDoItem)).contains("Foobar");
        }

        @Test
        public void includesDueDateIfAny() throws Exception {

            // given
            final LocalDate dueBy = wrap(toDoItem).getDueBy();
            assertThat(titleService.titleOf(toDoItem)).contains("due by " + dueBy.toString("yyyy-MM-dd"));

            // when
            final LocalDate fiveDaysFromNow = Clock.getTimeAsLocalDate().plusDays(5);
            toDoItem.setDueBy(fiveDaysFromNow);

            // then
            then(titleService.titleOf(toDoItem)).contains("due by " + fiveDaysFromNow.toString("yyyy-MM-dd"));
        }


        @Test
        public void ignoresDueDateIfNone() throws Exception {

            // when
            // (since wrapped, will call clearDueBy)
            wrap(toDoItem).setDueBy(null);

            // then
            then(titleService.titleOf(toDoItem)).doesNotContain("due by");
        }

        @Test
        public void usesWhetherCompleted() throws Exception {

            // given
            assertThat(titleService.titleOf(toDoItem)).doesNotContain("Completed!");

            // when
            wrap(toDoItem).completed();

            // then
            then(titleService.titleOf(toDoItem)).doesNotContain("due by")
                                             .contains("Completed!");
        }
    }

    public static class Actions {

        public static class Completed extends ToDoItemIntegTest {

            @Inject
            private EventBusService eventBusService;

            @Test
            public void happyCase() throws Exception {

                // given
                assertThat(wrap(toDoItem).isComplete()).isFalse();

                // when
                wrap(toDoItem).completed();

                // then
                assertThat(wrap(toDoItem).isComplete()).isTrue();
            }

            @Test
            public void cannotCompleteIfAlreadyCompleted() throws Exception {

                // given
                toDoItem.setComplete(true);

                // expect
                expectedExceptions.expectMessage("Already completed");

                // when
                wrap(toDoItem).completed();

                // and then
                final EventObject ev = toDoItemSubscriptions.mostRecentlyReceivedEvent(EventObject.class);
                then(ev).isNull();
            }



            @Test
            public void subscriberReceivesEvents() throws Exception {

                // given
                final ToDoItem.CompletedDomainEvent[] evHolder = new ToDoItem.CompletedDomainEvent[1];
                eventBusService.register(new Object() {
                    @Subscribe
                    public void on(final ToDoItem.CompletedDomainEvent ev) {
                        evHolder[0] = ev;
                    }
                });

                // when
                wrap(toDoItem).completed();

                // then
                then(evHolder[0].getSource()).isEqualTo(toDoItem);
                then(evHolder[0].getIdentifier().getMemberName()).isEqualTo("completed");
            }

            @Test
            public void subscriberVetoesEventWithRecoverableException() throws Exception {

                // given
                toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_RECOVERABLE_EXCEPTION);

                // then
                expectedExceptions.expect(RecoverableException.class);
                expectedExceptions.expectMessage("Rejecting event (recoverable exception thrown)");

                // when
                wrap(toDoItem).completed();
            }

            @Test
            public void subscriberVetoesEventWithNonRecoverableException() throws Exception {

                // given
                toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_NON_RECOVERABLE_EXCEPTION);

                // then
                expectedExceptions.expect(NonRecoverableException.class);
                expectedExceptions.expectMessage("Rejecting event (non-recoverable exception thrown)");

                // when
                wrap(toDoItem).completed();
            }

            @Test
            public void subscriberVetoesEventWithAnyOtherException() throws Exception {

                // given
                toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_OTHER_EXCEPTION);

                // then
                expectedExceptions.expect(RuntimeException.class);
                expectedExceptions.expectMessage("Throwing some other exception");

                // when
                wrap(toDoItem).completed();
            }

        }


        public static class Duplicate extends ToDoItemIntegTest {

            ToDoItem duplicateToDoItem;

            @Inject
            private ClockService clockService;

            @Test
            public void happyCase() throws Exception {

                // given
                final LocalDate todaysDate = clockService.now();
                wrap(toDoItem).setDueBy(todaysDate);
                wrap(toDoItem).updateCost(new BigDecimal("123.45"));

                duplicateToDoItem = wrap(toDoItem).duplicate(
                        toDoItem.default0Duplicate(),
                        toDoItem.default1Duplicate(),
                        toDoItem.default2Duplicate(),
                        toDoItem.default3Duplicate(),
                        new BigDecimal("987.65"));

                // then
                assertThat(duplicateToDoItem.getDescription()).isEqualTo(wrap(toDoItem).getDescription() + " - Copy");
                assertThat(duplicateToDoItem.getCategory()).isEqualTo(wrap(toDoItem).getCategory());
                assertThat(duplicateToDoItem.getDueBy()).isEqualTo(todaysDate);
                assertThat(duplicateToDoItem.getCost()).isEqualTo(new BigDecimal("987.65"));
            }
        }

        public static class NotYetCompleted extends ToDoItemIntegTest {

            @Test
            public void happyCase() throws Exception {

                // given
                toDoItem.setComplete(true);

                // when
                wrap(toDoItem).notYetCompleted();

                // then
                assertThat(wrap(toDoItem).isComplete()).isFalse();
            }

            @Test
            public void cannotUndoIfNotYetCompleted() throws Exception {

                // given
                assertThat(wrap(toDoItem).isComplete()).isFalse();

                // when, then should fail
                expectedExceptions.expectMessage("Not yet completed");
                wrap(toDoItem).notYetCompleted();
            }

            @Test
            public void subscriberReceivesEvent() throws Exception {

                // given
                assertThat(toDoItemSubscriptions.getSubscriberBehaviour()).isEqualTo(DemoBehaviour.ANY_EXECUTE_ACCEPT);
                toDoItem.setComplete(true);

                // when
                wrap(toDoItem).notYetCompleted();

                // then
                assertThat(toDoItem.isComplete()).isFalse();

                // and then
                final ActionDomainEvent<ToDoItem> ev = toDoItemSubscriptions.mostRecentlyReceivedEvent(ActionDomainEvent.class);
                assertThat(ev).isNotNull();

                final ToDoItem source = ev.getSource();
                assertThat(source).isEqualTo(toDoItem);
                assertThat(ev.getIdentifier().getMemberName()).isEqualTo("notYetCompleted");
            }
        }
    }

    public static class Collections {

        public static class Dependencies {
            public static class Add extends ToDoItemIntegTest {

                private ToDoItem otherToDoItem;

                @Override
                @Before
                public void setUp() throws Exception {
                    super.setUp();
                    final List<ToDoItem> items = wrap(toDoItems).notYetComplete();
                    otherToDoItem = wrap(items.get(1));
                }

                @After
                public void tearDown() throws Exception {
                    toDoItem.getDependencies().clear();
                }

                @Test
                public void happyCase() throws Exception {

                    // given
                    assertThat(wrap(toDoItem).getDependencies()).hasSize(0);

                    // when
                    wrap(toDoItem).add(otherToDoItem);

                    // then
                    assertThat(wrap(toDoItem).getDependencies()).hasSize(1);
                    assertThat(wrap(toDoItem).getDependencies().iterator().next()).isEqualTo(unwrap(otherToDoItem));
                }


                @Test
                public void cannotDependOnSelf() throws Exception {

                    // then
                    expectedExceptions.expectMessage("Can't set up a dependency to self");

                    // when
                    wrap(toDoItem).add(wrap(toDoItem));
                }

                @Test
                public void cannotAddIfComplete() throws Exception {

                    // given
                    toDoItem.setComplete(true);

                    // then
                    expectedExceptions.expectMessage("Cannot add dependencies for items that are complete");

                    // when
                    wrap(toDoItem).add(otherToDoItem);
                }


                @Test
                public void subscriberReceivesEvent() throws Exception {

                    // given
                    toDoItemSubscriptions.reset();

                    // when
                    wrap(toDoItem).add(otherToDoItem);

                    // then received events
                    @SuppressWarnings("unchecked")
                    final List<EventObject> receivedEvents = toDoItemSubscriptions.receivedEvents();

                    assertThat(receivedEvents).hasSize(8);
                    assertThat(receivedEvents.get(0)).isInstanceOf(ActionDomainEvent.class); // ToDoItem#add() executed
                    assertThat(receivedEvents.get(1)).isInstanceOf(CollectionDomainEvent.class); // ToDoItem#dependencies add, executed
                    assertThat(receivedEvents.get(2)).isInstanceOf(CollectionDomainEvent.class); // ToDoItem#dependencies add, executing
                    assertThat(receivedEvents.get(3)).isInstanceOf(ActionDomainEvent.class); // ToDoItem#add executing
                    assertThat(receivedEvents.get(4)).isInstanceOf(ToDoItem.DueByDomainEvent.class); // side-effect of title() when creating the action
                    assertThat(receivedEvents.get(5)).isInstanceOf(ActionDomainEvent.class); // ToDoItem#add validate
                    assertThat(receivedEvents.get(6)).isInstanceOf(ActionDomainEvent.class); // ToDoItem#add disable
                    assertThat(receivedEvents.get(7)).isInstanceOf(ActionDomainEvent.class); // ToDoItem#add hide

                    // inspect the collection interaction (posted programmatically in ToDoItem#add)
                    final CollectionDomainEvent<ToDoItem,ToDoItem> ciEv = toDoItemSubscriptions.mostRecentlyReceivedEvent(CollectionDomainEvent.class);
                    assertThat(ciEv).isNotNull();

                    assertThat(ciEv.getSource()).isEqualTo(toDoItem);
                    assertThat(ciEv.getIdentifier().getMemberName()).isEqualTo("dependencies");
                    assertThat(ciEv.getOf()).isEqualTo(CollectionDomainEvent.Of.ADD_TO);
                    assertThat(ciEv.getValue()).isEqualTo(unwrap(otherToDoItem));

                    // inspect the action interaction (posted declaratively by framework)
                    final ActionDomainEvent<ToDoItem> aiEv = toDoItemSubscriptions.mostRecentlyReceivedEvent(ActionDomainEvent.class);
                    assertThat(aiEv).isNotNull();

                    assertThat(aiEv.getSource()).isEqualTo(toDoItem);
                    assertThat(aiEv.getIdentifier().getMemberName()).isEqualTo("add");
                    assertThat(aiEv.getArguments()).containsExactly(unwrap((Object)otherToDoItem));
                    assertThat(aiEv.getCommand()).isNotNull();
                }

                @Test
                public void subscriberVetoesEventWithRecoverableException() throws Exception {

                    // given
                    toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_RECOVERABLE_EXCEPTION);

                    // then
                    expectedExceptions.expect(RecoverableException.class);

                    // when
                    wrap(toDoItem).add(otherToDoItem);
                }

                @Test
                public void subscriberVetoesEventWithNonRecoverableException() throws Exception {

                    // given
                    toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_NON_RECOVERABLE_EXCEPTION);

                    // then
                    expectedExceptions.expect(NonRecoverableException.class);

                    // when
                    wrap(toDoItem).add(otherToDoItem);
                }

                @Test
                public void subscriberVetoesEventWithAnyOtherException() throws Exception {

                    // given
                    toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_OTHER_EXCEPTION);

                    // then
                    expectedExceptions.expect(RuntimeException.class);

                    // when
                    wrap(toDoItem).add(otherToDoItem);
                }
            }
            public static class Remove extends ToDoItemIntegTest {

                private ToDoItem otherToDoItem;
                private ToDoItem yetAnotherToDoItem;

                @Override
                @Before
                public void setUp() throws Exception {
                    super.setUp();
                    final List<ToDoItem> items = wrap(toDoItems).notYetComplete();
                    otherToDoItem = wrap(items.get(1));
                    yetAnotherToDoItem = wrap(items.get(2));

                    wrap(toDoItem).add(otherToDoItem);
                }

                @After
                public void tearDown() throws Exception {
                    toDoItem.getDependencies().clear();
                }

                @Test
                public void happyCase() throws Exception {

                    // given
                    assertThat(wrap(toDoItem).getDependencies()).hasSize(1);

                    // when
                    wrap(toDoItem).remove(otherToDoItem);

                    // then
                    assertThat(wrap(toDoItem).getDependencies()).hasSize(0);
                }


                @Test
                public void cannotRemoveItemIfNotADependency() throws Exception {

                    // then
                    expectedExceptions.expectMessage("Not a dependency");

                    // when
                    wrap(toDoItem).remove(yetAnotherToDoItem);
                }

                @Test
                public void cannotRemoveDependencyIfComplete() throws Exception {

                    // given
                    toDoItem.setComplete(true);

                    // then
                    expectedExceptions.expectMessage("Cannot remove dependencies for items that are complete");

                    // when
                    wrap(toDoItem).remove(otherToDoItem);
                }

                @Test
                public void subscriberVetoesEventWithRecoverableException() throws Exception {

                    // given
                    toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_RECOVERABLE_EXCEPTION);

                    // then
                    expectedExceptions.expect(RecoverableException.class);

                    // when
                    wrap(toDoItem).remove(otherToDoItem);
                }

                @Test
                public void subscriberVetoesEventWithNonRecoverableException() throws Exception {

                    // given
                    toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_NON_RECOVERABLE_EXCEPTION);

                    // then
                    expectedExceptions.expect(NonRecoverableException.class);

                    // when
                    wrap(toDoItem).remove(otherToDoItem);
                }

                @Test
                public void subscriberVetoesEventWithAnyOtherException() throws Exception {

                    // given
                    toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_OTHER_EXCEPTION);

                    // then
                    expectedExceptions.expect(RuntimeException.class);

                    // when
                    wrap(toDoItem).remove(otherToDoItem);
                }
            }
        }

    }

    public static class Properties {

        public static class Attachment extends ToDoItemIntegTest {

            @Test
            public void happyCase() throws Exception {

                final byte[] bytes = "{\"foo\": \"bar\"}".getBytes(Charset.forName("UTF-8"));
                final Blob newAttachment = new Blob("myfile.json", new MimeType("application/json"), bytes);

                // when
                wrap(toDoItem).setAttachment(newAttachment);

                // then
                assertThat(wrap(toDoItem).getAttachment()).isEqualTo(newAttachment);
            }

            @Test
            public void canBeNull() throws Exception {

                // when
                wrap(toDoItem).setAttachment(null);

                // then
                assertThat(wrap(toDoItem).getAttachment()).isEqualTo((Blob)null);
            }
        }

        public static class Category extends ToDoItemIntegTest {

            @Test
            public void cannotModify() throws Exception {

                // when, then
                expectedExceptions.expectMessage(containsString("Reason: Use action to update both category and subcategory."));
                wrap(toDoItem).setCategory(todoapp.dom.categories.Category.PROFESSIONAL);
            }
        }

        public static class Cost extends ToDoItemIntegTest {

            private BigDecimal cost;

            @Override
            @Before
            public void setUp() throws Exception {
                super.setUp();
                cost = wrap(toDoItem).getCost();
            }

            @Test
            public void happyCaseUsingProperty() throws Exception {

                final BigDecimal newCost = new BigDecimal("123.45");

                // when
                final String reason = "";
                wrap(toDoItem).updateCost(newCost);

                // then
                assertThat(wrap(toDoItem).getCost()).isEqualTo(newCost);
            }

            @Test
            public void happyCaseUsingAction() throws Exception {

                final BigDecimal newCost = new BigDecimal("123.45");

                // when
                final String reason = "";
                wrap(toDoItem).updateCost(newCost);

                // then
                assertThat(wrap(toDoItem).getCost()).isEqualTo(newCost);
            }

            @Test
            public void canBeNull() throws Exception {

                // when
                final String reason = "";
                wrap(toDoItem).updateCost(null);

                // then
                assertThat(wrap(toDoItem).getCost()).isEqualTo((BigDecimal)null);
            }

            @Test
            public void defaultForAction() throws Exception {

                // then
                assertThat(toDoItem.default0UpdateCost()).isEqualTo(cost);
            }

        }

        public static class Description extends ToDoItemIntegTest {

            @Test
            public void happyCase() throws Exception {

                // given
                final String description = wrap(toDoItem).getDescription();

                // when
                wrap(toDoItem).setDescription(description + " foobar");

                // then
                assertThat(wrap(toDoItem).getDescription()).isEqualTo(description + " foobar");
            }


            @Test
            public void failsRegex() throws Exception {

                // when
                expectedExceptions.expectMessage("Doesn't match pattern");
                wrap(toDoItem).setDescription("exclamation marks are not allowed!!!");
            }

            @Test
            public void cannotBeNull() throws Exception {

                // when, then
                expectedExceptions.expectMessage("'Description' is mandatory");
                wrap(toDoItem).setDescription(null);
            }


            @Test
            public void onlyJustShortEnough() throws Exception {

                // when, then
                wrap(toDoItem).setDescription(characters(100));
            }

            @Test
            public void tooLong() throws Exception {

                // then
                expectedExceptions.expectMessage("The value proposed exceeds the maximum length of 100");

                // when
                wrap(toDoItem).setDescription(characters(101));
            }


            @Test
            public void subscriberReceivesEvent() throws Exception {

                // given
                assertThat(toDoItemSubscriptions.getSubscriberBehaviour()).isEqualTo(DemoBehaviour.ANY_EXECUTE_ACCEPT);
                final String description = wrap(toDoItem).getDescription();

                // when
                wrap(toDoItem).setDescription(description + " foobar");

                // then published and received
                @SuppressWarnings("unchecked")
                final PropertyDomainEvent<ToDoItem,String> ev = toDoItemSubscriptions.mostRecentlyReceivedEvent(PropertyDomainEvent.class);
                assertThat(ev).isNotNull();

                final ToDoItem source = ev.getSource();
                assertThat(source).isEqualTo(toDoItem);
                assertThat(ev.getIdentifier().getMemberName()).isEqualTo("description");
                assertThat(ev.getOldValue()).isEqualTo(description);
                assertThat(ev.getNewValue()).isEqualTo(description + " foobar");
            }

            @Test
            public void subscriberVetoesEventWithRecoverableException() throws Exception {

                // given
                toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_RECOVERABLE_EXCEPTION);

                // then
                expectedExceptions.expect(RecoverableException.class);

                // when
                wrap(toDoItem).setDescription("Buy bread and butter");
            }


            @Test
            public void subscriberVetoesEventWithNonRecoverableException() throws Exception {

                // given
                toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_NON_RECOVERABLE_EXCEPTION);

                // then
                expectedExceptions.expect(NonRecoverableException.class);

                // when
                wrap(toDoItem).setDescription("Buy bread and butter");
            }


            @Test
            public void subscriberVetoesEventWithAnyOtherException() throws Exception {

                // given
                toDoItemSubscriptions.subscriberBehaviour(DemoBehaviour.ANY_EXECUTE_VETO_WITH_OTHER_EXCEPTION);

                // then
                expectedExceptions.expect(RuntimeException.class);

                // when
                wrap(toDoItem).setDescription("Buy bread and butter");
            }


            private static String characters(final int n) {
                final StringBuffer buf = new StringBuffer();
                for(int i=0; i<n; i++) {
                    buf.append("a");
                }
                return buf.toString();
            }
        }

        public static class DueBy extends ToDoItemIntegTest {

            @Inject
            private ClockService clockService;

            @Test
            public void happyCase() throws Exception {

                // when
                final LocalDate fiveDaysFromNow = clockService.now().plusDays(5);
                wrap(toDoItem).setDueBy(fiveDaysFromNow);

                // then
                assertThat(wrap(toDoItem).getDueBy()).isEqualTo(fiveDaysFromNow);
            }


            @Test
            public void canBeNull() throws Exception {

                // when
                wrap(toDoItem).setDueBy(null);

                // then
                assertThat(wrap(toDoItem).getDueBy()).isEqualTo((LocalDate)null);
            }

            @Test
            public void canBeUpToSixDaysInPast() throws Exception {

                final LocalDate nowAsLocalDate = clockService.now();
                final LocalDate sixDaysAgo = nowAsLocalDate.plusDays(-5);

                // when
                wrap(toDoItem).setDueBy(sixDaysAgo);

                // then
                assertThat(wrap(toDoItem).getDueBy()).isEqualTo(sixDaysAgo);
            }


            @Test
            public void cannotBeMoreThanSixDaysInPast() throws Exception {

                final LocalDate sevenDaysAgo = Clock.getTimeAsLocalDate().plusDays(-7);

                // when, then
                expectedExceptions.expectMessage("Due by date cannot be more than one week old");
                wrap(toDoItem).setDueBy(sevenDaysAgo);
            }
        }

        public static class Notes extends ToDoItemIntegTest {

            @Test
            public void happyCase() throws Exception {

                final String newNotes = "Lorem ipsum yada yada";

                // when
                wrap(toDoItem).setNotes(newNotes);

                // then
                assertThat(wrap(toDoItem).getNotes()).isEqualTo(newNotes);
            }

            @Test
            public void canBeNull() throws Exception {

                // when
                wrap(toDoItem).setNotes(null);

                // then
                assertThat(wrap(toDoItem).getNotes()).isEqualTo((String)null);
            }

            @Test
            public void suscriberReceivedDefaultEvent() throws Exception {

                final String newNotes = "Lorem ipsum yada yada";

                // when
                wrap(toDoItem).setNotes(newNotes);

                // then
                assertThat(toDoItem.getNotes()).isEqualTo(newNotes);

                // and then receive the default event.
                @SuppressWarnings("unchecked")
                final PropertyDomainEvent.Default ev = toDoItemSubscriptions.mostRecentlyReceivedEvent(PropertyDomainEvent.Default.class);
                assertThat(ev).isNotNull();

                assertThat(ev.getSource()).isEqualTo((Object)toDoItem);
                assertThat(ev.getNewValue()).isEqualTo((Object)newNotes);
            }


        }

        public static class OwnedBy extends ToDoItemIntegTest {

            @Test
            public void cannotModify() throws Exception {

                // when, then
                expectedExceptions.expectMessage("Reason: Always disabled. Identifier: todoapp.dom.todoitem.ToDoItem#atPath()");
                wrap(toDoItem).setAtPath("other");
            }


        }

        public static class Subcategory extends ToDoItemIntegTest {

            @Test
            public void cannotModify() throws Exception {

                // when, then
                expectedExceptions.expectMessage(containsString("Reason: Use action to update both category and subcategory."));
                wrap(toDoItem).setSubcategory(todoapp.dom.categories.Subcategory.CHORES);
            }
        }

    }




}