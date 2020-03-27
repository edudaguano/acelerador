package com.br.accava.acelerador.intermediateutility.languageconfiguration;

import com.br.accava.acelerador.intermediateutility.languageconfiguration.model.XMLFileModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageFile {

    private String fileType;
    private XMLFileModel xmlFileModel;
    private final static Logger console = Logger.getLogger(LanguageFile.class.getName());
    private final String path = "src\\main\\java\\com\\br\\accava\\acelerador\\intermediateutility\\languageconfiguration\\xmlconfig\\";

    public XMLFileModel getXMLFileModel(String type){
        return readXML(path + type.trim() + ".xml");
    }
    public boolean hasThisInformation(String type){
        return Files.exists(Paths.get(path + type.trim() + ".xml"));
    }

    private XMLFileModel readXML(String path){

        xmlFileModel = null;

        try {

            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLFileModel.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            xmlFileModel = (XMLFileModel) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            console.log(Level.SEVERE, "Ocorreu um erro ao ler o arquivo xml do tipo solicitado", e);
        }

        return xmlFileModel;
    }

}
