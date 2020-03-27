package com.br.accava.acelerador.angular.core.creator.files.create;

import com.br.accava.acelerador.angular.core.creator.files.interfaces.CreateFile;
import com.br.accava.acelerador.angular.core.creator.util.FileCreatorHelper;
import com.br.accava.acelerador.angular.core.model.Page;
import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class CreateComponentModel implements CreateFile {

    private String name;
    private String path;
    private Page page;


    @Override
    public void createName() {
        this.name = new FileCreatorHelper().createName(this.name,"model");
    }

    @Override
    public void createFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path + "\\" + this.name), StandardCharsets.UTF_8));
            // Todo: Implement to populate code
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBody() {

    }
}
