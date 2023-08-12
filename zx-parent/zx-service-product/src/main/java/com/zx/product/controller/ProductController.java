package com.zx.product.controller;

import com.zx.comment.pojo.Products;
import com.zx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("select/{id}")
    public Products selectById(@PathVariable Integer id){

        return productService.findById(id);
    }
}
