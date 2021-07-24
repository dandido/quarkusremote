package org.chedly;

import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/hello")
public class GreetingResource {

    @Inject
    // le meme nom que le fichier
    Template helloW;

    @Inject
    Template myhtml;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from remote Boom";
    }

    @GET
    @Path("/gettemp")
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance mytemp() {
        return helloW.data("name","Chedly?");
    }

    @GET
    @Path("/gethtml")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance mygethtml() {
        TemplateInstance  i = myhtml
        .data("name","Chedly via template?")
        .data("mylist",Arrays.asList("i1","i2","i3"));
        return i;
    }
}