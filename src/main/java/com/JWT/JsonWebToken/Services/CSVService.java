package com.JWT.JsonWebToken.Services;

import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.models.ItemRow;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CSVService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void uploadCsv(MultipartFile file,
                            ItemRow[] itemRows,
                            String delimiter,
                            String encoding,
                            String nameTable) throws IOException, SQLException {
        String SQL = generateInsertSql(itemRows,nameTable);
        System.out.println(SQL);

        String[] csvColumns = getColumnNamesFromCsv(file, delimiter, encoding);

         verifyCorrectColumns(csvColumns, itemRows);
        for (ItemRow itemRow : itemRows) {
            System.out.println(itemRow);
        }

    }

    public ResponseEntity<ErrorResponse> verifyCorrectColumns(String[] csvColumns, ItemRow[] itemsRows) {
        Set<String> csvColumnSet = new HashSet<>(Arrays.asList(csvColumns));
        Set<String> itemRowColumnSet = Arrays.stream(itemsRows)
                .map(ItemRow::getColumnName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Map<String, String> columnNamesMap = new HashMap<>();
        columnNamesMap.put("columnas_esperadas", Arrays.toString(itemsRows));
        columnNamesMap.put("columnas_recibidas", Arrays.toString(csvColumns));
        if (csvColumnSet.equals(itemRowColumnSet)) {
            throw new MyCustomIllegalArgumentException("Los nombres de las columnas en el archivo CSV no coinciden con la configuración.", columnNamesMap);
        }

        return null;
    }


    public String[] getColumnNamesFromCsv(MultipartFile file, String delimiter, String encoding) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), encoding))) {
            CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(delimiter.charAt(0)).withFirstRecordAsHeader().parse(reader);
            return csvParser.getHeaderMap().keySet().toArray(new String[0]);
        }
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
}
