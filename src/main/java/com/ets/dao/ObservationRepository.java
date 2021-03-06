/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ets.dao;

import com.ets.domain.Observation;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yusufakhond
 */
@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    @Override
    @EntityGraph("parent.withChildren")
    public List<Observation> findAll();    
}
