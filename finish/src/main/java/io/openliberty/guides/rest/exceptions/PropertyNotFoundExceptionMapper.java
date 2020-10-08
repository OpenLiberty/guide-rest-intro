package io.openliberty.guides.rest.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
// tag::generic[]
public class PropertyNotFoundExceptionMapper implements ExceptionMapper<PropertyNotFoundException> {
// end::generic[]

    @Override
    public Response toResponse(PropertyNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(404, ex.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(response)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }

}
