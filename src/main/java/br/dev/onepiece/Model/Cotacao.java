package br.dev.onepiece.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Cotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCotacao;

    @NotNull(message = "Data de recebimento não pode ser nula")
    private Date dataRecebimento;

    @NotBlank(message = "Descrição não pode estar vazia")
    @Size(max = 700, message = "Descrição não pode ter mais de 700 caracteres")
    private String descricao;

    @Size(max = 255, message = "Caminho do arquivo não pode ter mais de 255 caracteres")
    private String caminhoArquivo; // Caminho ou URL do arquivo no sistema de arquivos ou serviço de armazenamento

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nome da coluna estrangeira
    @NotNull(message = "Cliente não pode ser nulo")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "projetista_id") // Nome da coluna estrangeira
    @NotNull(message = "Projetista não pode ser nulo")
    private Projetista projetista;

    // Getters e Setters

    public Long getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(Long idCotacao) {
        this.idCotacao = idCotacao;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Projetista getProjetista() {
        return projetista;
    }

    public void setProjetista(Projetista projetista) {
        this.projetista = projetista;
    }
}
