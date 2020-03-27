package com.br.accava.acelerador.angular.core.reader;

import com.br.accava.acelerador.intermediateutility.languageconfiguration.ReadLanguageRegex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class HyperTextMarkupLanguageReader {
    //Cola do valor da variável contendo no html
    //Detectar se há mais de uma teg html dentro
    //Detectar se possue tag javascript e tirar
    //Detectar se possue tag css e tirar
    //Detectar se possue tag da linguagem no meio e tirar
    private ContentFileSeparator contentFileSeparator;
    private final String deleteText = "";
    private String content;

    public HyperTextMarkupLanguageReader(String content){
        this.contentFileSeparator = new ContentFileSeparator();
        this.content = content;
    }

    public String read(){
        return getAllHtmlTags(content);
    }

    private String getAllHtmlTags(String content){
        StringBuilder newContentConstructor = new StringBuilder();
        detectMultipleHtmlTags(content).forEach(tag -> {
            tag = deleteJavascript(tag);
            //Todo: Put the language type dynamic
            ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex("asp","language");
            tag = replaceVariableValues(tag,readLanguageRegex.getLanguageRegexModel().getValueHtm());
            tag = deleteIfHasLanguageInside(tag);
            newContentConstructor.append(tag + "\n");
            System.out.println("CONTENT NEWWW ----------------------");
            System.out.println(tag);
        });
        return newContentConstructor.toString();
    }

    private String replaceVariableValues(String currentContent,String regex){
        Matcher matcher = contentFileSeparator.getMatcher
                (currentContent,regex);
        Map<String,String> changerList = new HashMap<>();

        while(matcher.find()){
            String replaceFrom = matcher.group();
            String replaceTo = "{{" + matcher.group(1) + "}}";
            currentContent = currentContent.replace(replaceFrom,replaceTo);
            changerList.put(replaceFrom,replaceTo);
        }
        return currentContent;
    }
    private List<String> detectMultipleHtmlTags(String currentContent){
        return contentFileSeparator.getAllTypeContentAsList("html","htm",currentContent);
    }
    private String deleteJavascript(String currentContent){
        Matcher matcher = contentFileSeparator.getMatcher
                (currentContent,contentFileSeparator.getInsideLanguageRegex("javascript"));
        return matcher.replaceAll(this.deleteText);
    }
    private void detectCss(){}
    private String deleteIfHasLanguageInside(String currentContent){
        Matcher matcher = contentFileSeparator.getMatcher
                (currentContent,contentFileSeparator.getInsideLanguageRegex("asp"));
        return matcher.replaceAll(this.deleteText);
    }
}
