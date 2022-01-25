package com.ets.service;

import com.ets.dao.EncounterRepository;
import com.ets.domain.Encounter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yusufakhond
 */
@Service("encounterService")
public class EncounterService {
    
    @Autowired
    private EncounterRepository encounterRepository;
    
    public List<Encounter> save(List<Encounter> encounters) {
        encounters = encounterRepository.saveAll(encounters);
        return encounters;
    }
}
