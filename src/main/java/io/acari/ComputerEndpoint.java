package io.acari;

import io.acari.simple.web_service.GetAllComputerRequest;
import io.acari.simple.web_service.GetAllComputerResponse;
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
    @PayloadRoot(namespace = SERVICE_ENDPOINT, localPart = "getAllComputerRequest")
    public GetAllComputerResponse getAllComputerResponse(@RequestPayload GetAllComputerRequest getAllComputerRequest){
        Objects.requireNonNull(getAllComputerRequest, "Request cannot be null");
        GetAllComputerResponse getAllComputerResponse = new GetAllComputerResponse();
        getAllComputerResponse.getComputer().addAll(computerRepository.getAllComputers());
        return getAllComputerResponse;
    }
}
