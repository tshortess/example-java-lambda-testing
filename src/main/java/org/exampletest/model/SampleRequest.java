package org.exampletest.model;

public class SampleRequest {
    private String message;

    public SampleRequest() {
    }

    public SampleRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
