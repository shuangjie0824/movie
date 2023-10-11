package com.movie.service;


import com.movie.api.model.entity.User;
import com.movie.api.model.pojo.LoginDto;

import java.util.List;

public interface UserService {

    User login(LoginDto dto) throws Exception;

    List<User> findAll();

    User findById(String id);

    User update(User user);

    User save(User user) throws Exception;

    User findByUsername(String username);

    void deleteById(String id);

}
