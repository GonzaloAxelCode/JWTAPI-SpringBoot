package com.JWT.JsonWebToken.Services;

import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.models.CsvUploadConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CsvUploadService {
    private final JdbcTemplate jdbcTemplate;


     private ResponseEntity<ErrorResponse> verifyColumnNames(CSVParser csvParser, CsvUploadConfig config) throws IllegalArgumentException {
        String[] expectedColumnNames = config.getColumnNames();
        String[] actualColumnNames = csvParser.getHeaderMap().keySet().toArray(new String[0]);

         for (int i = 0; i < actualColumnNames.length; i++) {
             actualColumnNames[i] = actualColumnNames[i].toLowerCase();
         }


         Map<String, String> obj = new HashMap<>();
         obj.put("columnas_esperadas", Arrays.toString(expectedColumnNames));
         obj.put("columnas_recibidas", Arrays.toString(actualColumnNames));
         if (!Arrays.equals(expectedColumnNames, actualColumnNames)) {
             throw new MyCustomIllegalArgumentException("Los nombres de las columnas en el archivo CSV no coinciden con la configuración.",  obj);
         }
         return null;
    }


    @Transactional
    public void uploadCsv(MultipartFile file, CsvUploadConfig config, String delimiter) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String sql = generateSql(config);
            System.out.println(sql);
            CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(delimiter.charAt(0)).withFirstRecordAsHeader().parse(reader);

            verifyColumnNames(csvParser, config);


            jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);
            PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);

            int batchSize = 1000;
            int count = 0;

            for (CSVRecord record : csvParser) {
                setPreparedStatementValues(preparedStatement, record, config.getColumnTypes());
                preparedStatement.addBatch();

                if (++count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            preparedStatement.executeBatch();
            jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
        }
    }


    private String generateSql(CsvUploadConfig config) {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(config.getTableName()).append(" (");

        for (String columnName : config.getColumnNames()) {
            sql.append(columnName).append(", ");
        }

        sql.setLength(sql.length() - 2); // Eliminar la coma final
        sql.append(") VALUES (");

        for (int i = 0; i < config.getColumnNames().length; i++) {
            sql.append("?, ");
        }

        sql.setLength(sql.length() - 2); // Eliminar la coma final
        sql.append(")");

        return sql.toString();
    }


    private void setPreparedStatementValues(PreparedStatement preparedStatement, CSVRecord record, String[] columnTypes) throws SQLException {
        for (int i = 0; i < columnTypes.length; i++) {
            String columnType = columnTypes[i];
            String columnValue = record.get(i);

            if ("int".equalsIgnoreCase(columnType)) {
                preparedStatement.setInt(i + 1, Integer.parseInt(columnValue));
            } else if ("long".equalsIgnoreCase(columnType)) {
                preparedStatement.setLong(i + 1, Long.parseLong(columnValue));
            } else if ("double".equalsIgnoreCase(columnType)) {
                preparedStatement.setDouble(i + 1, Double.parseDouble(columnValue));
            } else {
                preparedStatement.setString(i + 1, columnValue);
            }
        }
    }



}
