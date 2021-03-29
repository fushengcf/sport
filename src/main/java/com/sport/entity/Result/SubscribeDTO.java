package com.sport.entity.Result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SubscribeDTO {
    private static final long serialVersionUID = -1840831686851699943L;
    private String id;
    @JsonFormat(pattern="HH",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="HH",timezone = "GMT+8")
    private Date finishTime;
}
