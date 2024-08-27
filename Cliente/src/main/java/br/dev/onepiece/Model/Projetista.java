package br.dev.onepiece.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Projetista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long idPro;
	
	private String nome;
	private String cnpj;
	private String email;
	private String telefone;
	private String cep;
	private String logradouro;
	private int numerolocal;
	private String cidade;
	private String UF;
	private String Complemento;
	
	
	
	public long getIdPro() {
		return idPro;
	}
	public void setIdPro(long idPro) {
		this.idPro = idPro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	} 
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String Logradouro) {
		this.logradouro = Logradouro;
	}
	public int getNumerolocal() {
		return numerolocal;
	}
	public void setNumerolocal(int numerolocal) {
		this.numerolocal = numerolocal;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getComplemento() {
		return Complemento;
	}
	public void setComplemento(String complemento) {
		Complemento = complemento;
	}
	
}
