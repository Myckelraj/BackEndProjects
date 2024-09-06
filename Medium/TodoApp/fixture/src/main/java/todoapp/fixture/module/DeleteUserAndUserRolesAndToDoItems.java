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
package todoapp.fixture.module;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;

import todoapp.dom.seed.tenancies.UsersTenancy;
import todoapp.fixture.util.Util;

public class DeleteUserAndUserRolesAndToDoItems extends FixtureScript {

    //region > username
    private String username;

    /**
     * The user to delete; required.
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {

        // defaults
        final String username = Util.coalesce(ec.getParameter("username"), getUsername());
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }

        // validate user
        final ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null) {
            // nothing to do...
            return;
        }

        final String atPath = UsersTenancy.TENANCY_PATH + username;

        // delete
        isisJdoSupport.executeUpdate(
                "DELETE FROM \"todo\".\"ToDoItemDependencies\" WHERE \"dependingId\" IN (SELECT \"id\" FROM \"todo\".\"ToDoItem\" WHERE \"atPath\" = '" + atPath + "')");
        isisJdoSupport.executeUpdate(
                "DELETE FROM \"todo\".\"ToDoItem\" WHERE \"atPath\" = '" + atPath + "'");
        isisJdoSupport.executeUpdate(
                "DELETE FROM \"isissecurity\".\"ApplicationUserRoles\" WHERE \"userId\" IN (SELECT \"id\" FROM \"isissecurity\".\"ApplicationUser\" WHERE \"username\" = '" + username + "')");
        isisJdoSupport.executeUpdate(
                "DELETE FROM \"isissecurity\".\"ApplicationUser\" WHERE \"username\" = '" + username + "'");
        isisJdoSupport.executeUpdate(
                "DELETE FROM \"isissecurity\".\"ApplicationTenancy\" WHERE \"path\" = '" + UsersTenancy.TENANCY_PATH + username + "'");
    }

    @Inject
    private IsisJdoSupport isisJdoSupport;

    @javax.inject.Inject
    private ApplicationUserRepository applicationUserRepository;

}