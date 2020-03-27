package com.br.accava.acelerador.angular.core.model.contructlanguage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComponentInsideTsConstructor {
    private String selector;
    private String templateUrl;
    private String StyleUrls;
}
