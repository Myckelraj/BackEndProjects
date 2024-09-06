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

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import todoapp.dom.similarto.SimilarToService;
import todoapp.dom.todoitem.ToDoItem;
import todoapp.dom.todoitem.ToDoItems;
import todoapp.fixture.scenarios.RecreateToDoItemsForCurrentUser;

public abstract class SimilarToService_IntegTest extends AbstractToDoIntegTest {

    RecreateToDoItemsForCurrentUser fixtureScript;

    @Before
    public void setUpData() throws Exception {
        fixtureScript = new RecreateToDoItemsForCurrentUser();
        fixtureScripts.runFixtureScript(fixtureScript, null);
    }

    @Inject
    ToDoItems toDoItems;
    @Inject
    SimilarToService similarToService;

    ToDoItem toDoItem;

    @Before
    public void setUp() throws Exception {

        toDoItem = wrap(fixtureScript.getToDoItems().get(0));
        assertThat(toDoItem).isNotNull();
    }

    public static class Collections {

        public static class SimilarTo extends SimilarToService_IntegTest {

            @Test
            public void happyCase() throws Exception {
                // when
                List<ToDoItem> similarItems = similarToService.similarTo(toDoItem);

                // then
                assertThat(similarItems).hasSize(7);
            }
        }
    }

}