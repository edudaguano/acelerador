package com.br.accava.acelerador.angular.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private String name;
    private String path;
    private String fileType;
    private List<LanguageType> pageLanguages;
    private List<Page> pages;
    private List<Function> functions;
    private List<Method> methods;
    private List<Variable> globalVariables;
    private String htmlContent;

}
