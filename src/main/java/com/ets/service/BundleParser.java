//package com.ets.service;
//
//import ca.uhn.fhir.context.FhirContext;
//import ca.uhn.fhir.parser.IParser;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import org.hl7.fhir.exceptions.FHIRFormatError;
//import org.hl7.fhir.r4.model.Bundle;
//import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
//import org.hl7.fhir.r4.model.Encounter;
//import org.hl7.fhir.r4.model.Observation;
//import org.hl7.fhir.r4.model.Patient;
//import org.hl7.fhir.r4.model.Resource;
//import org.hl7.fhir.utilities.Utilities;
//
///**
// *
// * @author yusufakhond
// */
//public class BundleParser {
//
//    //Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e
//    private FhirContext ctx;
//
//    public BundleParser(FhirContext ctx) {
//        this.ctx = ctx;
//    }
//
//    public void unbundle(InputStream is) {
//
//        IParser parser = ctx.newJsonParser();
//
//        Bundle bnd = parser.parseResource(Bundle.class, is);
//        //System.out.println(bnd.getType());
//
//        for (BundleEntryComponent be : bnd.getEntry()) {
//            Resource r = be.getResource();
//            if (r != null) {
//
//                //String tgt = Utilities.path(folder, r.fhirType() + "-" + r.getId() + ".json");
//                // new JsonParser().compose(new FileOutputStream(tgt), r);
//                //System.out.println(r.getResourceType());
//                switch (r.getResourceType()) {
//                    case Patient:
//                        Patient patient = (Patient) r;
//
//                        System.out.println(patient.getName());
//                        break;
//                    case Encounter:
//                        Encounter encounter = (Encounter) r;
//                        
//                        break;
//                    case Observation:
//                        Observation observation = (Observation) r;
//                        break;
//                }
//            }
//        }
//    }
//
//    
//}
