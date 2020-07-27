package io.openliberty.guides.rest.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

// tag::provider[]
@Provider
// end::provider[]
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    // tag::toResponse[]
    @Override
    public Response toResponse(Throwable t) {
        ErrorResponse response = new ErrorResponse("500", t.getMessage());
        return Response.serverError()
                       .entity(response)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
    //end::toResponse[]

}
