package com.br.accava.acelerador.angular.core.creator.util;

import com.br.accava.acelerador.angular.core.systemvariables.AngularFileTypesVariables;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NamePatternHelper {

    private final static Logger console = Logger.getLogger(NamePatternHelper.class.getName());

    public String changeNamePattern(String fileName){
        console.log(Level.INFO, "Trocando padrão de nomenclatura do arquivo");
        return fileName.toLowerCase().replace("_","-");
    }

    public String getName(String fileName){
        String typeFile = getNameFileType(fileName);
        return new NamePatternHelper().changeNamePattern(fileName).
                replace(typeFile,"");
    }

    public String getNameFileType(String fileName){
        int typeIndex = fileName.lastIndexOf(".");
        return fileName.substring(typeIndex);
    }
}
