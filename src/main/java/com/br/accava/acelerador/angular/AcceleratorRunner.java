package com.br.accava.acelerador.angular;

import com.br.accava.acelerador.angular.core.creator.files.create.CreateComponent;
import com.br.accava.acelerador.angular.core.model.LanguageType;
import com.br.accava.acelerador.intermediateutility.zip.ZipHandler;
import com.br.accava.acelerador.angular.core.model.Page;
import com.br.accava.acelerador.angular.core.reader.ReadFile;
import com.br.accava.acelerador.angular.core.systemvariables.PathVariables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class AcceleratorRunner {

    private final String ERRO_ARQUIVOS = "Erro ao ler arquivos";
    private final String ERRO_ARQUIVO = "Erro ao ler arquivo";
    //-----------------------------------------------------------


    private final static Logger console = Logger.getLogger(AcceleratorRunner.class.getName());

    private String pathToRead;
    private String pathToWrite = "src/main/resources/GenerateProject/";
    private List<Page> pages;
    private List<LanguageType> pageLanguages;

    public AcceleratorRunner(String path,List<LanguageType> pageLanguages) {
        this.pathToRead = path;
        this.pageLanguages = pageLanguages;
    }

    public void run(){
        readFolder(this.pathToRead);
        createFiles();
    }

    private void readFolder(String path){
        try(Stream<Path> paths = Files.walk(Paths.get(path))){
            this.pages = new ArrayList<Page>();
            paths.forEach(file -> checkFile(file));

        } catch (IOException e) {
            console.log(Level.SEVERE,ERRO_ARQUIVOS,e);
        }
    }

    private void checkFile(Path file){
        if(Files.isRegularFile(file)){
            this.pages.add(readFile(file));

        }
    }

    private Page readFile(Path file){
        Page page = new Page();
            try {
               page = new ReadFile().readFile(file.toFile(),this.pageLanguages);
            } catch (IOException e) {
                console.log(Level.SEVERE,
                        ERRO_ARQUIVO + " [" +
                                file.toFile().getName() + "] ", e);
            }
        return page;
    }

    private void createFiles(){
        ZipHandler zipHandler = new ZipHandler(PathVariables.ZIP_PATH.getItem(),this.pathToWrite);
        zipHandler.unzipFile();
        this.pages.forEach(page -> callCreators(page));

    }

    private void callCreators(Page page){
        System.out.println(page.getName());
        CreateComponent createComponent = new CreateComponent();
        createComponent.create(page,page.getFileType(),true);

    }

}
