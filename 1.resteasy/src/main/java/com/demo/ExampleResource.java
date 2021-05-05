package com.demo;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.demo.data.Health;
import com.demo.data.Person;


import org.jboss.logging.Logger;

@Path("/hello")
@ApplicationScoped
public class ExampleResource {

    private static final Logger LOG = Logger.getLogger(ExampleResource.class);

    @Inject
    private Health health;
    
    private String hostName;

    public ExampleResource(){
        try{
            this.hostName = InetAddress.getLocalHost().getHostName();
        }catch(Exception ex){
            System.out.println("Get hostname error!!");
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        String message =  this.hostName + " >> Hello";
        
        if(health.isLiveness() && health.isReadiness()){
            LOG.info("call /hello : " + message);
            return Response.ok(message).build();
        }else{
            return Response.status(503).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(){
        
        List<Person> lst = new ArrayList<Person>();
        lst.add(new Person("Fuangwit","0815369999","Bangkok"));
        lst.add(new Person("Anchu","0815369990","Bangkok"));

        if(health.isLiveness() && health.isReadiness()){
            LOG.info("call /hello/listAll");
            return Response.ok(lst).build();
        }else{
            return Response.status(503).build();
        }
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response echoParam(@PathParam("name") String name){
        String message = this.hostName + " >> Hello " + name;
    
        if(health.isLiveness() && health.isReadiness()){
            LOG.info("call hello/{name} : " + message);
            return Response.ok(message).build();
        }else{
            return Response.status(503).build();
        }
    }

    @POST
    public Response echoPostBody(String body){
        String message = "POST " + this.hostName + "/hello [Body] >> \n" + body;
        LOG.info(message);
        return Response.ok(message).build();
    }
}