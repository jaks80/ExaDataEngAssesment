package com.ets.resource;

import com.ets.domain.Encounter;
import com.ets.domain.Patient;
import java.util.Date;
import org.hl7.fhir.r4.model.Period;

/**
 *
 * @author yusufakhond
 */
public class ResourceMapper {

    public static Patient convertHAPIPatient(org.hl7.fhir.r4.model.Patient hapiPatient) {
        Patient patient = new Patient();
        patient.setUuid(hapiPatient.getId());
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
}
