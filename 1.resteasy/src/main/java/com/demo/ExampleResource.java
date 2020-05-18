package com.demo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.demo.data.Person;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> listAll(){
        List<Person> lst = new ArrayList<Person>();
        lst.add(new Person("Fuangwit","0815369999","Bangkok"));
        lst.add(new Person("Anchu","0815369990","Bangkok"));
        return lst;
    }
}