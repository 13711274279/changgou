package com.changgou.goods.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:admin
 * @Description:Brand构建
 * @Date 2019/6/14 19:13
 *****/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Brand",value = "Brand")
@Table(name="tb_brand")//指定映射关系到表名为wemall_goodsbrand
public class Brand implements Serializable{

	@ApiModelProperty(value = "品牌id",required = false)
	@Id//注解标识该字段为主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //主键的生成策略GenerationType.IDENTITY标识自增
	@Column(name = "id")
	private Integer id;//品牌id

	@ApiModelProperty(value = "品牌名称")
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "品牌图片地址")
	@Column(name = "image")
	private String image;

	@ApiModelProperty(value = "品牌的首字母",required = false)
    @Column(name = "letter")
	private String letter;//品牌的首字母

	@ApiModelProperty(value = "品牌的首字母",required = false)
	@Column(name = "seq")
	private int seq;//品牌的首字母
}
