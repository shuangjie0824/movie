package com.movie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Order;
import com.movie.api.model.vo.ArrangementVO;

import com.movie.mapper.ArrangementMapper;
import com.movie.mapper.FilmMapper;
import com.movie.mapper.OrderMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = ArrangementService.class,version = "1.0")
public class ArrangementServiceImpl implements ArrangementService {

    @Resource
    private ArrangementMapper arrangementMapper;

    @Resource
    private FilmMapper filmMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void save(Arrangement arrangement) {
        arrangement.setBoxOffice(0);
        arrangement.setCreateAt(DataTimeUtil.getNowTimeString());
        arrangementMapper.insert(arrangement);
    }

    @Override
    public List<Arrangement> findAll() {
        return arrangementMapper.selectList(null);
    }

    @Override
    public ArrangementVO findByFilmId(String fid) {
        List<Arrangement> list = arrangementMapper.selectList(new QueryWrapper<Arrangement>().in("fid", fid));
        return new ArrangementVO(list, filmMapper.selectById(fid));
    }

    @Override
    public List<Integer> getSeatsHaveSelected(String id) {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().in("aid", id));
        List<Integer> seats = new ArrayList<>();
        for (Order o : orders) {
            String[] split = o.getSeats().split("号");
            for (String s : split) {
                seats.add(Integer.parseInt(s));
            }
        }
        return seats;
    }

    @Override
    public Arrangement findById(String id) {
        return arrangementMapper.selectById(id);
    }

    @Override
    public void deleteById(String id) {
        arrangementMapper.deleteById(id);
    }

    @Override
    public Arrangement Update(Arrangement arrangement) {
        arrangementMapper.updateById(arrangement);
        return arrangement;
    }

}
