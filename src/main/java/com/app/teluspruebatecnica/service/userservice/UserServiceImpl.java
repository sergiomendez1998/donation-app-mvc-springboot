package com.app.teluspruebatecnica.service.userservice;

import com.app.teluspruebatecnica.dao.UserDAO;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EntityManager entityManager;



    //get all users
    @Override
    @Transactional(readOnly = true)
    public List<Users> getUsers() {
        return (List<Users>) userDAO.findAll();
    }

    //save the user to the database
    @Override
    @Transactional
    public void addNewUser(Users user) {
        userDAO.save(user);
    }

    //get the user by id document if exist
    @Override
    @Transactional(readOnly = true)
    public Users getUserByidDocument(int idDocument) {
       CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> query = builder.createQuery(Users.class);
        Root<Users> root = query.from(Users.class);

        query.select(root).where(builder.equal(root.get("idDocument"), idDocument));
        try {
            return this.entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void removeUser(Users user) {

    }
}
