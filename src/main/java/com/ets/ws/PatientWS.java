/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ets.ws;

import com.ets.ExaDataEngAssesmentApplication;
import com.ets.domain.Patient;
import com.ets.resource.ExternalResourceBundleBuilder;
import com.ets.resource.ResourceFactory;
import com.ets.service.PatientService;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yusufakhond
 */
@RestController
@RequestMapping//("/movies")
public class PatientWS {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private ResourceFactory factory;
    
    @RequestMapping("/save")
    public Patient save() {
        
        Bundle bundle = new ExternalResourceBundleBuilder()
                .setFhirContext(ExaDataEngAssesmentApplication.getCtx())
                .setResourceFileName("Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e.json")
                .build();
        factory.setBundle(bundle);
        Patient patient = factory.getPatient();
        return patientService.savePatient(patient);
    }
}
