package com.movie.api.model.vo;

import com.movie.api.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUserVO implements Serializable {

    private User user;

    private Integer Number;

}
