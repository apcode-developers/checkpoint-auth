package com.check.point.service.auth.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTableRequest {

    private Integer draw, start, length, sortCol;
    private Map<String, Object> extraParam;
    private String sortDir;
}
