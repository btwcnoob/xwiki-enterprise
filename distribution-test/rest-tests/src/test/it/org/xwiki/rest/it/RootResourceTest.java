/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rest.it;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xwiki.rest.Relations;
import org.xwiki.rest.it.framework.AbstractHttpTest;
import org.xwiki.rest.model.jaxb.Link;
import org.xwiki.rest.model.jaxb.Xwiki;
import org.xwiki.rest.resources.RootResource;

public class RootResourceTest extends AbstractHttpTest
{
    @Override
    public void testRepresentation() throws Exception
    {
        GetMethod getMethod = executeGet(getFullUri(RootResource.class));
        assertEquals(getHttpMethodInfo(getMethod), HttpStatus.SC_OK, getMethod.getStatusCode());

        Xwiki xwiki = (Xwiki) unmarshaller.unmarshal(getMethod.getResponseBodyAsStream());

        Link link = getFirstLinkByRelation(xwiki, Relations.WIKIS);
        assertNotNull(link);

        link = getFirstLinkByRelation(xwiki, Relations.SYNTAXES);
        assertNotNull(link);

        // link = xwikiRoot.getFirstLinkByRelation(Relations.WADL);
        // assertNotNull(link);

        checkLinks(xwiki);
    }
}