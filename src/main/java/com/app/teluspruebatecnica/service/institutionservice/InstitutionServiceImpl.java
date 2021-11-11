package com.app.teluspruebatecnica.service.institutionservice;

import com.app.teluspruebatecnica.dao.InstitutionDAO;
import com.app.teluspruebatecnica.model.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {


    @Autowired
    private InstitutionDAO institutionDAO;

    //list all the institutions
    @Override
    public List<Institution> getInstitutions() {
        return (List<Institution>) institutionDAO.findAll();
    }

    //save the institution in case we want to create a new one
    @Override
    public void addInstitutions(Institution institution) {
          institutionDAO.save(institution);
    }



    //find the institution by id
    @Override
    public Institution findInstitution(Institution institution) {
        return institutionDAO.findById(institution.getInstitutionId()).orElse(null);
    }
}
