package io.openliberty.guides.rest.exceptions;

public class ErrorResponse {
    String errorCode;
    String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
