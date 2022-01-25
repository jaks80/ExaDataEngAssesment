package com.ets.resource;

import com.ets.domain.Encounter;
import com.ets.domain.Observation;
import com.ets.domain.Patient;
import com.ets.service.PatientService;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond
 */
@Service
public class ResourceFactory {
    
    private Bundle bundle;
    private Patient patient;
    private Set<Encounter> encounters = new LinkedHashSet<>();
    private Set<Observation> observations = new LinkedHashSet<>();

    public ResourceFactory(){
    
    }
    
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
        for (Bundle.BundleEntryComponent be : bundle.getEntry()) {
            Resource r = be.getResource();
           
            if (r != null) {
                switch (r.getResourceType()) {
                    case Patient:
                        org.hl7.fhir.r4.model.Patient hapiPatient = (org.hl7.fhir.r4.model.Patient) r;

                        patient = ResourceMapper.convertHAPIPatient(hapiPatient);                        
                        break;
                    case Encounter:
                        org.hl7.fhir.r4.model.Encounter hapiEncounter = (org.hl7.fhir.r4.model.Encounter) r;

                        this.encounters.add(ResourceMapper.convertHAPIEncounter(hapiEncounter));

                        break;
                    case Observation:
                        org.hl7.fhir.r4.model.Observation observation = (org.hl7.fhir.r4.model.Observation) r;

                        this.observations.add(ResourceMapper.convertHAPIObservation(observation));
                        
                        break;

                    case Condition:

                        Condition condition = (Condition) r;
                        //System.out.println("Condition: " + condition.getCode().getCodingFirstRep().getDisplay());
                        break;
                }
            }
        }
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Set<Encounter> getEncounters() {
        return encounters;
    }

}
