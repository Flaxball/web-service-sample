package io.acari;

import io.acari.simple.web_service.AllComputersRequest;
import io.acari.simple.web_service.AllComputersResponse;
import io.acari.simple.web_service.ComputersByModelRequest;
import io.acari.simple.web_service.ComputersByModelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Objects;

@Endpoint
public class ComputerEndpoint {
    static final String SERVICE_ENDPOINT = "http://acari.io/simple/web-service";
    private ComputerRepository computerRepository;

    @Autowired
    public ComputerEndpoint(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @ResponsePayload
    @PayloadRoot(namespace = SERVICE_ENDPOINT, localPart = "allComputersRequest")
    public AllComputersResponse allComputersRequest(@RequestPayload AllComputersRequest allComputersRequest){
        Objects.requireNonNull(allComputersRequest, "Request cannot be null");
        AllComputersResponse AllComputersResponse = new AllComputersResponse();
        AllComputersResponse.getComputers().addAll(computerRepository.allComputers());
        return AllComputersResponse;
    }

    @ResponsePayload
    @PayloadRoot(namespace = SERVICE_ENDPOINT, localPart = "computersByModelRequest")
    public ComputersByModelResponse computersByModelRequest(@RequestPayload ComputersByModelRequest computersByModelRequest){
        Objects.requireNonNull(computersByModelRequest, "Request cannot be null");
        ComputersByModelResponse computersByModelResponse = new ComputersByModelResponse();
        computersByModelResponse.getComputers().addAll(computerRepository.computerByModel(computersByModelRequest.getModel()));
        return computersByModelResponse;
    }
}
