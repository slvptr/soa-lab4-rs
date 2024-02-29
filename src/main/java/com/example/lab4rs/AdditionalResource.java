package com.example.lab4rs;

import com.example.lab4rs.client.AdditionalService;
import com.example.lab4rs.client.AdditionalWSI;
import com.example.lab4rs.client.CountTransferred;
import com.example.lab4rs.client.CountTransferredResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

@Path("")
public class AdditionalResource {
    final String WSDL_URL = "http://127.0.0.1:8080/lab4-ws-1.0-SNAPSHOT/Additional?wsdl";
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/count-transferred")
    @Produces("text/plain")
    public Response countTransferred() throws MalformedURLException {
        try {
            URL url = new URL(WSDL_URL);

            AdditionalService additionalService
                    = new AdditionalService(url);
            AdditionalWSI employeeServiceProxy
                    = additionalService.getAdditionalPort();

            CountTransferred params = new CountTransferred();
            CountTransferredResponse response = employeeServiceProxy.countTransferred(params);

            return Response.ok()
                    .entity(response.getReturn())
                    .build();
        } catch (NoSuchElementException e) {
            return Response.status(500).build();
        }
    }
}