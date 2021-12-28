package com.app.teluspruebatecnica;




import com.app.teluspruebatecnica.model.Donation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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

    // we can use this method to get users returning just two specific fields
    @GetMapping("/users")
    public List<Object[]> getDonations() {
       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
       CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
       Root<User> root = query.from(User.class);

        query.multiselect(
                root.get("user").get("firstName"),
                root.get("user").get("lastName")
                );
        //we create an object of type List<Object[]> and we pass the query to it to avoid the error lack of constructor
        List<Object[]> data= entityManager.createQuery(query).getResultList();
        return data;
    }

}
