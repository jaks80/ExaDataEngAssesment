/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ets.ws;

import com.ets.domain.Observation;
import com.ets.service.ObservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yusufakhond
 */
@RestController
@RequestMapping("/obs")
public class ObservationWS {
    
    @Autowired
    private ObservationService observationService;
    
    /*
     Just a sample report to see all observations
    */
    @RequestMapping("/getall")
    public List<String> get() {
        System.out.println("Observation.....");
         List<Observation> list = observationService.findWithChidren();
        return observationService.observationReport(list);       
    }
}
