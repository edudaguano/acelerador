package com.br.accava.acelerador.backend.modelo;

import java.util.List;

public class ClsModel {

	private String caminhoCls;
	private String nomeCls;
	private List<MetodoModel> listaMetodos;

	
/*------------------------------- CONSTRUTOR ---------------------------*/	
	public ClsModel() {
		super();
	}

	public ClsModel(String caminhoCls, String nomeCls, List<MetodoModel> listaMetodos) {
		super();
		this.caminhoCls = caminhoCls;
		this.nomeCls = nomeCls;
		this.listaMetodos = listaMetodos;
	}
	
/*-------------------------- GETTER/SETTER ------------------------------*/

	public String getCaminhoCls() {
		return caminhoCls;
	}

	public void setCaminhoCls(String caminhoCls) {
		this.caminhoCls = caminhoCls;
	}

	public String getNomeCls() {
		return nomeCls;
	}

	public void setNomeCls(String nomeCls) {
		this.nomeCls = nomeCls;
	}

	public List<MetodoModel> getListaMetodos() {
		return listaMetodos;
	}

	public void setListaMetodos(List<MetodoModel> listaMetodos) {
		this.listaMetodos = listaMetodos;
	}

}
