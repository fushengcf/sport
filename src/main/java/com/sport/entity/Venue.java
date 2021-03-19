package com.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;

@Data
@TableName(value = "venue")
public class Venue {
    private String id;

    /**
    * 名称
    */
    private String name;
    private String lat;
    private String lng;
    private String phone;
    private String address;
    private String password;
    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public static final String NAME = "name";
    public static final String PHONE = "phone";
}