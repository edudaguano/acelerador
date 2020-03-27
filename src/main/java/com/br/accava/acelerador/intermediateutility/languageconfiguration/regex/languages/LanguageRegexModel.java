package com.br.accava.acelerador.intermediateutility.languageconfiguration.regex.languages;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguageRegexModel {
    @JsonProperty
    private String language;

    @JsonProperty
    private String insideDetect;

    @JsonProperty
    private String importDetect;

    @JsonProperty
    private String backCallDetect;

    @JsonProperty
    private String valueHtm;
}
