package com.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.IdType.UUID;

@Data
@TableName("teacher")
public class Teacher extends Model<Teacher> implements Serializable {
    private static final long serialVersionUID = -1840831686851699943L;
    @TableId(value = "id",type =UUID)
    private String id;
    private String name;
    private String password;
    private String phone;
    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public static final String NAME = "name";
}