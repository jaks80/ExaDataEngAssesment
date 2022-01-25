package com.ets;

import ca.uhn.fhir.context.FhirContext;
import com.ets.resource.ResourceFactory;
import com.ets.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
https://fhirblog.com/2014/08/18/processing-fhir-bundles-using-hapi/
 */
@SpringBootApplication
public class ExaDataEngAssesmentApplication {

    private static FhirContext ctx;
    @Autowired
    private static PatientService patientService;

    public static void main(String[] args) {
        SpringApplication.run(ExaDataEngAssesmentApplication.class, args);
        ctx = FhirContext.forR4();
        
        ApplicationContext context = new AnnotationConfigApplicationContext(ExaDataEngAssesmentApplication.class);
        
        PatientService patientService = context.getBean(PatientService.class);
        ResourceFactory factory = context.getBean(ResourceFactory.class);

//        Bundle bundle = new ExternalResourceBundleBuilder()
//                .setFhirContext(ctx)
//                .setResourceFileName("Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e.json")
//                .build();
//        factory = new ResourceFactory(bundle);
//        Patient patient = factory.getPatient();
//        patient = patientService.savePatient(patient);
    }

    public static FhirContext getCtx() {
        return ctx;
    }
}
