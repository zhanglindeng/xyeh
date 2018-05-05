package com.dengzhanglin.xyeh.repository;

import com.dengzhanglin.xyeh.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsRepository {
    private GoodsMapper goodsMapper;

    @Autowired
    public GoodsRepository(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }
}
