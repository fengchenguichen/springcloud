package com.zx.comment.pojo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Products)实体类
 *
 * @author makejava
 * @since 2023-07-18 22:21:37
 */
@Data
@Table(name = "products")
public class Products implements Serializable {
    private static final long serialVersionUID = -96158321346222704L;
    
    private Integer id;
    
    private String name;
    
    private Object price;
    
    private String flag;
    
    private String goodsDesc;
    
    private String images;
    
    private Integer goodsStock;
    
    private String goodsType;



}

