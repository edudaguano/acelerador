package com.br.accava.acelerador.angular.core.creator.files.create;

import com.br.accava.acelerador.angular.core.creator.files.interfaces.CreateFile;
import com.br.accava.acelerador.angular.core.creator.files.populate.WriteTsFileHandler;
import com.br.accava.acelerador.angular.core.creator.util.FileCreatorHelper;
import com.br.accava.acelerador.angular.core.model.Page;
import com.br.accava.acelerador.angular.core.systemvariables.FileVariables;
import lombok.AllArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@AllArgsConstructor
public class CreateComponentTs implements CreateFile {

    private String name;
    private String path;
    private Page page;


    @Override
    public void createName() {
        this.name = new FileCreatorHelper().createName(this.name,"ts");
    }

    @Override
    public void createFile() {
        String finalPath = this.path + "\\" + this.name;
        //File fileCopy = new File(this.path + "\\" + this.name);
        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(finalPath), StandardCharsets.UTF_8));

            WriteTsFileHandler writeTsFileHandler = new WriteTsFileHandler();
            String content = writeTsFileHandler.replaceTemplate(Files.readAllLines(Paths.get(FileVariables.TS_TEMPLATE.getItem())),page);


            bufferedWriter.write(content);
            bufferedWriter.close();

            // Todo: Implement to populate code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBody() {

    }
}
