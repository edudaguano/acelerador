package com.br.accava.acelerador.backend.jab.modelo;

import java.util.List;

public class ChamadaModel {
	
	private  String nomeChamada;
	private  String assinaturaChamada;
	private  String tipoChamada;
	private  String[] atributos;
	
/*------------------------------- CONSTRUTOR ---------------------------*/	
	public ChamadaModel() {
		super();
	}


	public ChamadaModel(String nomeChamada, String assinaturaChamada, String tipoChamada, String[] atributos) {
		super();
		this.nomeChamada = nomeChamada;
		this.assinaturaChamada = assinaturaChamada;
		this.tipoChamada = tipoChamada;
		this.atributos = atributos;
	}


	/*-------------------------- GETTER/SETTER ------------------------------*/
	public String getNomeChamada() {
		return nomeChamada;
	}
	
	public void setNomeChamada(String nomeChamada) {
		this.nomeChamada = nomeChamada;
	}
	
	public String getAssinaturaChamada() {
		return assinaturaChamada;
	}
	
	public void setAssinaturaChamada(String assinaturaChamada) {
		this.assinaturaChamada = assinaturaChamada;
	}
	
	public String getTipoChamada() {
		return tipoChamada;
	}
	
	public void setTipoChamada(String tipoChamada) {
		this.tipoChamada = tipoChamada;
	}


	public String[] getAtributos() {
		return atributos;
	}


	public void setAtributos(String[] atributos) {
		this.atributos = atributos;
	}


}
