package com.check.point.service.auth.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTableResponse<T> {

    private List<T> data;
    private Integer draw;
    private Integer recordFiltered, recordsTotal;

}
