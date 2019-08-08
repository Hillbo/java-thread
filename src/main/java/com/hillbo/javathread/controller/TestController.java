package com.hillbo.javathread.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Controller
@Path("/testController")
public class TestController {

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "test";
    }

}
