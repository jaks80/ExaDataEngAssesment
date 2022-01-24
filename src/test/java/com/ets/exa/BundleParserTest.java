/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ets.exa;

import ca.uhn.fhir.context.FhirContext;
import com.ets.domain.Patient;
import com.ets.resource.ExternalResourceBundleBuilder;
import com.ets.resource.ResourceFactory;
import java.io.InputStream;
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
        ResourceFactory factory = new ResourceFactory(bundle);
        Patient patient = factory.getPatient();
    }

}
