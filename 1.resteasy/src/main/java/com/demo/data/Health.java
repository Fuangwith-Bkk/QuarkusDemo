package com.demo.data;

import javax.inject.Singleton;

@Singleton
public class Health {

    private boolean liveness = true;
    private boolean readiness = true;

    public Health(){}

    public Health(boolean liveness, boolean readiness){
        this.liveness = liveness;
        this.readiness = readiness;
    }
    
    public boolean isLiveness(){
        return this.liveness;
    }

    public void setLiveness(boolean liveness){
        this.liveness = liveness;
    }

    public boolean isReadiness(){
        return this.readiness;
    }

    public void setReadiness(boolean readiness){
        this.readiness = readiness;
    }

}
