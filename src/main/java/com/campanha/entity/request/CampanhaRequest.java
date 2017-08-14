package com.campanha.entity.request;

public class CampanhaRequest {
	
	private Long id;
    private String nome;
    private String idTimeCoracao;
	private String dtInicioVigencia;
	private String dtFimVigencia;

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
	public String getDtInicioVigencia() {
		return dtInicioVigencia;
	}
	public void setDtInicioVigencia(String dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}
	public String getDtFimVigencia() {
		return dtFimVigencia;
	}
	public void setDtFimVigencia(String dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}
    
}
