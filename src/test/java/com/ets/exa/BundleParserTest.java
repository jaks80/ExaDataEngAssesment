package com.ets.exa;

import ca.uhn.fhir.context.FhirContext;
import com.ets.resource.ExternalResourceBundleBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.hl7.fhir.r4.model.Bundle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author yusufakhond
 */
public class BundleParserTest {

    private static FhirContext ctx;

    public BundleParserTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        ctx = FhirContext.forR4();
    }

    @Test
    public void testSomeMethod() {
        Bundle bundle = new ExternalResourceBundleBuilder()
                .setFhirContext(ctx)
                .setResourceFileName("Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e.json")
                .build();
        //ResourceFactory factory = new ResourceFactory(bundle);
        //Patient patient = factory.getPatient();
        System.out.println("");
    }

    @Test
    public void testGetAllFilesInResource() throws URISyntaxException, IOException {
        
        
       
    }

}
