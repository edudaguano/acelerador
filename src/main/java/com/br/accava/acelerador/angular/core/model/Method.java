package com.br.accava.acelerador.angular.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Method {
    private String name;
    private String variables;
    private String returnType;
    private String classMethod;
}
