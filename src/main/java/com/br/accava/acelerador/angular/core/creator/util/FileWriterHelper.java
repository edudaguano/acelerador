package com.br.accava.acelerador.angular.core.creator.util;

public class FileWriterHelper {

    public String generateAppName(String name){
        return new NamePatternHelper().changeNamePattern(name);
    }


}
