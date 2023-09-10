package com.JWT.JsonWebToken.Upload.Personal;

import com.JWT.JsonWebToken.Services.CsvUploadService;
import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.Responses.SuccessResponse;
import com.JWT.JsonWebToken.Utils.models.CsvUploadConfig;
import com.JWT.JsonWebToken.Utils.models.ReferenceForeingKey;
import lombok.AllArgsConstructor;
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
public class MaestroHisPersonalController {

    private final MaestroHisPersonalRepository repository;
    private final CsvUploadService csvUploadService;
    private final JdbcTemplate jdbcTemplate;
    private final String name_table = "maestro_his_personal";

    @PostMapping("/upload-all-" + name_table)
    public ResponseEntity<Object> importCSV(@RequestParam("csv_file") MultipartFile file, @RequestParam String delimiter, @RequestParam String encoding) {

        try {
            CsvUploadConfig config = new CsvUploadConfig();

            config.setTableName(name_table);


            config.setSetForeingKeys(new ReferenceForeingKey[]{
                    new ReferenceForeingKey("maestro_his_establecimiento","id_establecimiento"),
                    new ReferenceForeingKey("maestro_tipo_doc","id_tipo_documento"),
                    new ReferenceForeingKey("maestro_condicion_contrato","id_condicion"),
                    new ReferenceForeingKey("maestro_his_profesion","id_profesion"),
                    new ReferenceForeingKey("maestro_his_colegio","id_colegio")
            });


            config.setColumnNames(new String[]{
                    "id_personal",
                    "id_tipo_documento",
                    "numero_documento",
                    "apellido_paterno_personal",
                    "apellido_materno_personal",
                    "nombres_personal",
                    "fecha_nacimiento",
                    "id_condicion",
                    "id_profesion",
                    "id_colegio",
                    "numero_colegiatura",
                    "id_establecimiento",
                    "fecha_alta",
                    "fecha_baja"
            });

            config.setColumnTypes(new String[]{
                    "string",
                    "int",  // Suponiendo que id_tipo_documento es un ID numérico
                    "string",
                    "string",
                    "string",
                    "string",
                    "string",
                    "long",  // Suponiendo que id_condicion es un ID numérico
                    "long",  // Suponiendo que id_profesion es un ID numérico
                    "long",  // Suponiendo que id_colegio es un ID numérico
                    "string",
                    "string", // Suponiendo que id_establecimiento es un ID STRING
                    "string",
                    "string"
            });

            csvUploadService.uploadCsv(file, config,delimiter,encoding);

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
    public ResponseEntity<Page<MaestroHisPersonal>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MaestroHisPersonal> ubigeosPage = repository.findAll(pageable);
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
                    .errorIObjectDetails(e.getCause())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
