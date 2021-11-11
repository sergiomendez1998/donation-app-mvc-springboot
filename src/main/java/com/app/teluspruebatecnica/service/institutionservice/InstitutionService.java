package com.app.teluspruebatecnica.service.institutionservice;

import com.app.teluspruebatecnica.model.Country;
import com.app.teluspruebatecnica.model.Institution;

import java.util.List;

public interface InstitutionService {
    public List<Institution> getInstitutions();
    public void addInstitutions(Institution institution);
    Institution findInstitution(Institution institution);
}
