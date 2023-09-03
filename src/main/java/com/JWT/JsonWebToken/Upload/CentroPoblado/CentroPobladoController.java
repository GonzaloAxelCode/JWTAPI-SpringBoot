package com.JWT.JsonWebToken.Upload.CentroPoblado;
import com.JWT.JsonWebToken.Services.CsvUploadService;
import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.Responses.SuccessResponse;
import com.JWT.JsonWebToken.Utils.models.CsvUploadConfig;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CentroPobladoController {
    private final CentroPobladoRepository repository;
    private final CsvUploadService csvUploadService;
    private final JdbcTemplate jdbcTemplate;

    private final String name_table = "maestro_centro_poblado";




    @PostMapping("/upload-all-" + name_table)
    public ResponseEntity<Object> importCSV(@RequestParam("csv_file") MultipartFile file, @RequestParam String delimiter, @RequestParam String encoding) {

        try {
            CsvUploadConfig config = new CsvUploadConfig();
            config.setTableName(name_table);
            config.setColumnNames(new String[]{
                    "id_actividad_his",
                    "descripcion_actividad_his",
                    "fg_estado",

            });
            config.setColumnTypes(new String[]{"string", "string", "int"});

            csvUploadService.uploadCsv(file, config,delimiter);

            SuccessResponse successResponse = SuccessResponse.builder()
                    .successMessage("Importacion Exitosa")
                    .successDetails("CSV de "+ name_table +  " subido a la base de datos con exito")
                    .build();
            return ResponseEntity.ok(successResponse);


        } catch (IOException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .errorMessage("Error al leer el archivo CSV.")
                    .errorDetails(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        } catch (MyCustomIllegalArgumentException e) {

            ErrorResponse errorResponse = ErrorResponse.builder()
                    .errorMessage("Error de verificacion")
                    .errorDetails(e.getMessage())
                    .errorIObjectDetails(e.getCustomArgument())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);


        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .errorMessage("Error durante la importación.")
                    .errorDetails(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/get-all-"+ name_table)
    public ResponseEntity<Page<MaestroCentroPoblado>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MaestroCentroPoblado> ubigeosPage = repository.findAll(pageable);
        return ResponseEntity.ok(ubigeosPage);
    }


    @DeleteMapping("/delete-all-" + name_table)
    public ResponseEntity<Object> deleteTable() {
        try {

            String sql = "TRUNCATE TABLE " + name_table + " , cita_nominal";

            jdbcTemplate.execute(sql);

            SuccessResponse successResponse = SuccessResponse.builder()
                    .successMessage("Tabla eliminada exitosamente")
                    .successDetails("La tabla '" + name_table  + "' ha sido eliminada")
                    .build();

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .errorMessage("Error durante la eliminación de la tabla.")
                    .errorDetails(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
