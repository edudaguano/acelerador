package com.br.accava.acelerador.intermediateutility.languageconfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "datatype")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Datatype implements Serializable {

    @XmlElement(name = "stringdatatype")
    private String stringDataType;

    @XmlElement(name = "intdatatype")
    private String intDataType;

    @XmlElement(name = "doubledatatype")
    private String doubleDataType;

    @XmlElement(name = "floatdatatype")
    private String floatDataType;

    @XmlElement(name = "bytedatatype")
    private String byteDataType;

    @XmlElement(name = "shortdatatype")
    private String shortDataType;

    @XmlElement(name = "longdatatype")
    private String longDataType;

    @XmlElement(name = "chardatatype")
    private String charDataType;

    @XmlElement(name = "booleandatatype")
    private String booleanDataType;

    @XmlElement(name = "integerdatatype")
    private String integerDataType;
}
