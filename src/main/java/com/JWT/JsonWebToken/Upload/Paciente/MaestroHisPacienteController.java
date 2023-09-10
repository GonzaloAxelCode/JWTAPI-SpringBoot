package com.JWT.JsonWebToken.Upload.Paciente;

import com.JWT.JsonWebToken.Services.CSVService;
import com.JWT.JsonWebToken.Services.CsvUploadService;
import com.JWT.JsonWebToken.Upload.Personal.MaestroHisPersonal;
import com.JWT.JsonWebToken.Upload.Personal.MaestroHisPersonalRepository;
import com.JWT.JsonWebToken.Utils.CustomExeptions.MyCustomIllegalArgumentException;
import com.JWT.JsonWebToken.Utils.ModelColumnExtractor;
import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.JWT.JsonWebToken.Utils.Responses.SuccessResponse;
import com.JWT.JsonWebToken.Utils.models.CsvUploadConfig;
import com.JWT.JsonWebToken.Utils.models.ItemRow;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MaestroHisPacienteController {
    private final MaestroHisPacienteRepository repository;
    private final CsvUploadService csvUploadService;
    private final JdbcTemplate jdbcTemplate;
    private final String name_table = "maestro_his_paciente";
    private final ModelColumnExtractor columnExtractor;
    private  final CSVService csvService;

    @PostMapping("/upload-all-" + name_table)
    public ResponseEntity<Object> importCSV(@RequestParam("csv_file") MultipartFile file, @RequestParam String delimiter, @RequestParam String encoding) {

        Class<?> entityClass = MaestroHisPaciente.class;
        ItemRow[] itemRows = columnExtractor.extractColumnsAndTypes(entityClass);

               try {
            CsvUploadConfig config = new CsvUploadConfig();

            config.setTableName(name_table);

            config.setSetForeingKeys(new ReferenceForeingKey[]{
                    new ReferenceForeingKey("maestro_tipo_doc", "id_tipo_documento"),
                    new ReferenceForeingKey("maestro_his_etnia", "id_etnia"),
                    new ReferenceForeingKey("maestro_his_pais", "id_pais"),
                    new ReferenceForeingKey("maestro_his_establecimiento", "id_establecimiento"),
                    new ReferenceForeingKey("maestro_his_ubigeo_inei_reniec", "ubigeo_reniec"),
                    new ReferenceForeingKey("maestro_his_ubigeo_inei_reniec", "ubigeo_declarado"),
            });

            config.setColumnNames(new String[]{
                    "id_paciente",
                    "id_tipo_documento",
                    "numero_documento",
                    "apellido_paterno_paciente",
                    "apellido_materno_paciente",
                    "nombres_paciente",
                    "fecha_nacimiento",
                    "genero",
                    "id_etnia",
                    "historia_clinica",
                    "ficha_familiar",
                    "ubigeo_nacimiento",
                    "ubigeo_reniec",
                    "domicilio_reniec",
                    "ubigeo_declarado",
                    "domicilio_declarado",
                    "referencia_domicilio",
                    "id_pais",
                    "id_establecimiento",
                    "fecha_alta",
                    "fecha_modificacion"
            });


                        config.setColumnTypes(new String[]{
                    "string",
                    "int",  // Assuming id_tipo_documento is a string
                    "string",
                    "string",
                    "string",
                    "string",
                    "string",
                    "string",
                    "string",  // Assuming id_etnia is a numeric ID
                    "string",
                    "string",
                    "int",
                    "int",  // Assuming ubigeo_reniec is an integer
                    "string",
                    "int",  // Assuming ubigeo_declarado is an integer
                    "int",
                    "int",
                    "string",// Assuming id_pais is a string
                    "string", // Assuming id_establecimiento is a string
                    "string",
                    "string",

            });

            //csvUploadService.uploadCsv(file, config,delimiter,encoding);
            csvService.uploadCsv(file,itemRows ,delimiter,encoding,name_table);




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
    public ResponseEntity<Page<MaestroHisPaciente>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MaestroHisPaciente> ubigeosPage = repository.findAll(pageable);
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
