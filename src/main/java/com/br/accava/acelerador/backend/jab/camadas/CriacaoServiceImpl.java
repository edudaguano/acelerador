package com.br.accava.acelerador.backend.jab.camadas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.br.accava.acelerador.backend.jab.modelo.AtributoMetodoModel;
import com.br.accava.acelerador.backend.jab.modelo.ChamadaModel;
import com.br.accava.acelerador.backend.jab.modelo.ClsModel;
import com.br.accava.acelerador.backend.jab.modelo.MetodoModel;
import com.google.common.util.concurrent.Service;

public class CriacaoServiceImpl {


	private static String nomeInterface = "";
	private static String packageJr =  "";

	public void criarServiceImpl(ClsModel clsModel, String caminhoEspelhado) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(caminhoEspelhado)));

		String packageBasico = configuracaoBasica(clsModel, caminhoEspelhado);
		String importBasico =  configuracaoBasicaImport(clsModel, caminhoEspelhado);

		bw.append("package " + packageBasico);
		bw.append(System.lineSeparator());
		bw.append(System.lineSeparator());
		bw.append("import " + importBasico);
		bw.append(System.lineSeparator());
		bw.append("import org.slf4j.Logger;\r\n" + 
				"import org.slf4j.LoggerFactory;\r\n" + 
				"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
				"import org.springframework.stereotype.Repository;");
		bw.append(System.lineSeparator());
		bw.append(System.lineSeparator());

		if (clsModel != null) {
			bw.append("@Service");
			bw.append(System.lineSeparator());
			bw.append("public class " + nomeInterface.trim()+ "ServiceImpl implements "+ nomeInterface.trim() +"Service {");
			bw.append(System.lineSeparator());
			bw.append(System.lineSeparator());
			bw.append("\t@autowired");
			bw.append(System.lineSeparator());
			bw.append("\tprivate "+nomeInterface+"Dao " +nomeInterface.toLowerCase() + ";");
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
					atributosMetodo = atributosMetodo.replace(", );", ") {");
					bw.append("\t public String "+mm.getNomeMetodo() + atributosMetodo);
					bw.append(System.lineSeparator());
					atributosMetodo = atributosMetodo.replace("String","");
					atributosMetodo = atributosMetodo.replace("{",";");
					bw.append("\t\treturn "+ nomeInterface.toLowerCase() +"." +mm.getNomeMetodo() +atributosMetodo);
					bw.append(System.lineSeparator());
					bw.append("\t}");
					bw.append(System.lineSeparator());
					bw.append(System.lineSeparator());
				}
			}
		}
		bw.append(System.lineSeparator());
		bw.append("}");
		bw.close();

	}

	private static String configuracaoBasica(ClsModel clsModel, String caminhoEspelhado) {
		nomeInterface = clsModel.getNomeCls();
		if (nomeInterface != null && nomeInterface.endsWith(".cls")) {
			nomeInterface = nomeInterface.substring(0, nomeInterface.indexOf(".cls"));
		}

		String packageJr = caminhoEspelhado.substring(caminhoEspelhado.indexOf("\\src") + 4 );
		packageJr = packageJr.replace("\\", ".");
		packageJr = packageJr.replace(".java", "");
		packageJr = packageJr.substring(1, packageJr.lastIndexOf(".")) + ";";
		packageJr = packageJr.replace("main.", "");
		return packageJr;
	}

	private static String configuracaoBasicaImport(ClsModel clsModel, String caminhoEspelhado) {
		nomeInterface = clsModel.getNomeCls();
		if (nomeInterface != null && nomeInterface.endsWith(".cls")) {
			nomeInterface = nomeInterface.substring(0, nomeInterface.indexOf(".cls"));
		}

		String packageJr = caminhoEspelhado.substring(caminhoEspelhado.indexOf("\\src") + 4 );
		packageJr = packageJr.replace("\\", ".");
		packageJr = packageJr.replace(".java", "");
		packageJr = packageJr.substring(1, packageJr.lastIndexOf(".")) + ";";
		packageJr = packageJr.replace("main.", "");
		packageJr = packageJr.substring(0, packageJr.lastIndexOf("."));
		return packageJr;
	}

}
