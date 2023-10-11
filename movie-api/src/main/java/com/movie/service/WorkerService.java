package com.movie.service;


import com.movie.api.model.entity.Worker;
import com.movie.api.model.pojo.LoginDto;

import java.util.List;

public interface WorkerService {

    Worker create(Worker worker) throws Exception;

    Worker login(LoginDto dto) throws Exception;

    void update(Worker worker) throws Exception;

    List<Worker> findAll();

    Worker findById(String id);

    void deleteById(String id);

}
