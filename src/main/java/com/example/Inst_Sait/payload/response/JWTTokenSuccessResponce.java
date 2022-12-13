package com.example.Inst_Sait.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponce {
    private boolean success;
    private String token;
}
