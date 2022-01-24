package com.ets;

import ca.uhn.fhir.context.FhirContext;
import com.ets.domain.Patient;
import com.ets.resource.ExternalResourceBundleBuilder;
import com.ets.resource.ResourceFactory;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
https://fhirblog.com/2014/08/18/processing-fhir-bundles-using-hapi/
 */
@SpringBootApplication
public class ExaDataEngAssesmentApplication {

    private static FhirContext ctx;

    public static void main(String[] args) {
        SpringApplication.run(ExaDataEngAssesmentApplication.class, args);
        ctx = FhirContext.forR4();
       
        Bundle bundle = new ExternalResourceBundleBuilder()
               .setFhirContext(ctx)
               .setResourceFileName("Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e.json")
               .build();
        ResourceFactory factory = new ResourceFactory(bundle);
        Patient patient = factory.getPatient();
        
//
//        /*
//        String input = "{"
//                + "\"resourceType\" : \"Patient\","
//                + "  \"name\" : [{"
//                + "    \"family\": \"Simpson\""
//                + "  }]"
//                + "}";
//        IParser parser = getCtx().newJsonParser();
//        Patient parsed = parser.parseResource(Patient.class, input);
//        System.out.println(parsed.getName().get(0).getFamily());
//         */
//        
//        BundleParser bundleParser = new BundleParser(ctx);
//        InputStream is = BundleParser.class.getClassLoader().getResourceAsStream("Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e.json");
//        bundleParser.unbundle(is);
    }

    public static FhirContext getCtx() {
        return ctx;
    }
}
