package com.br.accava.acelerador.intermediateutility.languageconfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "variablenametype")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableNameType {

    @XmlElement(name = "stringname")
    private String stringName;

    @XmlElement(name = "intname")
    private String intName;

    @XmlElement(name = "doublename")
    private String doubleName;

    @XmlElement(name = "floatname")
    private String floatName;

    @XmlElement(name = "bytename")
    private String byteName;

    @XmlElement(name = "shortname")
    private String shortName;

    @XmlElement(name = "longname")
    private String longName;

    @XmlElement(name = "charname")
    private String charName;

    @XmlElement(name = "booleanname")
    private String booleanName;

}
