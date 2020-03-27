package com.br.accava.acelerador.angular.core.systemvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AngularFileTypesVariables {

    COMPONENT("component"),
    HTML("html"),
    TS("ts"),
    SPEC("spec.ts"),
    MODULE("module.ts"),
    SERVICE("service.ts"),
    SERVICE_SPEC("service.spec.ts"),
    JSON("json"),
    JS("js"),
    CSS("css");


    private String item;

}
