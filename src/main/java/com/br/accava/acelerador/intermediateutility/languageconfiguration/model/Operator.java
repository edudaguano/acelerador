package com.br.accava.acelerador.intermediateutility.languageconfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operator")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operator {

    @XmlElement
    private String addition;

    @XmlElement
    private String subtraction;

    @XmlElement
    private String multiplication;

    @XmlElement
    private String division;

    @XmlElement
    private String modulus;

    @XmlElement
    private String increment;

    @XmlElement
    private String decrement;

}
