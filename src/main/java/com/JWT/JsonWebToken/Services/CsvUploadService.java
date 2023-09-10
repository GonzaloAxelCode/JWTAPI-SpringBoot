package com.JWT.JsonWebToken.Services;

import com.JWT.JsonWebToken.Utils.models.ItemRow;
import com.JWT.JsonWebToken.Utils.models.ReferenceForeingKey;
import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.models.CsvUploadConfig;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CsvUploadService {
    private final JdbcTemplate jdbcTemplate;


    private ResponseEntity<ErrorResponse> verifyColumnNames(CSVParser csvParser, CsvUploadConfig config) throws IllegalArgumentException {
        String[] expectedColumnNames = config.getColumnNames();
        String[] actualColumnNames = csvParser.getHeaderMap().keySet().toArray(new String[0]);

        // Convierte los arrays en listas y cambia a minúsculas
        List<String> expectedColumnsList = Arrays.stream(expectedColumnNames)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        List<String> actualColumnsList = Arrays.stream(actualColumnNames)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        // Ordena las listas (opcional, dependiendo de tus necesidades)
        Collections.sort(expectedColumnsList);
        Collections.sort(actualColumnsList);

        // Convierte las listas ordenadas nuevamente en arrays (si es necesario)
        expectedColumnNames = expectedColumnsList.toArray(new String[0]);
        actualColumnNames = actualColumnsList.toArray(new String[0]);

        System.out.println("Columnas Esperadas: " + Arrays.toString(expectedColumnNames));
        System.out.println("Columnas Recibidas: " + Arrays.toString(actualColumnNames));

        Map<String, String> obj = new HashMap<>();
        obj.put("columnas_esperadas", Arrays.toString(expectedColumnNames));
        obj.put("columnas_recibidas", Arrays.toString(actualColumnNames));

        // Compara las listas ordenadas
        if (!Arrays.equals(expectedColumnNames, actualColumnNames)) {
            throw new MyCustomIllegalArgumentException("Los nombres de las columnas en el archivo CSV no coinciden con la configuración.", obj);
        }

        return null;
    }


    @Transactional
    public void uploadCsv(MultipartFile file, CsvUploadConfig config, String delimiter,String encoding) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(),encoding))) {

                CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(delimiter.charAt(0)).withFirstRecordAsHeader().parse(reader);
                verifyColumnNames(csvParser, config);
                jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);

                String sql = generateSql(config);
                PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);

                int batchSize = 1000;
                int count = 0;
                Set<String> valoresUnicos = new HashSet<>();
                for (CSVRecord record : csvParser) {

                    if(record.isSet("Id_Personal")){
                        if (valoresUnicos.contains(record.get("Id_Personal"))) {
                            continue;
                        }
                        valoresUnicos.add(record.get("Id_Personal"));
                    }
                    setPreparedStatementValues(preparedStatement, record, config.getColumnTypes(), config);
                    preparedStatement.addBatch();

                    if (++count % batchSize == 0) {
                        preparedStatement.executeBatch();
                    }
                }

                preparedStatement.executeBatch();
                jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);

        }
    }




    @Transactional
    public void uploadCsv(MultipartFile file, CsvUploadConfig config, String delimiter) throws IOException, SQLException {
        uploadCsv(file,config,delimiter,"UTF-8");
    }

    @Transactional

    public void uploadCsvV2(MultipartFile file, ItemRow[] itemRows, String delimiter, String encoding,String nameTable) throws IOException, SQLException {
        String SQL = generateInsertSql(itemRows,nameTable);



    }


    private String generateInsertSql(ItemRow[] itemRows, String tableName) {
        // Construir la consulta SQL INSERT
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append(" (");

        for (ItemRow itemRow : itemRows) {
            sql.append(itemRow.getColumnName()).append(",");
        }

        // Eliminar la última coma
        sql.deleteCharAt(sql.length() - 1);

        sql.append(") VALUES (");

        for (int i = 0; i < itemRows.length; i++) {
            sql.append("?,");
        }

        // Eliminar la última coma
        sql.deleteCharAt(sql.length() - 1);

        sql.append(")");

        return sql.toString();
    }

    private String generateSql(CsvUploadConfig config) {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(config.getTableName()).append(" (");

        for (String columnName : config.getColumnNames()) {
            sql.append(columnName).append(", ");
        }

        sql.setLength(sql.length() - 2);
        sql.append(") VALUES (");

        for (int i = 0; i < config.getColumnNames().length; i++) {
            sql.append("?, ");
        }

        sql.setLength(sql.length() - 2); // Eliminar la coma final
        sql.append(")");

        return sql.toString();
    }
    @Transactional
    private PreparedStatement setPreparedStatementValues(PreparedStatement preparedStatement, CSVRecord record, String[] columnTypes,  CsvUploadConfig config) throws SQLException {
        for (int i = 0; i < columnTypes.length; i++) {
            String columnType = columnTypes[i];
            String columnName = config.getColumnNames()[i];
            String columnValue = record.get(i);
            if (columnValue == null || columnValue.isEmpty()) {
                preparedStatement.setNull(i+1, java.sql.Types.NULL);
                continue;
            }
            if(config.getSetForeingKeys() != null){
                for(ReferenceForeingKey foreingKey: config.getSetForeingKeys()){

                    if(Objects.equals(foreingKey.getColumnName(), columnName)){
                        //setPreparedStatementColumnValue(preparedStatement, i + 1, columnType, columnValue);
                        continue;
                    }else{
                        setPreparedStatementColumnValue(preparedStatement, i + 1, columnType, columnValue);
                    }
                }

            }else{
                setPreparedStatementColumnValue(preparedStatement, i + 1, columnType, columnValue);

            }
        }
        return  preparedStatement;
    }

    private PreparedStatement setPreparedStatementColumnValue(PreparedStatement preparedStatement, int columnIndex, String columnType, String columnValue) throws SQLException {



        if (columnValue == null || columnValue.isEmpty()) {
            preparedStatement.setNull(columnIndex, java.sql.Types.NULL);
        } else {
            if ("int".equalsIgnoreCase(columnType)) {

                try {
                    Integer valorEntero = Integer.parseInt(columnValue);
                    preparedStatement.setInt(columnIndex, Integer.parseInt(columnValue));
                } catch (NumberFormatException e) {
                    preparedStatement.setNull(columnIndex, java.sql.Types.NULL);
                }

            } else if ("long".equalsIgnoreCase(columnType)) {

                try {
                    long valorEntero = Long.parseLong(columnValue);
                    preparedStatement.setLong(columnIndex, Long.parseLong(columnValue));
                } catch (NumberFormatException e) {
                    preparedStatement.setNull(columnIndex, java.sql.Types.NULL);
                }
            } else if ("double".equalsIgnoreCase(columnType)) {
                try {
                    Double valorEntero = Double.parseDouble(columnValue);
                    preparedStatement.setInt(columnIndex, Integer.parseInt(columnValue));
                } catch (NumberFormatException e) {
                    preparedStatement.setNull(columnIndex, java.sql.Types.NULL);
                }
                preparedStatement.setDouble(columnIndex, Double.parseDouble(columnValue));
            } else {
                preparedStatement.setString(columnIndex,columnValue );
            }
        }
        return  preparedStatement;
    }

    private boolean checkIfKeyExists(String tableName, String keyName, String keyValue,String columnType) {

        try {
            if(Objects.equals(keyName, "ubigeo_reniec")){

                keyName = "id_ubigueo_reniec";


            }
            if(Objects.equals(keyName, "ubigeo_declarado")){

                keyName ="id_ubigueo_inei";

            }

            String sql;
            if("int".equalsIgnoreCase(columnType) || "long".equalsIgnoreCase(columnType) ){
                try {
                    Long valorEntero = Long.parseLong(keyValue);
                    sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + keyName + " = CAST(? AS bigint)";

                } catch (NumberFormatException e) {
                    return  false;
                }

            } else {
                 sql= "SELECT COUNT(*) FROM " + tableName + " WHERE " + keyName + " = ?";
            }

            int count = jdbcTemplate.queryForObject(sql, Integer.class, keyValue);

            if (keyValue.equals(String.valueOf(140137))){
                System.out.println(count);
            }

            return count == 0;
        } catch (DataAccessException e) {

            e.printStackTrace();
            return false;
        }
    }



}
