package com.ets.ws;

import com.ets.ExaDataEngAssesmentApplication;
import com.ets.domain.Encounter;
import com.ets.domain.Observation;
import com.ets.domain.Patient;
import com.ets.resource.ExternalResourceBundleBuilder;
import com.ets.resource.ResourceFactory;
import com.ets.service.EncounterService;
import com.ets.service.ObservationService;
import com.ets.service.PatientService;
import java.io.File;
import java.net.URL;
import java.util.List;
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

    private boolean parsingDone = false;// A poor way to block double parsing and persisting bellow files
    //Files are in local resources folder
    private String[] files = {"Aaron697_Dickens475_8c95253e-8ee8-9ae8-6d40-021d702dc78e.json",
        "Aaron697_Jerde200_6fa23508-960e-ff22-c3d0-0519a036543b.json",
        "Abbey813_Price929_83524678-9bff-93b7-ef89-d7f5390072ff.json",
        "Beth967_Hansen121_4e343b0a-8698-b6dd-64c6-c2d2d0959e6e.json",
        "Bette450_Anderson154_6e4ac285-2a8d-a30d-5ecb-e32cb595a876.json",
        "Bill567_Breitenberg711_1029f880-d3db-f477-9da3-f59c14ed22c6.json",
        "Blanch844_Kuhlman484_a18edb30-e93c-8e9b-8e6a-95a651a24a36.json",
        "Bob965_Mohr916_f406a4e8-821b-0c9a-c8ec-09ad0f1fe9c6.json",
        "Bobbi508_Beer512_b8c71fe0-e911-205e-19c3-b92e88e5b5a6.json",
        "Chase54_Klocko335_b0478b4f-16f2-af38-d199-44db19304df2.json",
        "Chelsie189_Larson43_0f978b87-8054-e6d3-aa03-20e101ea37c0.json",
        "Cherie102_Reinger292_64cdd7b0-d5a5-ca8a-4b03-3db12d7534be.json"};

    @Autowired
    private PatientService patientService;
    @Autowired
    private EncounterService encounterService;
    @Autowired
    private ObservationService observationService;

    @Autowired
    private ResourceFactory factory;

    @RequestMapping("/")
    public List<String> home() {

        if (!parsingDone) {
            for (String s : files) {
                Bundle bundle = new ExternalResourceBundleBuilder()
                        .setFhirContext(ExaDataEngAssesmentApplication.getCtx())
                        .setResourceFileName(s)
                        .build();

                factory.setBundle(bundle);
                Patient patient = factory.getPatient();
                patientService.savePatient(patient);

                List<Encounter> encounters = factory.getEncounters();

                encounters.forEach((e) -> e.setPatient(patient));
                encounterService.save(encounters);

                List<Observation> observations = factory.getObservations();
                observationService.saveBulk(observations);

            }
        }
        parsingDone = true;

        List<Observation> list = observationService.findWithChidren();
        //Return sample report
        return observationService.observationReport(list);
    }

    private File[] getResourceFolderFiles(String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }

}
