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
package net.refractions.udig.core.filter;

import java.util.Set;

import org.opengis.filter.Id;
import org.opengis.filter.identity.Identifier;

/**
 * AdaptingFilter that implements Id interface.
 * 
 * @author Jody
 * @since 1.1.0
 */
class AdaptingId extends AdaptingFilter<Id> implements Id {

    AdaptingId( Id filter ) {
        super(filter);
    }
    
    public Set<Object> getIDs() {
        return wrapped.getIDs();
    }

    public Set<Identifier> getIdentifiers() {
        return wrapped.getIdentifiers();
    }
    public String toString() {
        return "Adapting:"+wrapped.getIDs();
    }
}
