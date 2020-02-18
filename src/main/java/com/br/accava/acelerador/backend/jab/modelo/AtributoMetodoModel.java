package com.br.accava.acelerador.backend.jab.modelo;

public class AtributoMetodoModel {

	private  String nomeAtributo;
	private  String tipoAtributo;

	/*------------------------------- CONSTRUTOR ---------------------------*/	
	public AtributoMetodoModel() {
		super();
	}

	public AtributoMetodoModel(String nomeAtributo, String tipoAtributo) {
		super();
		this.nomeAtributo = nomeAtributo;
		this.tipoAtributo = tipoAtributo;
	}

	/*-------------------------- GETTER/SETTER ------------------------------*/
	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public String getTipoAtributo() {
		return tipoAtributo;
	}

	public void setTipoAtributo(String tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}




}
