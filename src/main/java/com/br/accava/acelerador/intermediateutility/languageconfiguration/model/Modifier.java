package com.br.accava.acelerador.intermediateutility.languageconfiguration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "modifier")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Modifier implements Serializable {

    @XmlElement(name = "private")
    private String privateModifier;

    @XmlElement(name = "public")
    private String publicModifier;

    @XmlElement(name = "protected")
    private String protectedModifier;

    @XmlElement(name = "default")
    private String defaultModifier;

    @Override
    public String toString() {
        return   privateModifier + '|' +
                    publicModifier + '|' +
                        protectedModifier + '|' +
                          defaultModifier + '|';
    }
}
