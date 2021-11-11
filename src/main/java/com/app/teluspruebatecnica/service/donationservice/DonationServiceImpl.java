package com.app.teluspruebatecnica.service.donationservice;

import com.app.teluspruebatecnica.dao.DonationDAO;
import com.app.teluspruebatecnica.model.Country;
import com.app.teluspruebatecnica.model.Donation;
import com.app.teluspruebatecnica.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationDAO donationDAO;

    @Autowired
    private EntityManager entityManager;

//    get the list of donations by user
    @Override
    @Transactional(readOnly = true)
    public List<Donation> getDonationsByUser(Users user) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Donation> query = builder.createQuery(Donation.class);
        Root<Donation> root = query.from(Donation.class);

        query.where(builder.equal(root.get("user"), user));

        return this.entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public void saveDonation(Donation donation) {
        donationDAO.save(donation);

    }

    //get the donation per country to control not more than one donation per country in a month
    @Override
    @Transactional(readOnly = true)
    public Donation getDonationByCountry(Country country) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Donation> query = builder.createQuery(Donation.class);
        Root<Donation> root = query.from(Donation.class);
        query.where(builder.equal(root.get("country"), country));
        try {
            return this.entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

}
