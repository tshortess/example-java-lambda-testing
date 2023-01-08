package org.exampletest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.exampletest.model.SampleRequest;
import org.exampletest.model.SampleResponse;

public class Handler implements RequestHandler<SampleRequest, SampleResponse>{
    @Override
    public SampleResponse handleRequest(SampleRequest sampleRequest, Context context)
    {

        return sampleRequest.getMessage().contains("Hello") ? new SampleResponse("200", sampleRequest.getMessage()) :
                new SampleResponse("400", sampleRequest.getMessage());
    }
}