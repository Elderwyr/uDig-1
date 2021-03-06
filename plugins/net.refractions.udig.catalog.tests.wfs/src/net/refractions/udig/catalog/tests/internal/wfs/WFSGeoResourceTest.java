/*
 *    uDig - User Friendly Desktop Internet GIS client
 *    http://udig.refractions.net
 *    (C) 2012, Refractions Research Inc.
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package net.refractions.udig.catalog.tests.internal.wfs;

import java.net.URL;

import net.refractions.udig.catalog.IGeoResource;
import net.refractions.udig.catalog.IService;
import net.refractions.udig.catalog.internal.wfs.WFSServiceExtension;
import net.refractions.udig.catalog.tests.AbstractGeoResourceTest;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;

/**
 * @author dzwiers
 */
public class WFSGeoResourceTest extends AbstractGeoResourceTest {

    private IService service = null;
    private IGeoResource resource = null;

    @Before
    public void setUp() throws Exception {
        WFSServiceExtension fac = new WFSServiceExtension();
        URL url = new URL("http://demo.opengeo.org/geoserver/wfs?"); //$NON-NLS-1$
        service = fac.createService(url, fac.createParams(url));
        resource = service.resources((IProgressMonitor) null).get(0);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see net.refractions.udig.catalog.tests.AbstractGeoResourceTest#getResolve()
     */
    protected IGeoResource getResolve() {
        return resource;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see net.refractions.udig.catalog.tests.AbstractResolveTest#hasParent()
     */
    protected boolean hasParent() {
        return true;
    }
    
}
