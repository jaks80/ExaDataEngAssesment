package com.ets.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

/**
 *
 * @author yusufakhond
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedEntityGraph(
    name = "parent.withChildren",
    attributeNodes = {
            @NamedAttributeNode("encounter")
    }
)
public class Observation extends Resource{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_fk")
    private Encounter encounter;
    private String concept;
    private String catagory;
    private String result;

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

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
}
