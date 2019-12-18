package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * Description: 实体类
 */
@Data
@ApiModel(value = "请求检测手机号")
//lombok @Accessors生成  set
@Accessors(chain = true)
public class Cheak extends BaseProtocolIn{

   @ApiModelProperty(name = "手机号")
   @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$",message = "手机号不正确")
   @NotBlank(message = "手机号不能为空")
   private String phone;
   @ApiModelProperty(name = "验证码")
   @NotBlank(message = "验证码不能为空")
   private String code;
}
