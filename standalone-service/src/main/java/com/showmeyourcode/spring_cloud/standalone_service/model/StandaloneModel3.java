package com.showmeyourcode.spring_cloud.standalone_service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel(description = "Representation of StandaloneModel3 tracked by the application.")
public class StandaloneModel3 implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.", example = "1", required = true)
    private String standalone3Property1;
    @ApiModelProperty(notes = "standalone3Property2. Non-negative integer", example = "42", position = 1)
    private Integer standalone3Property2;
}
