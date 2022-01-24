package com.ets.service;

import com.ets.dao.PatientDAO;
import com.ets.domain.Patient;
import com.ets.resource.ResourceMapper;

/**
 *
 * @author yusufakhond
 */
public class PatientService {

    private PatientDAO dao;

    public PatientService() {
        dao = new PatientDAO();
    }

    public int savePatient(org.hl7.fhir.r4.model.Patient hapiPatient) {
        Patient patient = ResourceMapper.convertHAPIPatient(hapiPatient);
        dao.save(patient);
        return 1;
    }

}
