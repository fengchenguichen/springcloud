package com.zx.product.service.impl;

import com.zx.comment.pojo.Products;
import com.zx.product.mapper.ProductMapper;
import com.zx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Products findById(Integer id) {
        return productMapper.selectById(id);
    }
}
