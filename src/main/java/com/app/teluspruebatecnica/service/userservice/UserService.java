package com.app.teluspruebatecnica.service.userservice;

import com.app.teluspruebatecnica.model.Users;

import java.util.List;


public interface UserService {


    public List<Users> getUsers();
    public void addNewUser(Users user );
    Users getUserByidDocument(int idDocument);
    public void removeUser(Users user);



}
