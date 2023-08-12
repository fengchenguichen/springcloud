package com.zx.page.feign;

import com.zx.comment.pojo.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductFallBack implements ProductFeign{
    @Override
    public Products selectById(Integer id) {
        return null;
    }

    @Override
    public String selectServerPort() {
        return "-2";
    }
}
