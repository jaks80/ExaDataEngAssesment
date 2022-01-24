package com.ets.resource;

import com.ets.domain.Encounter;
import com.ets.domain.Patient;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
//import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Encounter.DiagnosisComponent;
import org.hl7.fhir.r4.model.Observation;

import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Type;

/**
 *
 * @author yusufakhond
 */
public class ResourceFactory {

    private Bundle bundle;
    private Patient patient;
    private Set<Encounter> encounters = new LinkedHashSet<>();
    private Set<Observation> observations = new LinkedHashSet<>();

    public ResourceFactory(Bundle bundle) {
        this.bundle = bundle;
        for (Bundle.BundleEntryComponent be : bundle.getEntry()) {
            Resource r = be.getResource();
            if (r != null) {
                switch (r.getResourceType()) {
                    case Patient:
                        org.hl7.fhir.r4.model.Patient hapiPatient = (org.hl7.fhir.r4.model.Patient) r;
                        
                        patient = ResourceMapper.convertHAPIPatient(hapiPatient);
                        
//                        System.out.println("Patient name: " + this.patient.getFamilyName() + " -:" + this.patient.getGivenName());
//                        System.out.println("Patient UUID: " + this.patient.getId());
//                        System.out.println("DOB: " + this.patient.getDateOfBirth());

                        break;
                    case Encounter:
                        org.hl7.fhir.r4.model.Encounter hapiEncounter = (org.hl7.fhir.r4.model.Encounter) r;                        
                        
                        Encounter encounter = ResourceMapper.convertHAPIEncounter(hapiEncounter);
                        this.encounters.add(encounter);
                        
                        break;
                    case Observation:
                        Observation observation = (Observation) r;

                        this.observations.add(observation);
                        String concept = observation.getCode().getCodingFirstRep().getDisplay();
                        
                        String catagory = observation.getCategoryFirstRep().getCodingFirstRep().getDisplay();
                        
                        
                        //Type type = observation.getValue();
                        String unit = observation.getValueQuantity().getUnit();
                        String vale = observation.getValueQuantity().getValue().toString();
                        //String value = observation.getValueCodeableConcept().getCodingFirstRep().getDisplay();
                        
                        //List<Coding> list = observation.getc.getCode().getCoding();
                        
                        String _encounterReference = observation.getEncounter().getReference();

                        int index1 = _encounterReference.lastIndexOf(':');
                        String encunterUUID = _encounterReference.substring(index1 + 1);
                        System.out.println("Observation Encounter Ref:" + encunterUUID+" obs:"+concept);
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
