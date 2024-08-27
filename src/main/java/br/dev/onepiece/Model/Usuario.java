package br.dev.onepiece.Model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	private String username;
	private String email;
	private String senha;
	
	 @ManyToOne
	    @JoinColumn(name = "projetista_id")
	    private Projetista projetista;

	

	public Projetista getProjetista() {
		return projetista;
	}

	public void setProjetista(Projetista projetista) {
		this.projetista = projetista;
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
	
		
	}
    
	//comentário 2
	

}
