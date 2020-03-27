package com.br.accava.acelerador.intermediateutility.languageconfiguration.regex.HyperTextMarkup;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class HyperTextMarkupRegexModel {

    @JsonProperty
    private String language;

    @JsonProperty
    private String tagDetect;
}
