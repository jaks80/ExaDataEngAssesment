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
@RequestMapping
public class BundleWS {

    private String[] files = {"Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e",
         "Aaron697_Jerde200_6fa23508-960e-ff22-c3d0-0519a036543b",
         "Abbey813_Price929_83524678-9bff-93b7-ef89-d7f5390072ff"};
    @Autowired
    private PatientService patientService;

    @Autowired
    private ResourceFactory factory;

    @RequestMapping("/")
    public String home() {

        for (String s : files) {
            Bundle bundle = new ExternalResourceBundleBuilder()
                    .setFhirContext(ExaDataEngAssesmentApplication.getCtx())
                    .setResourceFileName(s)
                    .build();

            factory.setBundle(bundle);
            Patient patient = factory.getPatient();
            patientService.savePatient(patient);
        }
        return "Resource reading started....";
    }
}
