package com.achievement.entity.Result;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;

@Data
public class HouseMasterDTO {
    private String id;
    private String name;
    private String password;
    private String buildingId;
    private String building;
    private String phone;
    @TableField(fill = INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
//    private Date lastUpdateTime;

    public static final String NAME = "name";
}
