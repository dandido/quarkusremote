package org.chedly;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

@Path("/hero")
public class HeroRessource {
    //use http://localhost:8085/q/metrics
    private AtomicInteger level = new AtomicInteger(0);
    private final MeterRegistry registry;

    HeroRessource(MeterRegistry registry) {
        this.registry = registry;
        this.registry.gauge("custome.level",level);
    }
    @GET
    @Path("/up")
    //savoir le nombre d'appel de cette methode
    @Counted(value = "custome.levelup")
    public String levelup(){
        level.incrementAndGet();
        return "level up";
    }

    @GET
    @Path("/down")
    @Counted(value = "custome.leveldown")
    public String leveldown(){
        level.decrementAndGet();
        return "level up";
    }

    @GET
    @Path("/battle")
    @Timed(value = "custome.battleTime")
    // for test purpose only , you should never do throws like that 
    public String battle() throws InterruptedException{
        Thread.sleep(3000);
        return "Battle";
    }
}
