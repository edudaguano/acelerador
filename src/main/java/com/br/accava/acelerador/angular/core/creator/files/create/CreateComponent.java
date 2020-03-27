package com.br.accava.acelerador.angular.core.creator.files.create;

import com.br.accava.acelerador.angular.core.model.Page;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateComponent {

    private final static Logger console = Logger.getLogger(CreateComponent.class.getName());

    public void create(Page page, String type, boolean containsHTML){
        switch (type){
            case "css":
                copyFileToCSSFolder(page);
                break;
            case "js":
                copyFileToJSFolder(page);
                break;
            case "image":
                copyFileToImageFolder(page);
                break;
            default:
                createPageComponent(page);
                break;

        }
    }
    private void createPageComponent(Page page){
        createFolder(page);
        createHTML(page);
        createTs(page);
        createSpecTs(page);
      //  createService(page);
        //createModel(page);

    }

    private void createFolder(Page page){
        System.out.println(page.getName() + "  " + page.getPath());
        String finalPath  = new CreateFolderComponent().createFolder(page.getName(),page.getPath());
        page.setPath(finalPath);
    }

    private void createHTML(Page page){
        console.log(Level.INFO,"Criando component html em: " + page.getPath() + "; da pagina: " + page.getName());
        CreateComponentHtml createComponentHtml = new CreateComponentHtml(page.getName(),page.getPath(),page);
        createComponentHtml.createName();
        createComponentHtml.createFile();
    }
    private void createTs(Page page){
        console.log(Level.INFO,"Criando component ts em: " + page.getPath() + "; da pagina: " + page.getName());
        CreateComponentTs createComponentTs = new CreateComponentTs(page.getName(),page.getPath(),page);
        createComponentTs.createName();
        createComponentTs.createFile();
    }
    private void createSpecTs(Page page){
        console.log(Level.INFO,"Criando component spec ts em: " + page.getPath() + "; da pagina: " + page.getName());
        CreateComponentSpecTs createComponentSpecTs = new CreateComponentSpecTs(page.getName(),page.getPath(),page);
        createComponentSpecTs.createName();
        createComponentSpecTs.createFile();
    }
    private void createService(Page page){
        CreateComponentService createComponentService = new CreateComponentService(page.getName(),page.getPath(),page);
        createComponentService.createFile();
    }
    private void createModel(Page page){
        CreateComponentModel createComponentModel = new CreateComponentModel(page.getName(),page.getPath(),page);
        createComponentModel.createFile();
    }

    private void copyFileToCSSFolder(Page page){

    }
    private void copyFileToJSFolder(Page page){

    }
    private void copyFileToImageFolder(Page page){

    }

}
