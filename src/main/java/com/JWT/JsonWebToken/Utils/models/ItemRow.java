package com.JWT.JsonWebToken.Utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRow {
    private String columnName;
    private String columnType;
    private Boolean isForeignKey;
    private Boolean isId;
    private String referencedColumnName;
    private String nameTableReference;
}
