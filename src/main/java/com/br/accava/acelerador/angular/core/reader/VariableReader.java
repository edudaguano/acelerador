package com.br.accava.acelerador.angular.core.reader;

import com.br.accava.acelerador.angular.core.model.Content;
import com.br.accava.acelerador.angular.core.model.Function;
import com.br.accava.acelerador.angular.core.model.LanguageType;
import com.br.accava.acelerador.angular.core.model.Variable;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.LanguageFile;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.Info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VariableReader {
    private Info languageConfiguration;
    private String fileContent;
    private List<Variable> variables;
    private List<LanguageType> languages;
    private String contentType;

    public VariableReader(String fileContent,String contentType){
        this.fileContent = fileContent;
        this.contentType = contentType;
    }

    private void readXMLConfiguration(){
        LanguageFile languageFile = new LanguageFile();
        languageConfiguration = null;
        if(languageFile.hasThisInformation(contentType)) {
            this.languageConfiguration = languageFile.getXMLFileModel(contentType).getInfo();
        }

    }

    public List<Variable> read(){
        System.out.println("VariableReader : " + contentType);
        this.variables = new ArrayList<>();
        this.languageConfiguration = new Info();
        readXMLConfiguration();
        findLanguageVariables(this.fileContent,contentType);
        return this.variables;
    }

    private void findLanguageVariables(String fileContent,String contentType) {
        findValueFromHTM(fileContent,contentType);

    }

    private void findValueFromHTM(String fileContent,String contentType){
        ContentFileSeparator contentFileSeparator = new ContentFileSeparator();
        String contentSeparated = contentFileSeparator.getSpecificContentInsideContent(fileContent,contentType,"variablesHtm").getText();
        this.variables = createVariableListFromStringList(turnContentInList(contentSeparated));

    }

    private List<String> turnContentInList(String content){
        return Arrays.asList(content.split("\n"));
    }

    private List<Variable> createVariableListFromStringList(List<String> variables){
        List<Variable> newList = new ArrayList<>();
        variables.forEach(variable -> {
            Variable currentVariable = new Variable();
            String currentVariableType = "";
            System.out.println(languageConfiguration.getVariableNameType().getBooleanName());
            if(!variable.isEmpty()) {
                if (!Objects.isNull(languageConfiguration)) {
                    currentVariableType = returnVariableType(variable);
                }
                currentVariable.setNome(variable);
                currentVariable.setType(currentVariableType);

                System.out.println("Nome da variavel " + variable);
                System.out.println("Tipo da variavel " + currentVariableType);
            }

        });
        return newList;
    }
    private String returnVariableType(String variable){
        if(variable.startsWith(languageConfiguration.getVariableNameType().getBooleanName())){
            return "boolean";
        }
        if(variable.startsWith(languageConfiguration.getVariableNameType().getCharName())){
            return "string";
        }
        if(variable.startsWith(languageConfiguration.getVariableNameType().getDoubleName())){
            return "number";
        }
        if(variable.startsWith(languageConfiguration.getVariableNameType().getFloatName())){
            return "number";
        }
        if(variable.startsWith(languageConfiguration.getVariableNameType().getIntName())){
            return "number";
        }

        if(variable.startsWith(languageConfiguration.getVariableNameType().getLongName())){
            return "number";
        }
        if(variable.startsWith(languageConfiguration.getVariableNameType().getShortName())){
            return "number";
        }
        if(variable.startsWith(languageConfiguration.getVariableNameType().getStringName())){
            return "string";
        }
        return "string";
    }
}
