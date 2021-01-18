package com.demo;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.demo.data.Health;

import org.jboss.logging.Logger;

@Path("/status")
@ApplicationScoped
public class HealthResource {

    private static final Logger LOG = Logger.getLogger(HealthResource.class);

    @Inject
    Health health;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> status() {
        LOG.info("call /status");
        Map<String,String> mapStatus = new HashMap<String,String>();
        mapStatus.put("Liveness", health.isLiveness() ? "UP": "DOWN");
        mapStatus.put("Readiness", health.isReadiness() ? "UP": "DOWN");
        return mapStatus;
    }
    
    @GET
    @Path("not-ready")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> notReady(){
        LOG.info("call /status/not-ready");
        health.setReadiness(false);
        return status();
    }

    @GET
    @Path("ready")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> ready(){
        LOG.info("call /status/ready");
        health.setReadiness(true);
        return status();
    }

    @GET
    @Path("live")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> live(){
        LOG.info("call /status/live");
        health.setLiveness(true);
        return status();
    }

    @GET
    @Path("not-live")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> notLive(){
        LOG.info("call /status/not-live");
        health.setLiveness(false);
        return status();
    }



}
