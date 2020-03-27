package com.br.accava.acelerador.angular.core.systemvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileVariables {
    TS_TEMPLATE("src\\main\\resources\\ArquivosModelo\\Angular\\exemplo_ts.ts"),
    SPEC_TS_TEMPLATE("src\\main\\resources\\ArquivosModelo\\Angular\\exemplo_spects.spec.ts"),
    SERVICE_TEMPLATE("src\\main\\resources\\ArquivosModelo\\Angular\\exemplo_service.ts");


    private String item;
}
