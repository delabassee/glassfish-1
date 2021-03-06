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

package samples.ejb.installed_libraries_embedded.ejb;

public interface Customer extends javax.ejb.EJBLocalObject
{
  public String getLastName() ;

  public String getFirstName() ;

  public String getAddress1() ;

  public String getAddress2() ;

  public String getCity() ;

  public String getState() ;

  public String getZipCode() ;

  public String getSSN() ;

  public long getSavingsBalance() ;

  public long getCheckingBalance() ;

  public void doCredit(long amount, String accountType) ;

  public void doDebit(long amount, String accountType) ;

}

  
