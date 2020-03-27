package com.br.accava.acelerador.angular.core.creator.util;

import com.br.accava.acelerador.angular.core.systemvariables.AngularFileTypesVariables;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCreatorHelper {

    private final static Logger console = Logger.getLogger(FileCreatorHelper.class.getName());


    public String createName(String fileName,String type){
        String name = changeNamePattenToComponent(fileName,new NamePatternHelper().getNameFileType(fileName));

        console.log(Level.INFO, "Trocando padrão de nomenclatura do arquivo de: " + name);
        switch (type){
            case "htm":
            case "html":
                name = createToHtmlFile(name);
                break;
            case "ts":
                name = createToTsFile(name);
                break;
            case "spec":
                name = createToSpecFile(name);
                break;
            case "css":
                name = createToCssFile(name);
                break;
            case "folder":
                name = createToFolder(fileName,new NamePatternHelper().getNameFileType(fileName));
                break;
        }

        return name;
    }
    private String changeNamePattenToComponent(String fileName,String typeFile){
        return new NamePatternHelper().changeNamePattern(fileName).
                replace(typeFile,"." + AngularFileTypesVariables.COMPONENT.getItem());
    }

    private String createToFolder(String fileName,String typeFile){
        return new NamePatternHelper().changeNamePattern(fileName).
                                        replace(typeFile,"");
    }


    private String createToHtmlFile(String fileName){
            return fileName + "." + AngularFileTypesVariables.HTML.getItem();
    }
    private String createToTsFile(String fileName){
        return fileName + "." + AngularFileTypesVariables.TS.getItem();
    }
    private String createToSpecFile(String fileName){
        return fileName + "." + AngularFileTypesVariables.SPEC.getItem();
    }
    private String createToCssFile(String fileName){
        return fileName + "." + AngularFileTypesVariables.CSS.getItem();
    }

}
