package com.ets.resource;

import com.ets.domain.Encounter;
import com.ets.domain.Observation;
import com.ets.domain.Patient;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond Resource factory is a Factory class that take foreign
 * bundle as input and produce local object.
 */
@Service
public class ResourceFactory {

    private Bundle bundle;
    private Patient patient;
    //private List<Encounter> encounters = new ArrayList<>();
    private Map<String,Encounter> encounterMap = new LinkedHashMap<>();
    private List<Observation> observations = new ArrayList<>();

    public ResourceFactory() {

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

                        Encounter encounter = ResourceMapper.convertHAPIEncounter(hapiEncounter);
                        encounterMap.put(encounter.getUuid(), encounter);
                        //this.encounters.add(encounter);

                        break;
                    case Observation:
                        org.hl7.fhir.r4.model.Observation hapiObservation = (org.hl7.fhir.r4.model.Observation) r;

                        Observation observation = ResourceMapper.convertHAPIObservation(hapiObservation);
                        
                        //Assigning Observation to Encounter (OneToMany)
                        String _encounterReference = hapiObservation.getEncounter().getReference();
                        int index1 = _encounterReference.lastIndexOf(':');
                        String encunterUUID = _encounterReference.substring(index1 + 1);
                        observation.setEncounter(encounterMap.get(encunterUUID));
                        
                        this.getObservations().add(observation);                        

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

    public List<Encounter> getEncounters() {
        return new ArrayList<>(encounterMap.values());
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

}
