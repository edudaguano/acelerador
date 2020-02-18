package com.br.accava.acelerador.backend.jab.modelo;

import java.util.List;

public class MetodoModel {

	private  String nomeMetodo;
	private  String declaracaoMetodo;
	private  String tipoMetodo;
	private  List<AtributoMetodoModel> listaAtributoMetodo;
	private  List<ChamadaModel> listaChamada;
	
/*------------------------------- CONSTRUTOR ---------------------------*/	
	public MetodoModel() {
		super();
	}


	public MetodoModel(String nomeMetodo, String declaracaoMetodo, String tipoMetodo,
			List<AtributoMetodoModel> listaAtributoMetodo, List<ChamadaModel> listaChamada) {
		super();
		this.nomeMetodo = nomeMetodo;
		this.declaracaoMetodo = declaracaoMetodo;
		this.tipoMetodo = tipoMetodo;
		this.listaAtributoMetodo = listaAtributoMetodo;
		this.listaChamada = listaChamada;
	}
	

/*-------------------------- GETTER/SETTER ------------------------------*/

	public String getNomeMetodo() {
		return nomeMetodo;
	}

	public void setNomeMetodo(String nomeMetodo) {
		this.nomeMetodo = nomeMetodo;
	}

	public String getDeclaracaoMetodo() {
		return declaracaoMetodo;
	}

	public void setDeclaracaoMetodo(String declaracaoMetodo) {
		this.declaracaoMetodo = declaracaoMetodo;
	}

	public String getTipoMetodo() {
		return tipoMetodo;
	}

	public void setTipoMetodo(String tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}

	public List<AtributoMetodoModel> getListaAtributoMetodo() {
		return listaAtributoMetodo;
	}

	public void setListaAtributoMetodo(List<AtributoMetodoModel> listaAtributoMetodo) {
		this.listaAtributoMetodo = listaAtributoMetodo;
	}

	public List<ChamadaModel> getListaChamada() {
		return listaChamada;
	}

	public void setListaChamada(List<ChamadaModel> listaChamada) {
		this.listaChamada = listaChamada;
	}
	
	
}
