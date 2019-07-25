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
package it.io.openliberty.guides.rest;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class EndpointTest {
    
    private static final Jsonb jsonb = JsonbBuilder.create();

    // tag::test[]
    @Test
    // end::test[]
    public void testGetProperties() {
        // tag::systemProperties[]
        String port = System.getProperty("liberty.test.port");
        String war = System.getProperty("war.name");
        // end::systemProperties[]
        String url = "http://localhost:" + port + "/" + war + "/";

        // tag::clientSetup[]
        Client client = ClientBuilder.newClient();
        // end::clientSetup[]

        // tag::target[]
        WebTarget target = client.target(url + "System/properties");
        // end::target[]
        // tag::requestget[]
        Response response = target.request().get();
        // end::requestget[]

        // tag::assertequals[]
        assertEquals("Incorrect response code from " + url, 
                     Response.Status.OK.getStatusCode(), response.getStatus());
        // end::assertequals[]

        // tag::body[]
        String json = response.readEntity(String.class);
        Properties sysProps = jsonb.fromJson(json, Properties.class);

        // tag::assertosname[]
        assertEquals("The system property for the local and remote JVM should match",
                     System.getProperty("os.name"),
                     sysProps.getProperty("os.name"));
        // end::assertosname[]
        // end::body[]
        response.close();
    }
}
