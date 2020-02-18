package com.br.accava.acelerador.backend.jab.estrutura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.br.accava.acelerador.backend.jab.modelo.AtributoMetodoModel;
import com.br.accava.acelerador.backend.jab.modelo.ChamadaModel;
import com.br.accava.acelerador.backend.jab.modelo.ClsModel;
import com.br.accava.acelerador.backend.jab.modelo.MetodoModel;
import com.br.accava.acelerador.intermediateutility.zip.ZipHandler;

/**@Complexidade 5/5*/
public class CriacaoEstruturaJab {

	
	private static int contadorCls = 0;
	private static int contadorMetodosPublicos = 0;
	private static int contadorChamadasProcOE = 0;
	
	/*----------------------------------------------------------------------------------------------------------------*/
	
	private static String pastaRaiz = "C:\\Users\\eduardo.m.daguano\\Desktop\\Componentes";
	private static final String ARQUIVO_SOLTO =  "C:\\Users\\eduardo.m.daguano\\Desktop\\ProjAcelerador\\DeParaRieOff.cls";

	/********************************************************************* PARA PROCS ********************************************************************/

	/*-------------------------------------- IDENTIFICADORES DE PROC --------------------------------------------------*/
	private static final String  IDENTIFICADOR_PROC_DECLARADA  =  "proc.";
	private static final String  IDENTIFICADOR_PROC_AVULSA 	   =  "\"pr_";

	private static final String  IDENTIFICADOR_PROC_DECLARADA_POR_CALL_INICIO = "\"{call";
	private static final String  IDENTIFICADOR_PROC_DECLARADA_POR_CALL_TERMINO = ")}\"";

	private static final String  IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_INICIO  = "with";
	private static final String  IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_MEIO    =  "=";
	private static final String  IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_TERMINO = "end with";

	private static final String  IDENTIFICADOR_ATRR_PROC_POR_CREATEPARAMETER = ".createparameter";
	private static final String IDENTIFICADOR_TERMINO_PROC_POR_ACTIVECON = "activeconnection";


	/*---------------------------------------------- CONTADORES/SINAL PROCS  -------------------------------------------*/
	private static boolean siglaComProcAvulsa = true;
	private static boolean siglaComChamadaProc = true;

	private static int contadorProcAvulsa = 0;
	private static boolean provavelProcMultiChamada = false;
	private static int contadorProcPorAtribuicao = 0;
	private static int contadorProcDeclaradaPorAtribuicao = 0;

	/*----------------------------------------------------- DETECTAR FUNCOES  -------------------------------------------*/
	private static final String DECLARACAO_FUNCAO_PUBLICA = "public function";
	private static final String DECLARACAO_FUNCAO_PRIVADA = "private function";
	private static final String DECLARACAO_SUB_PUBLICA = "public sub";
	private static final String DECLARACAO_SUB_PRIVADA = "private sub";
	private static final String DECLARACAO_FIM_FUNCAO = "end function";
	private static final String DECLARACAO_FIM_SUB = "end sub";

	private static int emDeclaracaoPublica = 0;
	private static int emDeclaracaoPrivada = 0;

	/*------------------------------------------- DETECTAR ATRIBUTOS FUNCOESS -------------------------------------------*/
	private static final String BYVAL = "ByVal";
	private static final String BYREF = "ByRef";


	/*------------------------------------------- DETECTAR ATRIBUTOS FUNCOESS -------------------------------------------*/
	private static final String IDENTIFICADOR_PROVAVEL_MAINFRAME = "\"oe";
	private static final String IDENTIFICADOR_CONFIRMACAO_MAINFRAME_PROGRAMA =  "prog";
	private static final String IDENTIFICADOR_CONFIRMACAO_MAINFRAME_TRANSACAO =  "tran";


	/*------------------------------------------------------- MODELOS ---------------------------------------------------*/
	private static ClsModel novoClsModelo = new ClsModel();
	private static List<ClsModel> listaArquivosCLS = new ArrayList<>();

	private static MetodoModel mmpu = new MetodoModel();
	private static List<MetodoModel> listaMetodoModeloPublico = new ArrayList<MetodoModel>();

	private static AtributoMetodoModel ammpu = new AtributoMetodoModel();
	private static List<AtributoMetodoModel> listaAtrrMMP = new ArrayList<AtributoMetodoModel>();

	/*--------------------------------------------------- MODELO PARA PROCS ----------------------------------------------*/
	private static ChamadaModel chamadaModeloProc = new ChamadaModel();
	private static List<ChamadaModel> listaChamadaModeloProc =  new ArrayList<ChamadaModel>();
	private static String atributosChamadaProc = "";

	/*-------------------------------------------------------- METODOS ---------------------------------------------------*/
	
	public static void main(String[] args) throws Exception {
		iniciarCriacaoDaEstrutura(new File(pastaRaiz));
		//		iniciaLeituraArquivoCls(new File(ARQUIVO_SOLTO));
		imprimirEstrutura();
	}

	public static List<ClsModel> iniciarCriacaoDaEstrutura(File pasta) throws Exception {
		for (File arquivo : pasta.listFiles()) {
			if(arquivo.isDirectory()) {
				iniciarCriacaoDaEstrutura(arquivo);
			} else if (arquivo.getAbsolutePath().endsWith(".cls")) {
				iniciaLeituraArquivoCls(arquivo);
			}
		}
		return listaArquivosCLS;

	}

	/**@Complexidade 2/5*/
	private static void iniciaLeituraArquivoCls(File arquivo) throws Exception {
		limpaUltimaEstruturaGerada();
		BufferedReader br = new BufferedReader(new FileReader(arquivo));
		String linha = br.readLine();

		while (linha != null) {

			if (linha.trim().equals("") || linha.trim().startsWith("'")) {
				linha = br.readLine();
			} else {
				String linhaComparativa = linha.toLowerCase();
				iniciaCriacaoDasEstruturas(linhaComparativa, linha);
				linha = br.readLine();
			}
		}

		gravaEstruturaCompletaArquivoCLS(arquivo);
	}

	private static void limpaUltimaEstruturaGerada() {
		listaMetodoModeloPublico = new ArrayList<MetodoModel>();
		novoClsModelo = new ClsModel();
		mmpu = new MetodoModel();
		listaMetodoModeloPublico = new ArrayList<MetodoModel>();
		ammpu = new AtributoMetodoModel();
		listaAtrrMMP = new ArrayList<AtributoMetodoModel>();
		chamadaModeloProc = new ChamadaModel();
		listaChamadaModeloProc =  new ArrayList<ChamadaModel>();
		atributosChamadaProc = "";
		emDeclaracaoPublica = 0;
		emDeclaracaoPrivada = 0;
		contadorProcPorAtribuicao = 0;
		contadorProcAvulsa = 0;
		contadorProcDeclaradaPorAtribuicao = 0;
	}

	private static void gravaEstruturaCompletaArquivoCLS(File arquivo) {
		String caminhoCLS = arquivo.getAbsolutePath();
		String nomeCLS    = arquivo.getAbsolutePath().substring(arquivo.getAbsolutePath().lastIndexOf("\\")+1);
		listaArquivosCLS.add(new ClsModel(caminhoCLS, nomeCLS, listaMetodoModeloPublico));
	}

	/**@Complexidade 1/5*/
	private static void iniciaCriacaoDasEstruturas(String linhaComparativa, String linhaOriginal) {
		verificaDeclaracaoMetodoPublico(linhaComparativa, linhaOriginal);
		construirAtributoMetodoModelo(linhaComparativa, linhaOriginal);
		construirChamadasMetodoModelo(linhaComparativa, linhaOriginal);
	}

	private static void verificaDeclaracaoMetodoPublico(String linhaComparativa, String linhaOriginal) {
		verificaInicioDeclaracaoMetodoPublico(linhaComparativa, linhaOriginal);
		verificaFimDeclaracaoMetodoPublico(linhaComparativa, linhaOriginal);
	}

	/**@Complexidade 3/5*/
	private static void verificaFimDeclaracaoMetodoPublico(String linhaComparativa, String linhaOriginal) {
		if ( (linhaComparativa.contains(DECLARACAO_FIM_FUNCAO) || linhaComparativa.contains(DECLARACAO_FIM_SUB)) && emDeclaracaoPublica > 0 ) {
			System.out.println("-> Terminando Leitura Estrutura -> Metodo Publico");
			System.out.println();

			emDeclaracaoPublica--;

			List<AtributoMetodoModel> listAtrr = new ArrayList<AtributoMetodoModel>();
			listAtrr = populaAtrrMetodoModelo(listAtrr);
			List<ChamadaModel> listCM = new ArrayList<>();
			listCM = popularChamadaModelo(listCM);
			listaMetodoModeloPublico.add(new MetodoModel(mmpu.getNomeMetodo(), mmpu.getDeclaracaoMetodo(), mmpu.getTipoMetodo(), listAtrr, listCM));
			listaChamadaModeloProc.clear();

			listaAtrrMMP.clear();
			mmpu = new MetodoModel();
		} 
	}

	/**@Complexidade 1/5*/
	private static void verificaInicioDeclaracaoMetodoPublico(String linhaComparativa, String linhaOriginal) {
		if (linhaComparativa.contains(DECLARACAO_FUNCAO_PUBLICA) || linhaComparativa.contains(DECLARACAO_SUB_PUBLICA) ) {
			System.out.println("-> Lendo Estrutura -> Metodo Publico");

			if (linhaOriginal.contains(BYVAL)) {
				extraiAtributosBYVAL(linhaOriginal);
			}
			if (linhaOriginal.contains(BYREF)) {
				extraiAtributosBYREF(linhaOriginal);
			}
			emDeclaracaoPublica++;
			construirModeloMetodo(linhaComparativa, linhaOriginal);
		} 
	} 

	/**@Complexidade 3/5*/
	private static MetodoModel construirModeloMetodo(String l, String lo) {
		mmpu = new MetodoModel();
		if (l.contains(DECLARACAO_FUNCAO_PUBLICA)) {
			mmpu.setNomeMetodo(lo.substring(lo.indexOf(DECLARACAO_FUNCAO_PUBLICA) + DECLARACAO_FUNCAO_PUBLICA.length()+1));
			mmpu.setTipoMetodo(DECLARACAO_FUNCAO_PUBLICA);
		} else if (l.contains(DECLARACAO_SUB_PUBLICA)) {
			mmpu.setNomeMetodo(lo.substring(lo.indexOf(DECLARACAO_SUB_PUBLICA) + DECLARACAO_SUB_PUBLICA.length()+1));
			mmpu.setTipoMetodo(DECLARACAO_SUB_PUBLICA);
		}
		if (l.contains("(")) {
			mmpu.setDeclaracaoMetodo(lo.substring(0, lo.indexOf("(")).trim());
			mmpu.setNomeMetodo(mmpu.getNomeMetodo().substring(0, mmpu.getNomeMetodo().indexOf("(")).trim());
		} else if (l.contains("[")) {
			mmpu.setDeclaracaoMetodo(lo.substring(0, lo.indexOf("[")));
			mmpu.setNomeMetodo(mmpu.getNomeMetodo().substring(0, mmpu.getNomeMetodo().indexOf("[")).trim());
		} else if (l.trim().contains("\t")) {
			mmpu.setDeclaracaoMetodo(lo.substring(0, lo.indexOf("\t")).trim());
			mmpu.setNomeMetodo(mmpu.getNomeMetodo().substring(0, mmpu.getNomeMetodo().indexOf("\t")).trim());
		}
		System.out.println("\t -"+mmpu.getNomeMetodo());
		System.out.println("\t -"+mmpu.getDeclaracaoMetodo());
		System.out.println("\t -"+mmpu.getTipoMetodo());
		return mmpu;
	}

	/**@Complexidade 2/5*/
	private static void construirAtributoMetodoModelo(String l, String lo) {
		if (emDeclaracaoPublica > 0 && emDeclaracaoPrivada <= 0) {
			ammpu = new AtributoMetodoModel();

			if (lo.trim().startsWith(BYVAL)) {
				extraiAtributosBYVAL(lo);
			}

			if (lo.trim().startsWith(BYREF)) {
				extraiAtributosBYREF(lo);
			}
		}
	}

	private static void extraiAtributosBYREF(String lo) {
		String aux = lo.substring(lo.indexOf(BYREF));
		aux = aux.substring(aux.indexOf(BYREF) + BYREF.length());

		recortaAtributoMetodo(aux);
		ammpu.setTipoAtributo(BYREF);

		System.out.println("\t \t -> " + ammpu.getTipoAtributo() + " : " + ammpu.getNomeAtributo());
		listaAtrrMMP.add(new AtributoMetodoModel(ammpu.getNomeAtributo(), ammpu.getTipoAtributo()));
	}

	private static void extraiAtributosBYVAL(String lo) {
		String aux = lo.substring(lo.indexOf(BYVAL));
		aux = aux.substring(aux.indexOf(BYVAL) + BYVAL.length());

		recortaAtributoMetodo(aux);
		ammpu.setTipoAtributo(BYVAL);

		System.out.println("\t \t -> " + ammpu.getTipoAtributo() + " : " + ammpu.getNomeAtributo());
		listaAtrrMMP.add(new AtributoMetodoModel(ammpu.getNomeAtributo(), ammpu.getTipoAtributo()));
	}

	
	/**@Complexidade 999/5 --- extremamente complexo
	 * 
	 * Existem 3 tratamentos de PROCS neste método, são eles: 
	 * (i)   Tratamento das chamadas via -> 	strSQL = "CALL PROC.XPTO"     ----> Proc de Call  		(Mono Linha e Multi Linha)  NÃO Existe COM  ".CreateParameter" 
	 * (ii)  Tratamento das chamadas via -> 	strSQL = "PROC.XPTO"		  ----> Proc de Atribuição  (Multi Linha)       		SÓ  Existe COM  ".CreateParameter"
	 * (iii) Tratamento das chamadas via -> 	cmdSQL = "pr_xpto"  	 	  ----> Proc Avulsa     	(Multi Linha) 				SÓ  Existe COM  ".CreateParameter" 
	 * 
	 * */
	private static void construirChamadasMetodoModelo(String l, String lo) {
		if (emDeclaracaoPublica > 0 && emDeclaracaoPrivada <= 0) {

			validaTransacaoLegado(l, lo);

			/*(iii) Proc Avulsa COM  ".CreateParameter"*/
			validaProcAvulsa(l);

			/* (i) e (ii) -  Proc por Atribuicao e Proc por Call*/
			importateEQueFunciona(l);

		}
	}


	/*-------------------------------------------- VERIFICACAO PARA PROCEDURES -------------------------------------*/

	private static void validaTransacaoLegado(String l, String lo) {
		if(l.contains(IDENTIFICADOR_PROVAVEL_MAINFRAME)) {
			String[] vazio = null;
			if (l.contains(IDENTIFICADOR_CONFIRMACAO_MAINFRAME_PROGRAMA)) {
				String programaMF = l.substring(l.indexOf("\"")+1);
				programaMF = programaMF.substring(0, programaMF.lastIndexOf("\""));
				listaChamadaModeloProc.add(new ChamadaModel(programaMF.toUpperCase(), l, "PROG MF", vazio));
			} else if (l.contains(IDENTIFICADOR_CONFIRMACAO_MAINFRAME_TRANSACAO)) {
				String programaMF = l.substring(l.indexOf("\"")+1);
				programaMF = programaMF.substring(0, programaMF.lastIndexOf("\""));
				listaChamadaModeloProc.add(new ChamadaModel(programaMF.toUpperCase(), l, "TRAN MF", vazio));

			}
		}
	}


	/*(iii) PROC AVULSA*/
	private static void validaProcAvulsa(String l) {
		if (siglaComProcAvulsa) {

			if (l.contains(IDENTIFICADOR_PROC_AVULSA) && contadorProcAvulsa <= 0) {
				contadorProcAvulsa++;
				String nomeChamada = l.substring(l.indexOf("\"")+1);
				if (nomeChamada.contains("\"")) {
					nomeChamada =  nomeChamada.substring(0, nomeChamada.lastIndexOf("\""));
					chamadaModeloProc.setNomeChamada(nomeChamada);
					chamadaModeloProc.setTipoChamada("PROC AVULSA");
					chamadaModeloProc.setAssinaturaChamada(l);

					System.out.println("\t\t\t" + nomeChamada);
				}
			} 

			if (contadorProcAvulsa > 0) {
				if (l.contains(IDENTIFICADOR_ATRR_PROC_POR_CREATEPARAMETER)) {
					recortaNomePrimeiroIdentificadorProc(l);
					System.out.println("\t\t\t ->" + atributosChamadaProc);
				}
			}

			if (contadorProcAvulsa > 0 && l.contains(IDENTIFICADOR_TERMINO_PROC_POR_ACTIVECON)) {
				String[] split = atributosChamadaProc.split(",");
				listaChamadaModeloProc.add(new ChamadaModel(chamadaModeloProc.getNomeChamada(), chamadaModeloProc.getAssinaturaChamada(), chamadaModeloProc.getTipoChamada(), split));
				atributosChamadaProc = "";
				chamadaModeloProc = new ChamadaModel();
				contadorProcAvulsa--;
			}

		}
	}

	/**Complexidade + de 8000 - Contempla os cenários (i) e (ii)*/
	private static void importateEQueFunciona(String l) {
		/* Tratamento para (i) e (ii) vide Doc do Metodo*/
		if (siglaComChamadaProc) {

			/*(ii) Proc de Atribuicao*/
			provavelProcMultiChamada = preProcessamentoProcsDeclaracaoPorAtribuicao(l);

			/*(i) Proc de Call NÃO Existe COM  ".CreateParameter"*/
			if (l.contains(IDENTIFICADOR_PROC_DECLARADA) && l.contains(IDENTIFICADOR_PROC_DECLARADA_POR_CALL_INICIO) ) {
				inicializacaoProcsPorCall(l);
			}

			/*(ii) Proc por Atribuicao - LINHA INICIAL*/
			else if (l.contains(IDENTIFICADOR_PROC_DECLARADA) && l.contains(IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_MEIO) && provavelProcMultiChamada) {
				String auxiliador = l.substring(l.indexOf(IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_MEIO));
				if (auxiliador.contains("\"") ) {
					auxiliador = auxiliador.substring(auxiliador.indexOf("\""));
					if (auxiliador.contains(IDENTIFICADOR_PROC_DECLARADA)) {
						auxiliador = auxiliador.substring(1);
						if(auxiliador.contains("\"")) {
							auxiliador = auxiliador.replace("\"", "");
						}
						chamadaModeloProc.setNomeChamada(auxiliador.trim());
						chamadaModeloProc.setAssinaturaChamada(l);
						chamadaModeloProc.setTipoChamada("PROC DECLARADA POR ATRIBUICAO");
						contadorProcPorAtribuicao++;
					}
				}

			} 

			/*(i) PROC por Atribuição - Linhas dos Parametros*/
			else if (l.contains("&") && !l.contains("call") && contadorProcDeclaradaPorAtribuicao > 0 ) {
				String aux = l.substring(l.indexOf("&"));
				if (aux.contains("_")) {
					aux =  aux.substring(0, aux.indexOf("_"));
					atributosChamadaProc = atributosChamadaProc + aux;
				} else if (aux.contains(IDENTIFICADOR_PROC_DECLARADA_POR_CALL_TERMINO)) {
					aux = aux.substring(0, aux.indexOf(IDENTIFICADOR_PROC_DECLARADA_POR_CALL_TERMINO));
					atributosChamadaProc = atributosChamadaProc + aux;
					String recorteAtrrProcMesmaLinha = recorteAtrrProcMesmaLinha(atributosChamadaProc);
					String[] split = recorteAtrrProcMesmaLinha.split(",");
					listaChamadaModeloProc.add(new ChamadaModel(chamadaModeloProc.getNomeChamada(), chamadaModeloProc.getAssinaturaChamada(), chamadaModeloProc.getTipoChamada(), split));

					chamadaModeloProc =  new ChamadaModel();
					atributosChamadaProc = "";
					contadorProcDeclaradaPorAtribuicao --;
				}
			} else if (contadorProcPorAtribuicao > 0 && provavelProcMultiChamada ) {
				if (l.contains(IDENTIFICADOR_ATRR_PROC_POR_CREATEPARAMETER)) {
					if (l.contains("(\"") && l.contains(",")) {
						recortaNomePrimeiroIdentificadorProc(l);

						System.out.println("\t\t\t ->" + atributosChamadaProc);
					}

				}
			} 
		}
	}

	/**Contempla o cenário (i)*/
	private static void inicializacaoProcsPorCall(String l) {
		/*Utilizacao de Procs por Call -> Inicio e Termino na mesma Linha*/
		if (l.contains(IDENTIFICADOR_PROC_DECLARADA_POR_CALL_TERMINO)) {
			if (l.contains("(")) {
				String aux = l.substring(l.indexOf("call") + "call".length(), l.indexOf("("));
				aux = aux.trim();
				String atributos = recorteAtrrProcMesmaLinha(l);
				atributos = atributos.trim();
				String[] split = atributos.split(",");
				listaChamadaModeloProc.add(new ChamadaModel(aux, l.trim(), "PROC DECLARADA POR CALL MONO LINHA", split));
				System.out.println("\t \t \t" + aux);
				System.out.println("\t \t \t" + atributos);
			}
		} 

		/*Utilizacao de Procs por Call -> Inicio e fim em linhas separadas*/
		else {
			if (l.contains("(") && contadorProcDeclaradaPorAtribuicao <= 0) {
				String nomeProc = l.substring(l.indexOf("call")+"call".length(), l.indexOf("("));
				nomeProc = nomeProc.trim();
				chamadaModeloProc.setNomeChamada(nomeProc);
				chamadaModeloProc.setAssinaturaChamada(l);
				chamadaModeloProc.setTipoChamada("PROC DECLARADA POR CALL MULTI LINHA");
				System.out.println("\t\t\t" + nomeProc);
			}
			if( l.contains("call") && l.contains("(\"") && contadorProcDeclaradaPorAtribuicao <= 0) {
				String atrrMesmaLinha = l.substring(l.indexOf("(\""));
				if (atrrMesmaLinha.contains("_")) {
					String aux = l.substring(l.indexOf("(\""), l.lastIndexOf("_"));
					atributosChamadaProc = atributosChamadaProc + aux;
					contadorProcDeclaradaPorAtribuicao++;
				} else {
					contadorProcDeclaradaPorAtribuicao++;
				}
			} 
		}
	}

	/**Contempla os cenários (i) e (ii)*/
	private static boolean preProcessamentoProcsDeclaracaoPorAtribuicao(String l) {
		if (l.trim().startsWith(IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_INICIO)) {
			provavelProcMultiChamada = true;
		}

		if (l.trim().contains(IDENTIFICADOR_PROC_DECLARADA_POR_ATRIBUICAO_TERMINO) && provavelProcMultiChamada && contadorProcPorAtribuicao > 0) {
			String[] split = atributosChamadaProc.split(",");
			listaChamadaModeloProc.add(new ChamadaModel(chamadaModeloProc.getNomeChamada(), chamadaModeloProc.getAssinaturaChamada(), chamadaModeloProc.getTipoChamada(), split));
			chamadaModeloProc = new ChamadaModel();
			atributosChamadaProc = "";
			contadorProcPorAtribuicao--;
			provavelProcMultiChamada = false;
		}

		return provavelProcMultiChamada;
	}


	/*---------------------------------------------- Metodos para Popular Modelos Proc --------------------------------------------------------*/

	private static List<AtributoMetodoModel> populaAtrrMetodoModelo(List<AtributoMetodoModel> atrrList) {
		atrrList = new ArrayList<AtributoMetodoModel>();
		for (AtributoMetodoModel atributoMetodoModel : listaAtrrMMP) {
			atrrList.add(atributoMetodoModel);
		}
		return atrrList;
	}

	private static List<ChamadaModel> popularChamadaModelo(List<ChamadaModel> listCM) {
		listCM = new ArrayList<>();

		for(ChamadaModel cm : listaChamadaModeloProc) {
			listCM.add(cm);
		}
		return listCM;
	}


	/*-------------------------------------------------- Recorte de String ------------------------------------------*/

	/**@Complexidade 3/5*/
	private static String recorteAtrrProcMesmaLinha(String atrr) {
		String recorte = atrr.substring(atrr.indexOf("call") + "call".length());
		recorte = recorte.substring(recorte.indexOf("(")+1, recorte.lastIndexOf(")"));

		char[] listaChar = recorte.toCharArray();
		String juntada = "";

		int contadorParenteses = 0;
		int contadorChaves = 0;
		int contadorAspas = 0;
		for(char c : listaChar) {
			if (c == '\"') {
				if (contadorAspas > 0) {
					contadorAspas--;
					continue;
				} else {
					contadorAspas++;
					continue;
				}
			}
			if (c == '(' || c == '{') {
				contadorParenteses++;
			}
			if (c == ')' || c == '}') {
				contadorParenteses--;
			}
			if (contadorParenteses != 0 || contadorAspas !=0 ) {
				if (c != ',') {
					juntada = (juntada + c).trim();
				} else {
					juntada = (juntada + "__").trim();
				}
			} else {
				juntada = (juntada + c).trim();
			}

		}
		return tratamentoAtrrProc(juntada);
	}

	private static String tratamentoAtrrProc(String juntada) {
		String novaJuntada = "";
		if (juntada != null && juntada.contains(",")) {
			String[] listaJuntada = juntada.split(",");
			for (String item: listaJuntada) {
				if(item.contains("null") || item.contains("mid") || item.contains("left") || item.contains("right") || item.contains("arrcampo")) {
					novaJuntada = novaJuntada + "?,";
				} else {
					novaJuntada = novaJuntada + item.trim() + ",";
				}
			}
			novaJuntada = novaJuntada.substring(0, novaJuntada.length()-1);
		}

		return novaJuntada;
	}

	private static void recortaNomePrimeiroIdentificadorProc(String l) {
		String nome = "";
		nome = l.substring(l.indexOf("(\"")+2, l.indexOf(","));
		if (nome.contains("\"")) {
			nome = nome.replace("\"", "");
		}
		if (nome.contains("@")) {
			nome =  nome.replace("@", "");
		}
		atributosChamadaProc =  atributosChamadaProc + nome +",";
	}

	private static void recortaAtributoMetodo(String aux) {
		if (aux.contains(",")) {
			aux = aux.substring(0, aux.indexOf(","));
			ammpu.setNomeAtributo(aux);
		} else if (aux.contains(")")) {
			aux = aux.substring(0, aux.indexOf(")"));
			ammpu.setNomeAtributo(aux);
		}
	}


	/*--------------------------------------------- METODOS PARA IMPRESSAO -----------------------------------------*/
	
	public static void imprimirEstrutura() {

		for (ClsModel clsModel : listaArquivosCLS) {
			if (clsModel != null) {
				contadorCls++;
				System.out.println("---------------------------------- Gerando Estrutura Lida --------------------------------------");
				System.out.println(clsModel.getCaminhoCls());
				System.out.println(clsModel.getNomeCls());
				System.out.println();
				if (clsModel.getListaMetodos() != null) {
					for(MetodoModel mm : clsModel.getListaMetodos()) {
						contadorMetodosPublicos++;
						System.out.println(mm.getDeclaracaoMetodo());
						System.out.println(mm.getNomeMetodo());
						if (mm.getListaAtributoMetodo() != null) {
							for (AtributoMetodoModel amm : mm.getListaAtributoMetodo()) {
								System.out.println(amm.getTipoAtributo() + " : " +  amm.getNomeAtributo());
							}
						}
						if (mm.getListaChamada() != null) {
							for (ChamadaModel cm : mm.getListaChamada()) {
								contadorChamadasProcOE++;
								if (cm.getTipoChamada().equals("PROG MF") || cm.getTipoChamada().equals("TRAN MF")) {
									System.out.println("\t "+ cm.getTipoChamada() +": " +cm.getNomeChamada() );
								} else {
									System.out.println("\t" +cm.getNomeChamada());
									System.out.println("\t" +cm.getTipoChamada());
									if (cm.getAtributos() != null) {
										for (String s : cm.getAtributos()) {
											System.out.println("\t \t" + s +",");
										}
									}
								}
							}
						}
						System.out.println();
					}
				}
				System.out.println("------------------------------- Termino da Geracao da Estrutura Lida ---------------------------");
			}

		}
		System.out.println("Arquivos CLS: " + contadorCls);
		System.out.println("Metodos Publicos: " + contadorMetodosPublicos);
		System.out.println("Contador de Chamadas a Procs e Transacooes OE: " + contadorChamadasProcOE);
	}

}
