package com.br.accava.acelerador.intermediateutility.languageconfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Info implements Serializable {

    @XmlElementWrapper(name = "functions")
    @XmlElement(name = "function")
    private List<String> function;

    @XmlElement(name = "functionelements")
    private FunctionElement functionElements;

    @XmlElement
    private Modifier modifier;

    @XmlElement
    private Datatype datatype;

    @XmlElement
    private Array array;

    @XmlElement
    private Loop loop;

    @XmlElement
    private Operator operator;

    @XmlElement(name = "variablenametype")
    private VariableNameType variableNameType;
}
