package com.br.accava.acelerador.angular.core.reader;

import lombok.Getter;

import java.util.List;

@Getter
public class InfoReader {
    private List<String> languages;
    private String privateModifier;
    private String publicModifier;

    public InfoReader(){

    }
}
