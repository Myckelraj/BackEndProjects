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
package todoapp.dom.seed.users;

import org.isisaddons.module.security.dom.user.AccountType;
import org.isisaddons.module.security.seed.scripts.AbstractUserAndRolesFixtureScript;
import org.isisaddons.module.security.seed.scripts.IsisModuleSecurityAdminRoleAndPermissions;
import todoapp.dom.seed.roles.*;
import todoapp.dom.seed.tenancies.ToDoAppAdminUserTenancy;
import todoapp.dom.seed.tenancies.UsersTenancy;

import java.util.Arrays;

public class ToDoAppAdminUser extends AbstractUserAndRolesFixtureScript {

    public static final String USER_NAME = "todoapp-admin";
    public static final String TENANCY_PATH = UsersTenancy.TENANCY_PATH + USER_NAME;

    private static final String PASSWORD = "pass";

    public ToDoAppAdminUser() {
        super(USER_NAME, PASSWORD, null,
                ToDoAppAdminUserTenancy.TENANCY_PATH, AccountType.LOCAL,
                Arrays.asList(IsisModuleSecurityAdminRoleAndPermissions.ROLE_NAME,

                              AuditModuleRoleAndPermissions.ROLE_NAME,
                              CommandModuleRoleAndPermissions.ROLE_NAME,
                              PublishingModuleRoleAndPermissions.ROLE_NAME,
                              SessionLoggerModuleRoleAndPermissions.ROLE_NAME,
                              SettingsModuleRoleAndPermissions.ROLE_NAME,
                              TogglzModuleAdminRole.ROLE_NAME,

                              ToDoAppRegularRoleAndPermissions.ROLE_NAME,
                              ToDoAppFixtureServiceRoleAndPermissions.ROLE_NAME,

                              ApacheIsisTranslationServicePoMenuRoleAndPermissions.ROLE_NAME,
                              ApacheIsisApplibDtoRoleAndPermissions.ROLE_NAME,
                              ApacheIsisMetadataMenuRoleAndPermissions.ROLE_NAME,
                              ApacheIsisPersistableMixinsRoleAndPermissions.ROLE_NAME,
                              ApacheIsisApplibMixinsRoleAndPermissions.ROLE_NAME,

                              ApacheIsisViewerWicketMixinsRoleAndPermissions.ROLE_NAME
                        ));
    }


    @Override
    protected void execute(ExecutionContext executionContext) {
        super.execute(executionContext);
    }

}
