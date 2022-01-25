package com.ets.resource;

import com.ets.domain.Encounter;
import com.ets.domain.Observation;
import com.ets.domain.Patient;
import java.util.Date;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Period;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond
 */

@Service
public class ResourceMapper {

    public ResourceMapper(){}
    
    public static Patient convertHAPIPatient(org.hl7.fhir.r4.model.Patient hapiPatient) {
        Patient patient = new Patient();        
        
        String _patientUUID = hapiPatient.getId();
        int index = _patientUUID.lastIndexOf(':');
        String patientUUID = _patientUUID.substring(index + 1);
        
        patient.setUuid(patientUUID);
        
        patient.setFamilyName(hapiPatient.getNameFirstRep().getFamily());
        patient.setGivenName(hapiPatient.getNameFirstRep().getGivenAsSingleString());
        patient.setDateOfBirth(hapiPatient.getBirthDate());
        return patient;
    }

    public static Encounter convertHAPIEncounter(org.hl7.fhir.r4.model.Encounter hapiEncounter) {
        Encounter encounter = new Encounter();
        String _encounterUUID = hapiEncounter.getId();
        int index = _encounterUUID.lastIndexOf(':');
        String encounterUUID = _encounterUUID.substring(index + 1);
        encounter.setUuid(encounterUUID);

        encounter.setType(hapiEncounter.getTypeFirstRep().getText());
        encounter.setReasonCode(hapiEncounter.getReasonCodeFirstRep().getCodingFirstRep().getDisplay());

        String _patientUUID = hapiEncounter.getSubject().getReference();
        index = _patientUUID.lastIndexOf(':');
        String patientUUID = _patientUUID.substring(index + 1);

        Period period = hapiEncounter.getPeriod();
        Date dateStart = period.getStart();
        Date end = period.getEnd();

        encounter.setDateStart(dateStart);
        encounter.setDateEnd(end);
        return encounter;
    }

    public static Observation convertHAPIObservation(org.hl7.fhir.r4.model.Observation hapiObservation) {
        Observation observation = new Observation();
        
        String _obsrvationUUID = hapiObservation.getId();

        int index1 = _obsrvationUUID.lastIndexOf(':');
        String obsrvationUUID = _obsrvationUUID.substring(index1 + 1);
        observation.setUuid(obsrvationUUID);
        
        String concept = hapiObservation.getCode().getCodingFirstRep().getDisplay();
        observation.setConcept(concept);
        
        String catagory = hapiObservation.getCategoryFirstRep().getCodingFirstRep().getDisplay();
        observation.setCatagory(catagory);

        String unit = "";
        String value = "";

        if (hapiObservation.getValue() instanceof Quantity) {

            unit = hapiObservation.getValueQuantity().getUnit();            
            value = hapiObservation.getValueQuantity().getValue().toString();
            observation.setResult(value+" "+unit);

        } else if (hapiObservation.getValue() instanceof CodeableConcept) {
            System.out.println("");
        }

        return observation;
    }
}
