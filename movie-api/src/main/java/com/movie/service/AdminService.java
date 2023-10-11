package com.movie.service;


import com.movie.api.model.pojo.LoginDto;

public interface AdminService {

    String login(LoginDto loginDto) throws Exception;

}
