package com.JWT.JsonWebToken.Utils.Responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse {
    private String successMessage;
    private String successDetails;
}
