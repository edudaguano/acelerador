package com.br.accava.acelerador.angular.core.reader;

import com.br.accava.acelerador.angular.core.model.Content;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.ReadLanguageRegex;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentFileSeparator {

    public Content getLanguageContent(String type,String content){
        System.out.println("Content File Separator: " + type);
        ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex(type,"language");
        String regex = readLanguageRegex.getLanguageRegexModel().getInsideDetect();
        Matcher matcher = getMatcher(content, regex);
        StringBuilder contentBuilder = new StringBuilder();
        while (matcher.find()) {
            contentBuilder.append(matcher.group() + "\n\n");
        }

        return new Content(type,contentBuilder.toString(),true);
    }

    public Content getHtmContent(String type,String content){
        ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex(type,"htm");
        String regex = readLanguageRegex.getHyperTextMarkupRegexModel().getTagDetect();
        Matcher matcher = getMatcher(content, regex);
        StringBuilder contentBuilder = new StringBuilder();
        while (matcher.find()) {
            contentBuilder.append(matcher.group() + "\n\n");
        }

        return new Content(type,contentBuilder.toString(),false);
    }

    public Content getSpecificContentInsideContent(String content,String type,String variableType){
        String regex = "";
        ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex(type,"language");
        switch (variableType){
            case "variablesHtm":
                regex = readLanguageRegex.getLanguageRegexModel().getValueHtm();
                break;
            case "variablesGlobal":
                //Todo: Get global variables;
                break;
        }

        Matcher matcher = getMatcher(content, regex);
        StringBuilder contentBuilder = new StringBuilder();
        while (matcher.find()) {

            if(matcher.groupCount() > 0) {
                contentBuilder.append(matcher.group(1) + "\n");
            }
        }

        return new Content(type,contentBuilder.toString(),true);
        /*
            Variaveis
            Funcoes
            Metodos

         */
    }

    public List<String> getAllTypeContentAsList(String typeFile,String type,String content){
        ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex(typeFile,type);
        String regex = readLanguageRegex.getHyperTextMarkupRegexModel().getTagDetect();
        Matcher matcher = getMatcher(content, regex);
        List<String> allContentsInContent = new ArrayList<>();
        while (matcher.find()) {
            allContentsInContent.add(matcher.group());
        }
        return  allContentsInContent;
    }

    public String getInsideLanguageRegex (String type){
        ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex(type,"language");
        return readLanguageRegex.getLanguageRegexModel().getInsideDetect();
    }

    public Matcher getMatcher(String content, String regex) {
        Pattern pattern = Pattern.compile(regex,
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        return pattern.matcher(content);
    }
}
