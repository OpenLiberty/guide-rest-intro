package io.openliberty.guides.rest.exceptions;

public class ErrorResponse {
    int errorCode;
    String errorMessage;

    public ErrorResponse(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
