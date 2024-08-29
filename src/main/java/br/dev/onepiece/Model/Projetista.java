package br.dev.onepiece.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Projetista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPro;

    @NotBlank(message = "Nome não pode estar vazio")
    @Column(unique = true, nullable = false)
    private String nome;

    @NotBlank(message = "CNPJ não pode estar vazio")
    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;

    @NotBlank(message = "Email não pode estar vazio")
    @Email(message = "Email deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Telefone não pode estar vazio")
    @Column(unique = true, nullable = false)
    private String telefone;

    @NotBlank(message = "CEP não pode estar vazio")
    private String cep;

    @NotBlank(message = "Logradouro não pode estar vazio")
    private String logradouro;

    @NotBlank(message = "Número do local não pode estar vazio")
    private String numerolocal;

    @NotBlank(message = "Cidade não pode estar vazia")
    private String cidade;

    @NotBlank(message = "UF não pode estar vazio")
    @Size(min = 2, max = 2, message = "UF deve conter 2 caracteres")
    private String UF;

    private String complemento;

    // Getters e Setters
    public Long getIdPro() {
        return idPro;
    }

    public void setIdPro(Long idPro) {
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

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumerolocal() {
        return numerolocal;
    }

    public void setNumerolocal(String numerolocal) {
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

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
