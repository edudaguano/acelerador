package com.br.accava.acelerador.intermediateutility.languageconfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "functionelement")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FunctionElement implements Serializable {

    @XmlElement
    private String start;

    @XmlElement
    private String end;
}
