package com.ets.resource;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Encounter.DiagnosisComponent;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Resource;

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
                        this.patient = (Patient) r;
                        this.patient.getBirthDate();
                        System.out.println("Patient name: " + this.patient.getName());
                        break;
                    case Encounter:
                        Encounter encounter = (Encounter) r;
                        this.encounters.add(encounter);
                        //List<CodeableConcept> reasonCode = encounter.getReasonCode();
                        
                        String reasonCodeString = encounter.getReasonCodeFirstRep().getCodingFirstRep().getDisplay();
                        
                        String _patientUUID= encounter.getSubject().getReference();
                        int index = _patientUUID.lastIndexOf(':');
                        String patientUUID = _patientUUID.substring(index+1);
                        
                        Period period = encounter.getPeriod();
                        Date dateStart = period.getStart();
                        Date end = period.getEnd();

                        List<DiagnosisComponent> diagnosis = encounter.getDiagnosis();

                        System.out.println("Encoutner: " + encounter.getId());
                        
                        //List<CodeableConcept> type = encounter.getType();
                        //type.forEach(c -> System.out.println("  "+c.getText()));
                        String encounterType = encounter.getTypeFirstRep().getText();
                        System.out.println("  Type: "+encounterType+" Reason:"+reasonCodeString+" Patient:"+patientUUID);
                        
                        break;
                    case Observation:
                        Observation observation = (Observation) r;

                        //System.out.println("Observations: " + observation.getId() + " Encounter: " + observation.getEncounter().getReference());
                        this.observations.add(observation);
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
