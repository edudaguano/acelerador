package com.br.accava.acelerador.angular.core.creator.files.populate;

import com.br.accava.acelerador.angular.core.model.languageInfo.ModifierInfo;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.LanguageFile;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.Info;

public class WriterFileHelper {


    public Info getLanguageInfo(String language){
        LanguageFile languageFile = new LanguageFile();
        return languageFile.getXMLFileModel(language).getInfo();
    }

    public String compareModifier(ModifierInfo languageFrom, String languageTo){
        String modifier = "";
        Info toInfo = getLanguageInfo(languageTo);
        switch(languageFrom.getType()){
            case "private":
                modifier = toInfo.getModifier().getPrivateModifier();
                break;
            case "public":
                modifier = toInfo.getModifier().getPublicModifier();
                break;
            case "protected":
                modifier = toInfo.getModifier().getProtectedModifier();
                break;
            case "default":
                modifier = toInfo.getModifier().getDefaultModifier();
                break;
        }
        return modifier;
    }
}
