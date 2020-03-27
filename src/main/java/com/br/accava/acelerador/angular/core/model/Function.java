package com.br.accava.acelerador.angular.core.model;

import com.br.accava.acelerador.angular.core.model.languageInfo.ModifierInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Function {
    private String name;
    private String returnType;
    private ModifierInfo modifier;
    private String variables;
    private String content;
    private List<Method> methods;
    private String languageType;

}
