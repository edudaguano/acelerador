package com.br.accava.acelerador.angular.core.reader;

import com.br.accava.acelerador.angular.core.model.LanguageType;
import com.br.accava.acelerador.angular.core.model.languageInfo.ModifierInfo;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.LanguageFile;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.FunctionElement;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.Info;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.Modifier;
import com.br.accava.acelerador.angular.core.model.Function;
import com.br.accava.acelerador.angular.core.systemvariables.RegexVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionReader {

    private List<Info> languageConfiguration;
    private String fileContent;
    private List<Function> functions;
    private List<LanguageType> languages;

    public FunctionReader(String fileContent,List<LanguageType> languages){
        this.fileContent = fileContent;
        this.languages = languages;
    }

    public List<Function> read(){
        this.functions = new ArrayList<>();
        this.languageConfiguration = new ArrayList<>();
        readXMLConfiguration();
        findLanguageFunctions(this.languageConfiguration,this.fileContent);
        return this.functions;
    }
    private void readXMLConfiguration(){
        LanguageFile languageFile = new LanguageFile();
        for(LanguageType language : languages){
            if(!language.getType().equals("htm")){
                this.languageConfiguration.add(languageFile.getXMLFileModel(language.getName()).getInfo());
            }
        }
    }

    private void findLanguageFunctions(List<Info> languagesFrontSystem, String fileContent) {
        String functionRegex = "";
        List<String> allLanguagesInfoFunction = new ArrayList<>();
        List<Modifier> allLanguagesInfoModifier = new ArrayList<>();
        List<FunctionElement> allLanguagesInfoFunctionElement = new ArrayList<>();

        for(Info languageInfo : languagesFrontSystem) {
            for (String function : languageInfo.getFunction()) {
                if (!allLanguagesInfoFunction.contains(function)) {
                    allLanguagesInfoFunction.add(function);
                }
            }

            allLanguagesInfoModifier.add(languageInfo.getModifier());


            if(!allLanguagesInfoFunctionElement.contains(languageInfo.getFunctionElements())){
                allLanguagesInfoFunctionElement.add(languageInfo.getFunctionElements());
            }

        }
        Info allLanguagesInfo = new Info();
        allLanguagesInfo.setFunction(allLanguagesInfoFunction);

        Modifier allModifiers = handlerWithModifers(allLanguagesInfoModifier);

        FunctionElement allFunctionsElement = handlerWithFunctionElement(allLanguagesInfoFunctionElement);

        allLanguagesInfo.setModifier(allModifiers);
        allLanguagesInfo.setFunctionElements(allFunctionsElement);


        functionRegex = createFunctionRegex(allLanguagesInfo);
        Pattern pattern = Pattern.compile(functionRegex,
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(fileContent);


        while (matcher.find()) {
            Function currentFunction = new Function();

            String modifier = matcher.group(1);
            ModifierInfo modifierInfo = new ModifierInfo();
            modifierInfo.setSyntax(modifier);

            modifierInfo.setType(detectTypeModifier(modifier,allLanguagesInfoModifier));

            currentFunction.setModifier(modifierInfo);
            currentFunction.setName(matcher.group(3));
            currentFunction.setVariables(matcher.group(4));
            currentFunction.setContent(matcher.group(6));
            functions.add(currentFunction);

        }

    }

    private String detectTypeModifier(String modifier,List<Modifier> allLanguagesInfoModifier){
        AtomicReference<String> type = new AtomicReference<>("");
        allLanguagesInfoModifier.forEach(currentModifier -> {

            if(modifier.equals(currentModifier.getPrivateModifier())){
                type.set("private");
            } else if (modifier.equals(currentModifier.getPublicModifier())) {
                type.set("public");
            } else if (modifier.equals(currentModifier.getProtectedModifier())) {
                type.set("protected");
            } else if (modifier.equals(currentModifier.getDefaultModifier())) {
                type.set("default");
            } else {
                type.set("none");
            }
        });

        return type.get();
    }


    private Modifier handlerWithModifers(List<Modifier> allLanguagesInfoModifier){

        StringBuilder privateModifier = new StringBuilder();
        StringBuilder publicModifier = new StringBuilder();
        StringBuilder defaultModifier = new StringBuilder();
        StringBuilder protectedModifier = new StringBuilder();



        allLanguagesInfoModifier.forEach(modifier -> {
            privateModifier.append(checkIfContains(privateModifier.toString(),modifier.getPrivateModifier() + "|"));

            publicModifier.append(checkIfContains(publicModifier.toString(),modifier.getPublicModifier() + "|"));
            defaultModifier.append(checkIfContains(defaultModifier.toString(),modifier.getDefaultModifier() + "|"));
            protectedModifier.append(checkIfContains(protectedModifier.toString(),modifier.getProtectedModifier() + "|"));

        });


        Modifier allModifiers = new Modifier();
        allModifiers.setPrivateModifier(deleteIfEndsWith(privateModifier.toString(),"|"));
        allModifiers.setDefaultModifier(deleteIfEndsWith(defaultModifier.toString(),"|"));
        allModifiers.setProtectedModifier(deleteIfEndsWith(protectedModifier.toString(),"|"));
        allModifiers.setPublicModifier(deleteIfEndsWith(publicModifier.toString(),"|"));

        return allModifiers;

    }
    private String checkIfContains(String value,String hasThisValue){
        String newValue = "";
        if(!value.contains(hasThisValue)){
            newValue = hasThisValue;
        }
        return newValue;
    }

    private FunctionElement handlerWithFunctionElement(List<FunctionElement> allLanguagesInfoFunctionElement){

        StringBuilder startFunction = new StringBuilder();
        StringBuilder endFunction = new StringBuilder();


        allLanguagesInfoFunctionElement.forEach(element -> {
            startFunction.append(element.getStart() + "|");
            endFunction.append(element.getEnd() + "|");
        });


        FunctionElement allFunctionsElement = new FunctionElement();
        allFunctionsElement.setStart(deleteIfEndsWith(startFunction.toString(),"|"));
        allFunctionsElement.setEnd(deleteIfEndsWith(endFunction.toString(),"|"));

        return allFunctionsElement;

    }

    private String deleteIfEndsWith(String value,String endWith){
        String newValue = value;
        System.out.println("VALOR: " + newValue);
        if(value.endsWith(endWith)){
            newValue = newValue.substring(0,newValue.length()-1);
        }
        System.out.println("VALOR NOVO: " + newValue);
        return newValue;
    }

    private String createFunctionRegex(Info languagesFrontSystem){
        return RegexVariables.FIND_ANY_FUNCTION.getItem(
                languagesFrontSystem.getModifier().toString(),
                languagesFrontSystem.getFunction(),
                languagesFrontSystem.getFunctionElements().getEnd());
    }
}
