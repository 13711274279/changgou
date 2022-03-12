package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Category",value = "Category")
@Table(name="tb_category")//
public class Category implements Serializable{

    @ApiModelProperty(value = "分类id",required = false)
    @Id//注解标识该字段为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键的生成策略GenerationType.IDENTITY标识自增
    @Column(name = "id")
    private Integer id;// 分类ID

    @Column(name = "name")
    private String name; //商品名字

    @Column(name = "goods_num")
    private int goods_num; //商品数量

    @Column(name = "is_show")
    private char is_show; //是否显示

    @Column(name = "is_menu")
    private char is_menu;  //是否导航

    @Column(name = "seq")
    private int seq; //排序

    @Column(name = "parent_id")
    private int parent_id; //

    @Column(name = "template_id")
    private int template_id; //模板ID

}
