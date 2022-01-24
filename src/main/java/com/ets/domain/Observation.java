package com.ets.domain;

/**
 *
 * @author yusufakhond
 */
public class Observation extends Resource{
    private Encounter encounter;
    private String concept;

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
    
    
}
