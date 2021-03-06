/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.io.*;
import java.net.*;
import com.sun.ejte.ccl.reporter.*;

/*
 * Unit test for:
 *
 *  https://glassfish.dev.java.net/issues/show_bug.cgi?id=6391
 *  ("PreDestroy not called in certain web components")
 *
 */
public class WebTest {

    private static final String TEST_NAME = "servlet-context-listener-annotation-predestroy";

    private static SimpleReporterAdapter stat
        = new SimpleReporterAdapter("appserv-tests");

    private String host;
    private String port;
    private String contextRoot;

    public WebTest(String[] args) {
        host = args[0];
        port = args[1];
        contextRoot = args[2];
    }
    
    public static void main(String[] args) {

        stat.addDescription("Unit test for Issue 6391");
        WebTest webTest = new WebTest(args);

        try {
            webTest.doTest();
        } catch (Exception ex) {
            ex.printStackTrace();
            stat.addStatus(TEST_NAME, stat.FAIL);
        }

	stat.printSummary();
    }

    public void doTest() throws Exception {

        File inFile = null;
        FileInputStream fis = null;
        BufferedReader br = null;
        String line = null;
        try {
            inFile = new File("/tmp/mytest");
            fis = new FileInputStream(inFile);
            br = new BufferedReader(new InputStreamReader(fis));
            line = br.readLine();
        } finally {
            inFile.delete();
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ioe) {
                // ignore
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                // ignore
            }
        }
            
        if ("SUCCESS".equals(line)) {
            stat.addStatus(TEST_NAME, stat.PASS);
	} else {
            stat.addStatus(TEST_NAME, stat.FAIL);
        }
    }
}
