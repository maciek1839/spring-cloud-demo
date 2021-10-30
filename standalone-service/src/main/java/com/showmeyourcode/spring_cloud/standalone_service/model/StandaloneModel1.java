package com.showmeyourcode.spring_cloud.standalone_service.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class StandaloneModel1 implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String standalonePropertyId;
    @Min(0)
    @Max(100)
    private long standaloneProperty1;
    @NotBlank
    @Size(min = 1, max = 20)
    private String standaloneProperty2;
    private List<StandaloneModel2> standaloneList;
}
