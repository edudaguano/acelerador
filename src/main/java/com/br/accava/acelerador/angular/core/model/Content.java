package com.br.accava.acelerador.angular.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Content {
    private String type;
    private String text;
    private boolean isLanguage;
}
