package com.zx.product.service;

import com.zx.comment.pojo.Products;

public interface ProductService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Products findById(Integer id);
}
