package com.br.accava.acelerador.backend.modelo;

public class AtributoChamadaModel {

	private static String nomeAtributo;
	private static String tipoAtributo;
	
/*------------------------------- CONSTRUTOR ---------------------------*/	
	public AtributoChamadaModel() {
		super();
	}

/*-------------------------- GETTER/SETTER ------------------------------*/
	public static String getNomeAtributo() {
		return nomeAtributo;
	}
	public static void setNomeAtributo(String nomeAtributo) {
		AtributoChamadaModel.nomeAtributo = nomeAtributo;
	}
	public static String getTipoAtributo() {
		return tipoAtributo;
	}
	public static void setTipoAtributo(String tipoAtributo) {
		AtributoChamadaModel.tipoAtributo = tipoAtributo;
	}
	
	
}
