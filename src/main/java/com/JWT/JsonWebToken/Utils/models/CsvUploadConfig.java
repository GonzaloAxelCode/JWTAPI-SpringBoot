package com.JWT.JsonWebToken.Utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvUploadConfig {

    private String tableName;
    private String[] columnNames;
    private String[] columnTypes;
    private ReferenceForeingKey[] setForeingKeys;
    private ItemRow[] rowsCsv;
}
