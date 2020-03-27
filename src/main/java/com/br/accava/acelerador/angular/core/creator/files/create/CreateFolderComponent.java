package com.br.accava.acelerador.angular.core.creator.files.create;

import com.br.accava.acelerador.angular.core.creator.util.FileCreatorHelper;

import java.io.File;

public class CreateFolderComponent {

    public String createFolder(String name, String path){
        String finalPath = path + new FileCreatorHelper().createName(name,"folder");
        File folder = new File(finalPath);
        folder.mkdir();

        return finalPath;
    }
}
