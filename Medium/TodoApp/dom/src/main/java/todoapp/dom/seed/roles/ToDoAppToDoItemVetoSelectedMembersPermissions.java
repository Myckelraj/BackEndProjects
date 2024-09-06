/*
 *  Copyright 2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
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
package todoapp.dom.seed.roles;

import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

import todoapp.dom.relativepriority.RelativePriorityService;
import todoapp.dom.todoitem.ToDoItem;

public class ToDoAppToDoItemVetoSelectedMembersPermissions extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "todoapp-todoitem-veto-selected-members-role";

    public ToDoAppToDoItemVetoSelectedMembersPermissions() {
        super(ROLE_NAME, "Veto 'updateCost' and 'relativePriority' for ToDoItem");
    }

    @Override
    protected void execute(final ExecutionContext executionContext) {
        // regular members
        newMemberPermissions(
                ApplicationPermissionRule.VETO,
                ApplicationPermissionMode.VIEWING,
                ToDoItem.class,
                "updateCost");
        // contributed members
        newMemberPermissions(
                ApplicationPermissionRule.VETO,
                ApplicationPermissionMode.VIEWING,
                RelativePriorityService.class,
                "relativePriority", "next", "previous"
        );
    }

}
