package org.apache.maven.scm.provider.clearcase.command;

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

import junit.framework.TestCase;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.clearcase.repository.ClearcaseRepository;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 */
public class ClearcaseCommandWrapperTest extends TestCase
{
    private ClearcaseCommandWrapper wrapper;
    
    public ClearcaseCommandWrapperTest(String testName)
    {
        super(testName);
    }
    
    public void setUp() throws Exception
    {
        wrapper = new ClearcaseCommandWrapper();
    }
    
    public void testSetValidRepository()
    {
        try
        {
            ClearcaseRepository repo = new ClearcaseRepository();
            wrapper.setRepository(repo);
            assertEquals(repo, wrapper.getRepository());
        }
        catch(ScmException e)
        {
            fail(e.getMessage());
        }
    }
    
    public void testSetInvalidRepository()
    {
        try
        {
            wrapper.setRepository(null);
            fail();
        }
        catch(ScmException e)
        {
        }
    }
}