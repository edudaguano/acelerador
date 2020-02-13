package com.br.accava.acelerador.intermediateutility.zip;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 *@author A. Crisafulli (allancrisafulli@gmail.com)
 *@description This class will handler with zip template projects example files.
 */
public class ZipHandler {

    private final static Logger console = Logger.getLogger(ZipHandler.class.getName());

    private String fromPath;
    private String toPath;

    public ZipHandler(String fromPath, String toPath){
        this.fromPath = fromPath;
        this.toPath = toPath;
    }

    /**
     * @author A. Crisafulli (allancrisafulli@gmail.com)
     * @description this function
     */
    public void unzipFile(){
        console.log(Level.INFO,"Iniciar a extração do projeto modelo");
        try {
            ZipFile file = new ZipFile(fromPath);

            FileSystem fileSystem = FileSystems.getDefault();
            Enumeration<? extends ZipEntry> entries = file.entries();

            ArrayList<? extends ZipEntry> entriesList = Collections.list(entries);

            Path fileSystemToPath = fileSystem.getPath(toPath);

            deleteAllPastFiles(fileSystemToPath);

            if(!Files.exists(fileSystemToPath)) {
                Files.createDirectory(fileSystemToPath);
            }


           for(int i = 0; i < entriesList.size(); i++){
                ZipEntry zipEntry = entriesList.get(i);
                String filePath = toPath + zipEntry.getName();
                createFile(fileSystem,filePath);
                extract(zipEntry, file,filePath);
            }

        } catch (IOException e) {
            console.log(Level.SEVERE,"Um erro ocorreu ao dezipar projeto modelo: " ,e);
        }
    }


    /**
     * @author A. Crisafulli (allancrisafulli@gmail.com)
     * @param path (Path)
     * @description In reverse order, deletes all files contained in the generation folder, including it (Initial node)
     */
    private void deleteAllPastFiles(Path path){
        console.log(Level.INFO,"Deletando todos os arquivos antigos da pasta de geração");
        try {
            Files.walk(path).sorted(Comparator.reverseOrder())
                    .map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            console.log(Level.SEVERE,"Um erro ocorreu ao extrair o projeto modelo >> ZipHandler >> deleteAllPastFiles: " ,e);
        }
    }

    /**
     * @author A. Crisafulli (allancrisafulli@gmail.com)
     * @param fileSystem (FileSystem)
     * @param path (String)
     * @description this function create the file with passed path
     */
    private void createFile(FileSystem fileSystem,String path){
        Path filePath = fileSystem.getPath(path);
        try {
            if(!Files.exists(filePath.getParent())){
                console.log(Level.INFO,"Criando parente : " + filePath.getParent());
                Files.createDirectories(filePath.getParent());
            }

            if(path.endsWith("/")) {
                console.log(Level.INFO,"Criando pasta : " + filePath);
                File fileInPath = new File(path);
                fileInPath.mkdir();

            }

            if(!Files.isDirectory(filePath)){
                console.log(Level.INFO,"Criando Arquivo : " + filePath);
                Files.deleteIfExists(filePath);
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            console.log(Level.SEVERE,"Um erro ocorreu ao extrair o projeto modelo >> ZipHandler >> createFile: " ,e);
        }
    }

    /**
     * @author A. Crisafulli (allancrisafulli@gmail.com)
     * @param zipEntry (ZipEntry)
     * @param file  (ZipFile)
     * @param filePath  (String)
     * @description this function extract a file from zip with passed path
     */
    private void extract(ZipEntry zipEntry,ZipFile file,String filePath) {
        try {
            if(!Files.isDirectory(Paths.get(filePath))) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getInputStream(zipEntry));
                while (bufferedInputStream.available() > 0) {
                    bufferedOutputStream.write(bufferedInputStream.read());
                }

                bufferedOutputStream.close();
                bufferedInputStream.close();
            }

        }  catch (IOException e) {
            console.log(Level.SEVERE,"Um erro ocorreu ao extrair o projeto modelo >> ZipHandler >> extract: " ,e);
        }
    }
}
