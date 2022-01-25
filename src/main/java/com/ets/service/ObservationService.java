package com.ets.service;

import com.ets.dao.ObservationRepository;
import com.ets.domain.Encounter;
import com.ets.domain.Observation;
import com.ets.domain.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond
 */
@Service("observationService")
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    public List<Observation> saveBulk(List<Observation> observations) {
        observations = observationRepository.saveAll(observations);
        return observations;
    }

    public List<Observation> findWithChidren() {

        List<Observation> list = observationRepository.findAll();
        return list;
    }

    /*
    Generate a basic observation report
    */
    public List<String> observationReport(List<Observation> list) {

        List<String> obs = new ArrayList<>();
        for (Observation o : list) {
            StringJoiner sj = new StringJoiner(" ");
            Patient p = o.getEncounter().getPatient();
            sj.add(p.getFullName() + "(" + p.getDateOfBirth() + ")");
            Encounter e = o.getEncounter();
            sj.add(e.getType() + " " + e.getReasonCode());

            sj.add(o.getCatagory() + " " + o.getConcept() + " " + o.getResult());
            obs.add(sj.toString());
        }
        return obs;
    }
}
