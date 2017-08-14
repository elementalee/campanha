package com.campanha.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({"id", "dtInicioVigencia", "alterado"})
public class Campanha {

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty("Nome Da Campanha")
	private String nome;
	
	@JsonProperty("ID do Time do Coração")
	private String idTimeCoracao;
	
	private LocalDate dtInicioVigencia;
	
	@JsonProperty("Data de Vigência")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtFimVigencia;
	
	private boolean alterado;

	public Campanha() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(String idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	public LocalDate getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(LocalDate dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public LocalDate getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(LocalDate dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public boolean isAlterado() {
		return alterado;
	}

	public void setAlterado(boolean alterado) {
		this.alterado = alterado;
	}

}
