package com.demo.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.demo.data.Health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

    @Inject
    Health health;

	@Override
	public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("App Liveness");
        if(health.isLiveness()){
            responseBuilder.up();
        }else{
            responseBuilder.down();
        }
        
		return responseBuilder.build();
	}
    
}
