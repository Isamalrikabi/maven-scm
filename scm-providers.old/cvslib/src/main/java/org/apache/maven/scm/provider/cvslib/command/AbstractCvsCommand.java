package org.apache.maven.scm.provider.cvslib.command;

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

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.AbstractCommand;
import org.apache.maven.scm.provider.cvslib.repository.CvsRepository;
import org.apache.maven.scm.provider.cvslib.util.CvsPass;
import org.apache.maven.scm.repository.Repository;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 */
public abstract class AbstractCvsCommand extends AbstractCommand
{
    private CvsRepository repository;
    private String tagName;

    /* (non-Javadoc)
     * @see org.apache.maven.scm.command.Command#setRepository(org.apache.maven.scm.repository.Repository)
     */
    public void setRepository(Repository repository) throws ScmException
    {
        if (repository instanceof CvsRepository)
        {
            this.repository = (CvsRepository)repository;

            if (repository.getPassword() != null)
            {
                try
                {
                    // Before we run a command lets make sure that there is an entry in the .cvspass
                    // file so that the checkout is successful.
                    CvsPass cvspass = new CvsPass();
                    cvspass.setCvsroot(this.repository.getCvsRoot());
                    cvspass.setPassword(this.repository.getPassword());
                    cvspass.execute();
                }
                catch (Exception e)
                {
                    //throw new ScmException( "Cannot create entry in .cvspass file to perform checkout.", e );
                }
            }
        }
        else
        {
            throw new ScmException("Invalid repository format");
        }
    }

    /* (non-Javadoc)
     * @see org.apache.maven.scm.command.Command#getRepository()
     */
    public Repository getRepository()
    {
        return repository;
    }

    /* (non-Javadoc)
     * @see org.apache.maven.scm.command.Command#setBranch(java.lang.String)
     */
    public void setBranch(String branchName)
    {
        setTag(branchName);
    }

    /* (non-Javadoc)
     * @see org.apache.maven.scm.command.Command#getBranch()
     */
    public String getBranch()
    {
        return getTag();
    }

    /* (non-Javadoc)
     * @see org.apache.maven.scm.command.Command#setTag(java.lang.String)
     */
    public void setTag(String tagName)
    {
        this.tagName = tagName;
    }

    /* (non-Javadoc)
     * @see org.apache.maven.scm.command.Command#getTag()
     */
    public String getTag()
    {
        return tagName;
    }
}
