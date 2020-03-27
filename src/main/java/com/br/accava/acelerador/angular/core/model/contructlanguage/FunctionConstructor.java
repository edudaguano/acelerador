package com.br.accava.acelerador.angular.core.model.contructlanguage;

import com.br.accava.acelerador.angular.core.model.Function;

public class FunctionConstructor {

    private String function;
    private Function functionObject;




    @Override
    public String toString() {
        return functionObject.getModifier() + " " + functionObject.getName()
                .replace(String.valueOf(functionObject.getName().charAt(0)),
                        String.valueOf(functionObject.getName().charAt(0)).toLowerCase()) +
                "(" + functionObject.getVariables() + ") {/*" + functionObject.getContent() + "*/}";
    }
}
