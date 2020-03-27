package com.br.accava.acelerador.angular.core.creator.files.populate;

import com.br.accava.acelerador.angular.core.creator.util.NamePatternHelper;
import com.br.accava.acelerador.angular.core.model.Function;
import com.br.accava.acelerador.angular.core.model.Page;
import com.br.accava.acelerador.angular.core.model.Variable;
import com.br.accava.acelerador.angular.core.systemvariables.TemplateVariables;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.LanguageFile;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.Info;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class WriteTsFileHandler {

    public String replaceTemplate(List<String> lines, Page page){

        Info typescriptInfo = readXMLTypescriptConfiguration();

        String content = Strings.join(lines, '\n');

        String functions = functionBuilder(page);
        String variables = variablesBuilder(page);
        String name = new NamePatternHelper().getName(page.getName());
        String className = constructClassName(name);

        return populateTemplate(content,name,functions,className,variables);
    }

    private String functionBuilder(Page page){
        StringBuilder functionsBuilder = new StringBuilder();
        if(!Objects.isNull(page.getFunctions())){
            page.getFunctions().forEach(function -> {
                functionsBuilder.append(constructFunction(function) + "\n \n");
            });
        }
        return functionsBuilder.toString();
    }

    private String variablesBuilder(Page page){
        StringBuilder variablesBuilder = new StringBuilder();
        if(!Objects.isNull(page.getGlobalVariables())){
            page.getGlobalVariables().forEach(variable -> {
                variablesBuilder.append(constructGlobalVariables(variable) + "\n");
            });
        }
        return variablesBuilder.toString();
    }

    private Info readXMLTypescriptConfiguration() {
        LanguageFile languageFile = new LanguageFile();
        return languageFile.getXMLFileModel("typescript").getInfo();
    }

    private String constructClassName(String name){
        StringBuilder className = new StringBuilder();

        AtomicBoolean nextUpper = new AtomicBoolean(false);
        Stream.of(name.split("-")).forEach(item ->{
            System.out.println(item);

            className.append(String.valueOf(item.charAt(0)).toUpperCase() + item.substring(1));

        });

        return className.toString();
    }

    private String constructFunction(Function function){
        return function.getModifier().getSyntax() + " " + function.getName()
                .replace(String.valueOf(function.getName().charAt(0)),
                        String.valueOf(function.getName().charAt(0)).toLowerCase()) +
                            "(" + function.getVariables() + ") {/*" + function.getContent() + "*/}";
    }
    private String constructGlobalVariables(Variable variable){
        return variable.getNome() + ": " + variable.getType();
    }

    private String populateTemplate(String content,String name,String functions,String className,String globalVariables){
        return content.replace(TemplateVariables.APP_NAME.getItem(),name)
                .replace(TemplateVariables.FUNCTIONS.getItem(),functions)
                    .replace(TemplateVariables.CLASS_NAME.getItem(),className)
                        .replace(TemplateVariables.GLOBAL_VARIABLES.getItem(),globalVariables);
    }

    /*
          Note: Compare Modifiers

     */

}
