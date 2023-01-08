package org.exampletest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.tests.annotations.*;
import org.exampletest.model.SampleRequest;
import org.exampletest.model.SampleResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HandlerTest {

    @Mock
    Context contextMock;

    @ParameterizedTest
    @Event(value = "events/hello_world.json", type = SampleRequest.class)
    public void singleEventTestReturns200(SampleRequest sampleRequest) throws IOException {
        assertThat(sampleRequest, is(not(nullValue())));

        Handler handler = new Handler();
        SampleResponse actualResponse = handler.handleRequest(sampleRequest, contextMock);

        assertThat(actualResponse.getMessage(), equalTo("Hello, World"));
        assertThat(actualResponse.getStatusCode(), equalTo("200"));
    }

    @ParameterizedTest
    @Event(value = "events/goodbye.json", type = SampleRequest.class)
    public void singleEventTestReturns400(SampleRequest sampleRequest) throws IOException {
        assertThat(sampleRequest, is(not(nullValue())));

        Handler handler = new Handler();
        SampleResponse actualResponse = handler.handleRequest(sampleRequest, contextMock);

        assertThat(actualResponse.getMessage(), equalTo("Goodbye"));
        assertThat(actualResponse.getStatusCode(), equalTo("400"));
    }


    @ParameterizedTest
    @HandlerParams(
            event = @Event(value = "events/hello_world.json", type = SampleRequest.class),
            response = @Response(value = "responses/hello_world.json", type = SampleResponse.class)
    )
    public void singleEventAndResponseTestReturns200(SampleRequest sampleRequest, SampleResponse expectedResponse) throws IOException {
        assertThat(sampleRequest, is(not(nullValue())));
        assertThat(expectedResponse, is(not(nullValue())));

        Handler handler = new Handler();
        SampleResponse actualResponse = handler.handleRequest(sampleRequest, contextMock);

        assertThat(actualResponse.getMessage(), equalTo(expectedResponse.getMessage()));
        assertThat(actualResponse.getStatusCode(), equalTo(expectedResponse.getStatusCode()));
    }

    @ParameterizedTest
    @HandlerParams(
            event = @Event(value = "events/goodbye.json", type = SampleRequest.class),
            response = @Response(value = "responses/goodbye.json", type = SampleResponse.class)
    )
    public void singleEventAndResponseTestReturns400(SampleRequest sampleRequest, SampleResponse expectedResponse) throws IOException {
        assertThat(sampleRequest, is(not(nullValue())));
        assertThat(expectedResponse, is(not(nullValue())));

        Handler handler = new Handler();
        SampleResponse actualResponse = handler.handleRequest(sampleRequest, contextMock);

        assertThat(actualResponse.getMessage(), equalTo(expectedResponse.getMessage()));
        assertThat(actualResponse.getStatusCode(), equalTo(expectedResponse.getStatusCode()));
    }

    @ParameterizedTest
    @HandlerParams(
            events = @Events(folder = "events/", type = SampleRequest.class),
            responses = @Responses(folder = "responses/", type = SampleResponse.class)
    )
    public void multiEventAndResponseTest(SampleRequest sampleRequest, SampleResponse expectedResponse) throws IOException {
        assertThat(sampleRequest, is(not(nullValue())));
        assertThat(expectedResponse, is(not(nullValue())));

        Handler handler = new Handler();
        SampleResponse actualResponse = handler.handleRequest(sampleRequest, contextMock);

        assertThat(actualResponse.getMessage(), equalTo(expectedResponse.getMessage()));
        assertThat(actualResponse.getStatusCode(), equalTo(expectedResponse.getStatusCode()));
    }
}
