package com.br.accava.acelerador.backend.jab.camadas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.br.accava.acelerador.backend.jab.modelo.AtributoMetodoModel;
import com.br.accava.acelerador.backend.jab.modelo.ClsModel;
import com.br.accava.acelerador.backend.jab.modelo.MetodoModel;

public class CriacaoService {

	public void criarService(ClsModel clsModel, String caminhoEspelhado) throws Exception {
		System.out.println(caminhoEspelhado);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(caminhoEspelhado)));
		
		String nomeInterface = clsModel.getNomeCls();
		if (nomeInterface != null && nomeInterface.endsWith(".cls")) {
			nomeInterface = nomeInterface.substring(0, nomeInterface.indexOf(".cls"));
			nomeInterface = nomeInterface+"Service";
		}

		String packageJr = caminhoEspelhado.substring(caminhoEspelhado.indexOf("\\src") + 4 );
		packageJr = packageJr.replace("\\", ".");
		packageJr = packageJr.replace(".java", "");
		packageJr = packageJr.substring(1, packageJr.lastIndexOf(".")) + ";";
		packageJr = packageJr.replace("main.", "");
		
		bw.append("package " + packageJr );
		
		bw.append(System.lineSeparator());
		bw.append(System.lineSeparator());
		bw.append("import com.altec.bsbr.fw.BusinessException;");
		bw.append(System.lineSeparator());
		bw.append(System.lineSeparator());
		
		if (clsModel != null) {
			bw.append("public interface " + nomeInterface+ " {");
			bw.append(System.lineSeparator());
			bw.append(System.lineSeparator());
			
			
			if (clsModel.getListaMetodos() != null) {
				for(MetodoModel mm : clsModel.getListaMetodos()) {
					String atributosMetodo = "";
					if (mm.getListaAtributoMetodo() != null) {
						for (AtributoMetodoModel amm : mm.getListaAtributoMetodo()) {
							String atrr = amm.getNomeAtributo();
							if (atrr == null) {
								atrr = "nulo";
							}
							if (atrr.contains(":")) {
								atrr = atrr.substring(atrr.indexOf(":") +1);
							}
							if (atrr.contains(" As ")) {
								atrr = atrr.substring(0, atrr.indexOf(" As "));
							}
							atributosMetodo += "String "+ atrr.trim() + ", ";
						}
						}
					atributosMetodo =  "(" + atributosMetodo + ")" + ";";
					atributosMetodo = atributosMetodo.replace(", );", ") throws BusinessException;");
					bw.append("\tpublic String "+ mm.getNomeMetodo() + atributosMetodo);
					bw.append(System.lineSeparator());
					}
				}
			}
		
		bw.append(System.lineSeparator());
		bw.append("}");
		bw.close();
	}

}
