package com.demo.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.demo.data.Health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck{

    @Inject
    Health health;

	@Override
	public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("App Readiness");
        if(health.isReadiness()){
            responseBuilder.up();
        }else{
            responseBuilder.down();
        }
		return responseBuilder.build();
	}

    
}
