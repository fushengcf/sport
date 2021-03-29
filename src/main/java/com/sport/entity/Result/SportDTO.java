package com.sport.entity.Result;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;

@Data
public class SportDTO {
    private static final long serialVersionUID = -1840831686851699943L;
    private String id;
    private String name;
    private String type;
    private String venue;
    private Integer status;
    private String price;
    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
