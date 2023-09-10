package com.JWT.JsonWebToken.Utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceForeingKey {
    private String tableName;
    private String columnName;
}
