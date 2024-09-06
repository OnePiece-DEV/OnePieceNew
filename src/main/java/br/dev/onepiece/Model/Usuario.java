package br.dev.onepiece.Model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	@NotBlank(message = "Username não pode estar vazio")
	@Column(unique = true, nullable = false)
	private String username;

	@NotBlank(message = "Email não pode estar vazio")
	@Email(message = "Email deve ser válido")
	@Column(unique = true, nullable = false)
	private String email;

	@NotBlank(message = "Senha não pode estar vazia")
	@Column(nullable = false)
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "projetista_id")
	private Projetista projetista;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Projetista getProjetista() {
		return projetista;
	}

	public void setProjetista(Projetista projetista) {
		this.projetista = projetista;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Usuario() {
    }

    public Usuario(String username, String senha, String email) {
        this.username = username;
        this.senha = senha;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getSenha(), usuario.getSenha());
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addAttribute(String string, Usuario usuario) {
		// Método personalizado
	}

	// Outros métodos relevantes

}
