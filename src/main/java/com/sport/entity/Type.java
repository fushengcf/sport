package com.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.IdType.UUID;

@Data
@TableName(value = "type")

public class Type {
    private static final long serialVersionUID = -1840831686851699943L;
    @TableId(value = "id",type =UUID)
    private String id;
    private String name;
    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public static final String NAME = "name";
}