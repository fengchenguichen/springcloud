package com.zx.page.feign;

import com.zx.comment.pojo.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zx-service-product", fallback = ProductFallBack.class)
public interface ProductFeign {

    @GetMapping("/product/select/{id}")
    public Products selectById(@PathVariable Integer id);

    @GetMapping("/server/select")
    public String selectServerPort();
}
