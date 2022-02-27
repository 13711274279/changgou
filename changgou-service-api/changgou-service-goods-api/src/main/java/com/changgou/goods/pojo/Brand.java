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
@Table(name="wemall_goodsbrand")//指定映射关系到表名为wemall_goodsbrand
public class Brand implements Serializable{

	@ApiModelProperty(value = "品牌id",required = false)
	@Id//注解标识该字段为主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //主键的生成策略GenerationType.IDENTITY标识自增
	@Column(name = "id")
	private Integer id;//品牌id

	@ApiModelProperty
	@Column(name = "addTime")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date addTime;

	@ApiModelProperty(value = "添加添加时间", required = false)
	@Column(name = "deleteStatus")
	private Integer deleteStatus;

	@ApiModelProperty(value = "审计", required = false)
	@Column(name = "audit")
	private Integer audit;

	@ApiModelProperty(value = "品牌名称")
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "推荐分数",required = false)
	@Column(name = "recommend")
	private Integer recommend;

	@ApiModelProperty(value = "排名",required = false)
	@Column(name = "sequence")
	private Integer sequence;

	@ApiModelProperty(value = "品牌图片地址")
    @Column(name = "brandLogo_id")
	private String brandLogo_id;

	@ApiModelProperty(value = "类别id")
	@Column(name = "category_id")
	private Integer category_id;

	@ApiModelProperty(value = "评论")
	@Column(name = "remark")
	private String remark;

	@ApiModelProperty(value = "状态")
	@Column(name = "userStatus")
	private Integer userStatus;

	@ApiModelProperty(value = "用户ID")
	@Column(name = "user_id")
	private Integer user_id;

	@ApiModelProperty(value = "微店")
	@Column(name = "weixin_shop_recommend")
	private Integer weixin_shop_recommend;

	@ApiModelProperty(value = "微店创建时间")
	@Column(name = "weixin_shop_recommendTime")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date weixin_shop_recommendTime;

	@ApiModelProperty(value = "品牌的首字母",required = false)
    @Column(name = "first_word")
	private String first_word;//品牌的首字母

}
