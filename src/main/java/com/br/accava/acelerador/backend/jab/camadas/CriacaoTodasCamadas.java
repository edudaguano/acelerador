package com.br.accava.acelerador.backend.jab.camadas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.br.accava.acelerador.backend.jab.estrutura.CriacaoEstruturaJab;
import com.br.accava.acelerador.backend.jab.modelo.ClsModel;
import com.br.accava.acelerador.intermediateutility.zip.ZipHandler;

public class CriacaoTodasCamadas {

	private static String pastaRaiz = "C:\\Users\\eduardo.m.daguano\\Desktop\\Componentes";

	private static final String SIGLA = "ibpj";
	private static final String SIGLA_REPLACE =  "\\nq";
	private static final String SIGLA_RAIZ_REPLACE =  "jab-xx-srv";

	private static String caminhoJabBackZip = "C:\\Users\\eduardo.m.daguano\\Desktop\\workspace\\acelerador\\src\\main\\resources\\proj_referencia\\jab.zip";
	private static String caminhoEspelhado = "C:\\Users\\eduardo.m.daguano\\Desktop\\Espelho\\";

	private static List<ClsModel> listaArquivosCLS = new ArrayList<>();

	/*------------------------------------ Modelos -------------------------------------*/
	private static CriacaoDaoService daoService =  new CriacaoDaoService();
	private static final String pacoteDaoService = caminhoEspelhado + "jab-ibpj-srv\\src\\main\\java\\com\\altec\\bsbr\\app\\jab\\ibpj\\dao ";

	private static CriacaoDaoImpl    daoImpl    =  new CriacaoDaoImpl();
	private static final String pacoteDaoImpl = "";

	private static CriacaoService     service      =  new CriacaoService();
	private static final String pacoteService = "";

	private static CriacaoServiceImpl serviceImpl  =  new CriacaoServiceImpl();
	private static final String pacoteServiceImpl = "";

	private static CriacaoEndPoint enpoint =  new CriacaoEndPoint();
	private static final String pacoteEndPoint = "";

	private static CriacaoWebService webservice =  new CriacaoWebService();
	private static final String pacoteWebService = "";

	private static boolean estruturaJab = true;

	public static void main(String[] args) throws Exception {
		unzipProject();
		renameProject();
		System.out.println("Iniciando remoção dos arquivos antigos");
		deleteOldProjectFiles(new File(caminhoEspelhado+"jab-"+SIGLA+"-srv"));

		listaArquivosCLS = CriacaoEstruturaJab.iniciarCriacaoDaEstrutura(new File(pastaRaiz));
		CriacaoEstruturaJab.imprimirEstrutura();
		

		for (ClsModel clsModelo: listaArquivosCLS) {
			String caminhoDaoService =  caminhoEspelhado + "jab-"+SIGLA+ "-srv\\src\\main\\java\\com\\altec\\bsbr\\app\\jab\\"+ SIGLA+"\\dao";
			caminhoDaoService = caminhoDaoService + "\\" + clsModelo.getNomeCls();
			caminhoDaoService = caminhoDaoService.replace(".cls", "Dao.java");
			daoService.criarDaoService(clsModelo, caminhoDaoService);
		}
		
		for (ClsModel clsModel : listaArquivosCLS) {
			String caminhoDaoImpl =  caminhoEspelhado + "jab-"+SIGLA+ "-srv\\src\\main\\java\\com\\altec\\bsbr\\app\\jab\\"+ SIGLA+"\\dao\\impl";
			caminhoDaoImpl = caminhoDaoImpl + "\\" + clsModel.getNomeCls().replace(".cls", "DaoImpl.java");
			daoImpl.criarDaoImpl(clsModel, caminhoDaoImpl);
		}

		for (ClsModel clsModelo: listaArquivosCLS) {
			String caminhoService =  caminhoEspelhado + "jab-"+SIGLA+ "-srv\\src\\main\\java\\com\\altec\\bsbr\\app\\jab\\"+ SIGLA+"\\service";
			caminhoService = caminhoService + "\\" + clsModelo.getNomeCls();
			caminhoService = caminhoService.replace(".cls", "Service.java");
			service.criarService(clsModelo, caminhoService);
		}

		for (ClsModel clsModel : listaArquivosCLS) {
			String caminhoServiceImpl =  caminhoEspelhado + "jab-"+SIGLA+ "-srv\\src\\main\\java\\com\\altec\\bsbr\\app\\jab\\"+ SIGLA+"\\service\\impl";
			caminhoServiceImpl = caminhoServiceImpl + "\\" + clsModel.getNomeCls().replace(".cls", "ServiceImpl.java");
			serviceImpl.criarServiceImpl(clsModel, caminhoServiceImpl);
		}
		
	}



	private static void renameProject() {
		String pathname;
		File espelho = new File(caminhoEspelhado);
		alteracaoParaSigla(espelho);
	}


	private static void unzipProject() {
		ZipHandler zipHandler = new ZipHandler(caminhoJabBackZip, caminhoEspelhado);
		zipHandler.unzipFile();
	}


	public static void alteracaoParaSigla(File pasta) {
		for (File arq : pasta.listFiles()) {
			if (arq.isDirectory()) {
				if (arq.getAbsolutePath().endsWith(SIGLA_RAIZ_REPLACE)) {
					String nomeReplace = arq.getAbsolutePath();
					nomeReplace = nomeReplace.replace(SIGLA_RAIZ_REPLACE, "jab-"+SIGLA+"-srv");
					arq.renameTo(new File(nomeReplace));
					arq = new File(nomeReplace);
				}
				if (arq.getAbsolutePath().endsWith(SIGLA_REPLACE)) {
					String nomeReplace = arq.getAbsolutePath();
					nomeReplace = nomeReplace.replace(SIGLA_REPLACE, "\\"+SIGLA);
					arq.renameTo(new File(nomeReplace));
					arq = new File(nomeReplace);
				}
				alteracaoParaSigla(arq);
			}
		}
	}

	private static void deleteOldProjectFiles(File pasta) {
		for (final File f : pasta.listFiles()) {
			if (f.isDirectory()) {
				deleteOldProjectFiles(f);
			}
			else {
				System.out.println(f.getAbsolutePath());
				if (f.getAbsolutePath().endsWith(".java") || f.getAbsolutePath().endsWith(".orig")) {
					f.delete();
				}
			}
		}

	}


}
