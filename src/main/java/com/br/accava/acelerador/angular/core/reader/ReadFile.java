package com.br.accava.acelerador.angular.core.reader;

import com.br.accava.acelerador.angular.core.creator.util.NamePatternHelper;
import com.br.accava.acelerador.angular.core.model.*;
import lombok.NoArgsConstructor;
import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor
public class ReadFile {

    private final static Logger console = Logger.getLogger(ReadFile.class.getName());

    private Page currentPage;
    private List<Page> pages;
    private LinkedHashSet<Function> functions;
    private LinkedHashSet<Method> methods;
    private LinkedHashSet<Variable> variables;
    private String htmlContent;

    public Page readFile(File file,List<LanguageType> pageLanguages) throws IOException {

        functions = new LinkedHashSet<>();
        methods = new LinkedHashSet<>();
        variables = new LinkedHashSet<>();
        this.htmlContent = "";
        
        System.out.println("--------------------------------------------");
        System.out.println("Informações do arquivo");
        System.out.println(file.getName());
        System.out.println(file.getPath());

        pageLanguages.forEach(languageType -> {
            System.out.println("Encontrando erro");
            System.out.println(languageType.getName());
            System.out.println(languageType.getType());
        });

        String fileType = new NamePatternHelper().getNameFileType(file.getName()).replace(".","");
        System.out.println(fileType);
        System.out.println(String.join("\n",Files.readAllLines(file.toPath(), Charset.forName(detectCharset(file)))));
        System.out.println("--------------------------------------------");

        findAll(String.join("\n",Files.readAllLines(file.toPath(),Charset.forName(detectCharset(file)))),pageLanguages);

        return new Page(file.getName(),"src\\main\\resources\\GenerateProject\\projeto-exemplo\\src\\app\\pages\\",fileType,pageLanguages,null,new ArrayList<>(this.functions),null,new ArrayList<>(this.variables),this.htmlContent);

    }

    private void findAll(String content,List<LanguageType> pageLanguages){

        List<Content> contentLanguage = languageSeparate(content,pageLanguages);
        contentLanguage.forEach(contentInPage -> {
            System.out.println("TIPO DO CONTEXTO " + contentInPage.getType() + "---------------------------------------------------------------\n\n");
            System.out.println(contentInPage.getText() + "\n\n");
            System.out.println("---------------------------------------------------------------\n\n");

            readFunctions(contentInPage.getText(), pageLanguages).forEach(function -> functions.add(function));

            if(!contentInPage.isLanguage()){
                this.htmlContent = this.htmlContent + readHtmlContent(contentInPage.getText(),pageLanguages);
            } else {
                readGlobalVariable(contentInPage).forEach(val -> variables.add(val));
                readMethods(contentInPage.getText(), pageLanguages);
            }

        });



    }

    private List<Content> languageSeparate(String content,List<LanguageType> pageLanguages){
        ContentFileSeparator contentFileSeparator = new ContentFileSeparator();

        List<Content> contents = new ArrayList<>();

        pageLanguages.forEach(languageType -> {
            System.out.println("languageSeparate name : " + languageType.getName());
            System.out.println("languageSeparate type : " + languageType.getType());

            System.out.println("languageSeparate type equals : " + languageType.getType().equals("language"));
            if(languageType.getType().equals("language")){
                contents.add(contentFileSeparator.getLanguageContent(languageType.getName(),content));
            } else {
                contents.add(contentFileSeparator.getHtmContent(languageType.getName(),content));
            }
        });
        return contents;
    }

    private List<Function> readFunctions(String content,List<LanguageType> pageLanguages){
        FunctionReader functionReader = new FunctionReader(content,pageLanguages);
        return functionReader.read();
    }

    private void readMethods(String content,List<LanguageType> pageLanguages){

    }

    private List<Variable> readGlobalVariable(Content content){
        VariableReader variableReader = new VariableReader(content.getText(),content.getType());
        return variableReader.read();
    }


    private void readUntiedContent(String content,List<LanguageType> pageLanguages){

    }


    private String readHtmlContent(String content,List<LanguageType> pageLanguages){
        HyperTextMarkupLanguageReader hyperTextMarkupLanguageReader = new HyperTextMarkupLanguageReader(content);
        return hyperTextMarkupLanguageReader.read();


    }

    private String detectCharset(File file){
        console.log(Level.INFO,"Detectado tipo de Encoding do arquivo");

        String charset = "";
        CharsetDetector charsetDetector = new CharsetDetector();
        try {
            charsetDetector.setText(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            console.log(Level.WARNING,"Ocorreu um erro ao tentar se contectar com o arquivo", e);
        }
        CharsetMatch charsetMatch = charsetDetector.detect();
        if(!Objects.isNull(charsetMatch)){
            charset = charsetMatch.getName();
        } else {
            console.log(Level.WARNING,"Encoding do arquivo não é suportado");
            throw new UnsupportedCharsetException("Encoding do arquivo não é suportado");
        }
        return charset;
    }
}
