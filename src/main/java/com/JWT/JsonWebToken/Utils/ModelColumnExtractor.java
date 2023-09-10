package com.JWT.JsonWebToken.Utils;
import com.JWT.JsonWebToken.Utils.models.ItemRow;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModelColumnExtractor {
    public ItemRow[] extractColumnsAndTypes(Class<?> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        List<ItemRow> itemRows = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(ManyToOne.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation != null ? columnAnnotation.name() : field.getName();
                String columnType = getColumnDataType(field);
                boolean isForeignKey = field.isAnnotationPresent(ManyToOne.class);
                boolean isId = field.isAnnotationPresent(Id.class);

                String referencedColumnName = getReferencedColumnName(field);
                String nameTableReference = getNameTableReference(field, entityClass);

                if (isForeignKey && referencedColumnName != null && nameTableReference != null) {
                    // Si es una clave foránea y se proporciona el nombre de la columna de referencia y de la tabla de referencia, úsalos
                    columnName = referencedColumnName;
                } else {
                    // Si no es clave foránea, convierte el nombre de columna en snake_case
                    columnName = camelCaseToSnakeCase(columnName);
                }

                ItemRow itemRow = new ItemRow(columnName.toLowerCase(), columnType, isForeignKey, isId, referencedColumnName, nameTableReference);

                itemRows.add(itemRow);
            }
        }

        return itemRows.toArray(new ItemRow[0]);
    }

    private String getColumnDataType(Field field) {
        // Obtén el tipo de dato de la columna, considerando si es una columna foránea
        if (field.isAnnotationPresent(ManyToOne.class)) {
            Class<?> referencedEntityClass = field.getType();
            Field[] referencedFields = referencedEntityClass.getDeclaredFields();
            for (Field referencedField : referencedFields) {
                if (referencedField.isAnnotationPresent(Id.class)) {
                    // Si la entidad referenciada tiene un campo con la anotación @Id, obtén su tipo de dato
                    return referencedField.getType().getSimpleName();
                }
            }
        }
        // Si no es una columna foránea o no se encontró un campo con @Id, usa el tipo de dato del campo actual
        return field.getType().getSimpleName();
    }



    private String getReferencedColumnName(Field field) {
        if (field.isAnnotationPresent(ManyToOne.class)) {
            JoinColumn joinColumnAnnotation = field.getAnnotation(JoinColumn.class);
            if (joinColumnAnnotation != null) {
                return joinColumnAnnotation.name(); // Obtén el nombre de la columna de referencia de la anotación @JoinColumn
            }
        }
        return null;
    }

    private String getNameTableReference(Field field, Class<?> entityClass) {
        if (field.isAnnotationPresent(ManyToOne.class)) {

            System.out.println(field.getType().getSimpleName());
            return field.getType().getSimpleName();

        }
        return null;
    }

    private String camelCaseToSnakeCase(String input) {

        return input.replaceAll("([a-z])([A-Z])", "$1_$2");

    }
}