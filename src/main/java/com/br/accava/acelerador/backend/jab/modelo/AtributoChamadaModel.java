package com.br.accava.acelerador.backend.jab.modelo;

public class AtributoChamadaModel {

	private  String nomeAtributo;
	private  String tipoAtributo;
	
/*------------------------------- CONSTRUTOR ---------------------------*/	
	public AtributoChamadaModel() {
		super();
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
