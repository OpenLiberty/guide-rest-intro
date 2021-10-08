// tag::comment[]
/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
// end::comment[]
package io.openliberty.guides.rest;

import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.openliberty.guides.rest.exceptions.PropertyNotFoundException;

// tag::path[]
@Path("properties")
// end::path[]
public class PropertiesResource {

    // tag::get1[]
    @GET
    // end::get1[]
    // tag::produces1[]
    @Produces(MediaType.APPLICATION_JSON)
    // end::produces1[]
    public Properties getProperties() {
        return System.getProperties();
    }

    // tag::get2[]
    @GET
    // end::get2[]
    // tag::pathParam1[]
    @Path("/{property}")
    // end::pathParam1[]
    // tag::produces2[]
    @Produces(MediaType.APPLICATION_JSON)
    // end::produces2[]
    // tag::getProperty[]
    // tag::pathParam2[]
    public String getProperty(@PathParam("property") String prop) 
    // end::pathParam2[]
            throws PropertyNotFoundException {
        Properties properties = System.getProperties();
        System.out.println(prop);
        if (properties.containsKey(prop)) {
            return "{" + 
                "\"" + prop + "\" : " + 
                "\"" + properties.getProperty(prop) + "\"" + 
            "}";
        }
        throw new PropertyNotFoundException("Property " + prop + " not found");
    }
    // end::getProperty[]

}
