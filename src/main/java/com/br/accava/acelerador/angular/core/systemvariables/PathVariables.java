package com.br.accava.acelerador.angular.core.systemvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PathVariables {
    ZIP_PATH("src/main/resources/projeto-exemplo.zip"),
    PAGE_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\app\\pages"),
    CSS_FILES_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\assets\\styles"),
    IMAGE_FILES_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\assets\\images"),
    MENU_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\app\\menu"),
    SERVICE_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\app\\services"),
    MODEL_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\app\\models"),
    SCRIPT_FOLDER("src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\assets\\scripts");


    private String item;
}
