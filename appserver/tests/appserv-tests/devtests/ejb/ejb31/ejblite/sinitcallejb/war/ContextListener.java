/*
 * Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.acme;

import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;

import java.lang.reflect.Method;

import javax.ejb.EJB;

@WebListener
public class ContextListener implements ServletContextListener {

    @EJB private SimpleStateless simpleStateless;

    public void contextInitialized(ServletContextEvent sce) {

	System.out.println("In ContextListener::contextInitialized");

	simpleStateless.hello();

    }

    @PostConstruct
    public void pc() {
	System.out.println("In ContextListener::postConstruct");
    }

    public void contextDestroyed(ServletContextEvent sce) {

	System.out.println("In ContextListener::contextDestroyed");

    }

}
