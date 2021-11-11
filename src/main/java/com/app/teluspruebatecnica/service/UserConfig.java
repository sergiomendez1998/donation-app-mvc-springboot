package com.app.teluspruebatecnica.service;

import com.app.teluspruebatecnica.dao.UserDAO;
import com.app.teluspruebatecnica.model.Rol;
import com.app.teluspruebatecnica.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service("userDetailsService")
@Slf4j
public class UserConfig implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    //find the current user that is trying to login
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {

        Users user = userDAO.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : user.getRolSet()){
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        }
       
        return new User(user.getUsername(), user.getPassword(), roles);
    }


}
