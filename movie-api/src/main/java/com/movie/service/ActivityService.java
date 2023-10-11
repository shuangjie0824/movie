package com.movie.service;

import com.movie.api.model.entity.Activity;

import java.util.List;

/**
 * @author abc
 * @date 2023/10/08
 */
public interface ActivityService {

    void create(Activity activity);

    Activity findById(String id);

    List<Activity> findAll();

    void deleteById(String id);

}
