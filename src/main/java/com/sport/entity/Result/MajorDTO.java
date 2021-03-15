package com.sport.entity.Result;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;


@Data
public class MajorDTO {
    private String id;
    private String name;
    private String instructor;
    private String phone;
    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
//    public static final Integer HOUSENUMBER =1;
}