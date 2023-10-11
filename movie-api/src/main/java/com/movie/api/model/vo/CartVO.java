package com.movie.api.model.vo;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Cart;
import com.movie.api.model.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 购物车前端展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVO implements Serializable {

    private Film film;

    private Arrangement arrangement;

    private Cart cart;

}
