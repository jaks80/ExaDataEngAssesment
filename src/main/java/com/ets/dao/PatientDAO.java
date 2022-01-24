package com.ets.dao;

import com.ets.domain.Patient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yusufakhond
 */
public class PatientDAO implements GenericDAO<Patient, Serializable>{

    @Override
    public void save(Patient entity) {
        String INSERT_USERS_SQL = "INSERT INTO users (id, name, email, country, password) VALUES(?, ?, ?, ?, ?)";
    }

    @Override
    public void saveBulk(List<Patient> entityList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List findAll(Class clazz) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Patient findByID(Class clazz, Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
