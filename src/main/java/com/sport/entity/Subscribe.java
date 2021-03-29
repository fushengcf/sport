package com.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.IdType.UUID;

@Data
@TableName(value = "subscribe")

public class Subscribe {
    private static final long serialVersionUID = -1840831686851699943L;
    @TableId(value = "id",type =UUID)
    private String id;
    private String username;
    private String sportId;
    private String venueId;
    private String typeId;

    private String phone;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date finishTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;
    private String price;


    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public static final String USERNAME = "username";
}