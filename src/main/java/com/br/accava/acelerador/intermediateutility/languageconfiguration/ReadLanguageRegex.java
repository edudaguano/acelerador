package com.br.accava.acelerador.intermediateutility.languageconfiguration;

import com.br.accava.acelerador.intermediateutility.languageconfiguration.regex.HyperTextMarkup.HyperTextMarkupRegexModel;
import com.br.accava.acelerador.intermediateutility.languageconfiguration.regex.languages.LanguageRegexModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadLanguageRegex {

    private String file = "_regex.json";
    private final String pathLanguage = "src\\main\\java\\com\\br\\accava\\acelerador\\intermediateutility\\languageconfiguration\\regex\\languages\\";
    private final String pathHtm = "src\\main\\java\\com\\br\\accava\\acelerador\\intermediateutility\\languageconfiguration\\regex\\HyperTextMarkup\\";

    @Getter
    private LanguageRegexModel languageRegexModel;

    @Getter
    private HyperTextMarkupRegexModel hyperTextMarkupRegexModel;

    public ReadLanguageRegex(String language,String type){
        System.out.println("ReadLanguageRegex " + language + " " + type);
        try {
            if (type.equals("language")) {
                languageRegexModel = getLanguageRegex(run(pathLanguage + language + file));

            } else {
                hyperTextMarkupRegexModel = getHtmRegex(run(pathHtm + language + file));
                hyperTextMarkupRegexModel.setTagDetect(hyperTextMarkupRegexModel.getTagDetect().replace("$tag$",language));
            }

        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String run(String path){
        System.out.println("ReadLanguageRegex >> run " + path);
        JSONParser jsonParser = new JSONParser();
        String jsonFile = "";
        try(FileReader fileReader  = new FileReader(path)){
            Object object = jsonParser.parse(fileReader);

            JSONObject jsonObject = (JSONObject) object;


            jsonFile = jsonObject.toJSONString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonFile;
    }

    private LanguageRegexModel getLanguageRegex(String json) throws JsonProcessingException {
        return new ObjectMapper()
                .readerFor(LanguageRegexModel.class)
                .readValue(json);
    }
    private HyperTextMarkupRegexModel getHtmRegex(String json) throws JsonProcessingException {
        return new ObjectMapper()
                .readerFor(HyperTextMarkupRegexModel.class)
                .readValue(json);
    }
}
