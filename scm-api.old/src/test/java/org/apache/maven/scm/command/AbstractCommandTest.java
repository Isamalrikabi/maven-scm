package org.apache.maven.scm.command;

/* ====================================================================
 * Copyright 2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

import java.io.File;

import junit.framework.TestCase;

import org.apache.maven.scm.util.DefaultConsumer;
import org.apache.maven.scm.util.StreamConsumer;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 */
public class AbstractCommandTest extends TestCase
{
	private String baseDir;
    /**
     * @param arg0
     */
    public AbstractCommandTest(String testName)
    {
        super(testName);
    }

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		baseDir = System.getProperty("basedir");
		assertNotNull("The system property basedir was not defined.", baseDir);
	}
    
    public void testWorkingDirectory()
    {
    	TestCommand cmd = new TestCommand();
    	cmd.setWorkingDirectory(baseDir + "/work");
    	String wd = cmd.getWorkingDirectory();
    	File workDir = new File(wd);
    	assertTrue(workDir.exists());
    	workDir.delete();
    }
    
    public void testCommandExecutionWithWorkingDir()
    {
        try
        {
        	TestCommand cmd = new TestCommand();
        	cmd.setWorkingDirectory(baseDir);
        	StreamConsumer cons = new DefaultConsumer();
            cmd.setConsumer(cons);
            assertEquals(cons, cmd.getConsumer());
            cmd.execute();
        }
        catch(Exception e)
        {
            fail(e.getMessage());
        }
    }
    
    public void testCommandExecutionWithoutWorkingDir()
    {
        try
        {
        	TestCommand cmd = new TestCommand();
        	//cmd.setWorkingDirectory(baseDir);
        	StreamConsumer cons = new DefaultConsumer();
            cmd.setConsumer(cons);
            assertEquals(cons, cmd.getConsumer());
            cmd.execute();
        }
        catch(Exception e)
        {
            fail(e.getMessage());
        }
    }
}
