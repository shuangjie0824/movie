package com.movie.api.model.pojo;

import com.movie.api.model.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateRolesDto implements Serializable {

    private String uid;

    private List<Role> roles;

}
