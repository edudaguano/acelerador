package com.br.accava.acelerador.angular.core.systemvariables;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateVariables {

    APP_NAME("{$name$}"),
    CLASS_NAME("{$name_FirstLetterUp$}"),
    FUNCTIONS("$functions$"),
    GLOBAL_VARIABLES("{$global_variables$}");

    private String item;
}
