package com.br.accava.acelerador.angular.core.systemvariables;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
public enum RegexVariables {

        FIND_ANY_FUNCTION("^[ \\t]*($modifiers$)?(?: ?)($functions$)(?: *)([a-zA-Z_]\\w*) *(?:\\()([^)]*)(?:\\))(?: As )?(\\w*)?([\\s\\S]*?)^[ \\t]*(?:$end_functions$)"),
        FIND_FUNCTION("(?:\\b{{type}}\\b) +(\\w+) *\\(([^\\)]*)\\)"),
        FIND_FUNCTION_NAME(""),
        FIND_FUNCTION_VARIABLES_GROUP("\\(([^\\)]*)\\)"),
        FIND_FUNCTION_VARIABLE(""),
        FIND_VARIABLE(""),
        FIND_VARIABLE_TYPE(""),
        FIND_VARIABLE_GENERIC(""),
        FIND_METHOD(""),
        FIND_METHOD_NAME(""),
        FIND_METHOD_VARIABLES_GROUP(""),
        FIND_METHOD_VARIABLE("(\\w+)"),
        FIND_FILES(""),
        FIND_PAGES("");


    private String item;

    public String getItem(String modifiers, List<String> functions, String endFunctions) {
        System.out.println("End functions " + endFunctions);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> newEndFuncions = new ArrayList<>();
        functions.forEach(value -> {
            value = endFunctions.replace("$function$",value);
            newEndFuncions.add(value);
            System.out.println("Function: " + value);
        });

        return item.replace("$modifiers$",modifiers).
                replace("$functions$",String.join("|",functions)).replace("$end_functions$",String.join("|",newEndFuncions));
    }
}
