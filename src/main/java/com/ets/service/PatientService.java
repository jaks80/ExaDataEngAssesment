package com.ets.service;

import com.ets.dao.PatientRepository;
import com.ets.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond
 */
@Service("patientService")
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientService() {
        
    }

    public Patient savePatient(Patient patient) {
       
        patient = patientRepository.saveAndFlush(patient);
        return patient;
    }

}
