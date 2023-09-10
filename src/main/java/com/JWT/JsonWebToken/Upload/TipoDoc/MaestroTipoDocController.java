package com.JWT.JsonWebToken.Upload.TipoDoc;


import com.JWT.JsonWebToken.Services.CsvUploadService;
import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoRepository;
import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.Responses.SuccessResponse;
import com.JWT.JsonWebToken.Utils.models.CsvUploadConfig;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MaestroTipoDocController {

    private final MaestroTipoDocRepository  repository;
    private final CsvUploadService csvUploadService;
    private final JdbcTemplate jdbcTemplate;
    private final String name_table = "maestro_tipo_doc";

    @PostMapping("/upload-all-" + name_table)
    public ResponseEntity<Object> importCSV(@RequestParam("csv_file") MultipartFile file, @RequestParam String delimiter, @RequestParam String encoding) {

        try {
            CsvUploadConfig config = new CsvUploadConfig();
            config.setTableName(name_table);

            config.setColumnNames(new String[]{

                    "id_tipo_documento",
                    "abrev_tipo_doc",
                    "descripcion_tipo_documento"
            });
            config.setColumnTypes(new String[]{

                    "int",
                    "string",
                    "string"
            });
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
    public ResponseEntity<Page<MaestroTipoDoc>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MaestroTipoDoc> ubigeosPage = repository.findAll(pageable);
        return ResponseEntity.ok(ubigeosPage);
    }


    @DeleteMapping("/delete-all-" + name_table)
    public ResponseEntity<Object> deleteTable() {
        try {

            String sql = "TRUNCATE TABLE " + name_table + " , cita_nominal, maestro_his_personal";

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




    @GetMapping("/export-csv-" + name_table)
    public ResponseEntity<?> exportCSV() {
        try {
            // Aquí debes obtener los datos que deseas exportar desde tu repositorio
            List<MaestroTipoDoc> dataToExport = repository.findAll();

            // Luego, convierte los datos en formato CSV
            String csvData = convertDataToCSV(dataToExport);

            // Convierte el CSV en un arreglo de bytes
            byte[] csvBytes = csvData.getBytes(StandardCharsets.UTF_8);

            // Configura las cabeceras de la respuesta para indicar que se está enviando un archivo CSV
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDispositionFormData("attachment", name_table + ".csv");

            // Devuelve la respuesta con los datos CSV y las cabeceras configuradas
            return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .errorMessage("Error durante la exportación de datos CSV.")
                    .errorDetails(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    private String convertDataToCSV(List<MaestroTipoDoc> data) {


        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("id_tipo_documento,abrev_tipo_doc,descripcion_tipo_documento\n"); // Encabezados

        for (MaestroTipoDoc item : data) {
            csvBuilder.append(item.getIdTipoDocumento()).append(",")
                    .append(item.getAbrevTipoDoc()).append(",")
                    .append(item.getDescripcionTipoDocumento()).append("\n");
        }

        return csvBuilder.toString();
    }

}


