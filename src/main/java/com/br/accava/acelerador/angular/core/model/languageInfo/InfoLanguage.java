package com.br.accava.acelerador.angular.core.model.languageInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoLanguage {
    private List<ModifierInfo> modifierInfos;
    private List<DatatypeInfo> datatypeInfos;

}
