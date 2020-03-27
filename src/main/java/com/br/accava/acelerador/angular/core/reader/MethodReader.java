package com.br.accava.acelerador.angular.core.reader;

import com.br.accava.acelerador.angular.core.model.Method;
import com.br.accava.acelerador.angular.core.model.Variable;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.ReadLanguageRegex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;

public class MethodReader {

    private ContentFileSeparator contentFileSeparator;
    private String content;

    public List<Method> read(String content) {
        this.contentFileSeparator = new ContentFileSeparator();
        this.content = content;

        return findMethod(content);
    }
    private List<Method> findMethod(String content){
        List<Method> methods = new ArrayList<>();
        findServiceCall(content).forEach(service -> {
            //Todo: manipulate services finding any
        });

        return methods;
    }

    private LinkedHashSet<String> findServiceCall(String content){
        LinkedHashSet<String> services = new LinkedHashSet<>();
        ReadLanguageRegex readLanguageRegex = new ReadLanguageRegex("asp","language");
        Matcher matcher = contentFileSeparator.getMatcher
                (content,readLanguageRegex.getLanguageRegexModel().getBackCallDetect());
        while(matcher.find()){
            //Todo: Make this dynamic
            String service = matcher.group(2).split(".")[1];
            services.add(service);
        }
        return services;
    }

    private LinkedHashSet<String> findAllServicesCall(String content){
        LinkedHashSet<String> serviceCalls = new LinkedHashSet<>();
        //Todo:implement service calls finder and call handleWithVariables
        return serviceCalls;
    }

    private List<Variable> handleWithVariable(String content){
        List<Variable> variables = new ArrayList<>();
        //Todo: implement variable handler;
        return variables;
    }
}
