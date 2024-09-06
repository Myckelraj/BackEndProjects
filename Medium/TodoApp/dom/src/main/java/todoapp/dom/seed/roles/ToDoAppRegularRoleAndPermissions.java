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

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

import todoapp.dom.ToDoAppDomainModule;

public class ToDoAppRegularRoleAndPermissions extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "todoapp-regular-role";

    public ToDoAppRegularRoleAndPermissions() {
        super(ROLE_NAME, "Read/write access to todoapp dom");
    }

    @Override
    protected void execute(final FixtureScript.ExecutionContext executionContext) {
        newPackagePermissions(
                ApplicationPermissionRule.ALLOW,
                ApplicationPermissionMode.CHANGING,
                "todoapp.app",
                ToDoAppDomainModule.class.getPackage().getName()
                );
    }

}
