package com.achievement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com-achievement-entity-Gradle")
@Data
public class Gradle {
    @ApiModelProperty(value="")
    private String id;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Date createTime;
}