package com.JWT.JsonWebToken.Utils.Responses;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String errorMessage;
    private String errorDetails;
    private Object errorIObjectDetails;

}
