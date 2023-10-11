package com.movie.api.model.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    private String username;

    private String password;

    private boolean remember;

}