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

package com.sun.s1asdev.ejb.ejb30.persistence.eem_adapted;

import java.util.Map;
import java.util.HashMap;

import javax.naming.InitialContext;

import javax.ejb.Stateful;
import javax.ejb.EJB;
import javax.ejb.LocalHome;

import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityNotFoundException;

import javax.persistence.EntityManager;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.EJBException;

@Stateful
public class SfulBean
    implements Sful {

    private String name;
    
    @EJB private AdaptedLocalHome home;

    private SfulDelegate delegate;
    
    private @PersistenceContext(unitName="lib/ejb-ejb30-persistence-eem_adapted-par1.jar#em",
                type=PersistenceContextType.EXTENDED) 
            EntityManager extendedEM;
 
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Boolean> doTests() {
        Person person = new Person(name);
        
        String delegateName = "delgname_" + name;
        String delegateData= "delgdata: " + name;
        try {
            delegate = (SfulDelegate) home.createDelegate(delegateName, delegateData);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        delegate.createPerson(delegateName, delegateData);
        Person dPerson = extendedEM.find(Person.class, delegateName);

        extendedEM.persist(person);
        Person foundPerson = delegate.find(name);
        
        boolean delegateRemovedMe = delegate.remove(name);
        
        boolean removedDelegate = removePerson(delegateName);
        
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("findDelegateCreatedPerson", (dPerson != null));
        map.put("delegateFoundMe", (foundPerson != null));
        map.put("delegateRemovedMe", delegateRemovedMe);
        map.put("removedDelegate", removedDelegate);
        //map.put("emequal", (extendedEM == delegate.getEM()));

        return map;
    }

    Person findPerson() {

        Person p = extendedEM.find(Person.class, name);
        System.out.println("Found " + p);
        return p;
    }

    boolean removePerson(String personName) {

        Person p = extendedEM.find(Person.class, personName);
        boolean removed = false;
        if (p != null) {
            extendedEM.remove(p);
            removed = true;
        }
        return removed;
    }
    
}
