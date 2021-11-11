package com.app.teluspruebatecnica;




import com.app.teluspruebatecnica.model.Donation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestServiceController {



    @Autowired
    private EntityManager entityManager;

    // we can use this method to get all the donations showing just some specific fields
    @GetMapping("/donations")
    public List<Object[]> getDonations() {
       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       Root<Donation> root = query.from(Donation.class);

        query.multiselect(
                root.get("user").get("firstName"),
                root.get("user").get("lastName"),
                root.get("user").get("email"),
                root.get("user").get("idDocument"),
                root.get("country").get("name"),
                root.get("institution").get("name"),
                root.get("donationAmount"),
                root.get("dateOfDonation")
                );
        //we create an object of type List<Object[]> and we pass the query to it to avoid the error lack of constructor
        List<Object[]> data= entityManager.createQuery(query).getResultList();
        return data;

    }

}
