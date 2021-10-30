package com.showmeyourcode.spring_cloud.standalone_service.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class StandaloneModel2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String standalone2Property1;
    private Integer standalone2Property2;
}
