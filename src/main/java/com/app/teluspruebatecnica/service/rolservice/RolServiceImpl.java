package com.app.teluspruebatecnica.service.rolservice;

import com.app.teluspruebatecnica.dao.RolDAO;
import com.app.teluspruebatecnica.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDAO rolDAO;

    //we save the new rol once the user has been created
    @Override
    @Transactional
    public void addNewRole(Rol rol) {
        rolDAO.save(rol);
    }
}
