package br.com.wryel.horas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
	columnNames = "email", 
	name = "login_constraint"
)})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String TIPO_USUARIO = "U";
	
	public static final String TIPO_SUPERVISOR = "S";
	
	public static final String TIPO_ADMINISTRADOR = "A";
	
	public static final String ATIVO_SIM = "S";
	
	public static final String ATIVO_NAO = "N";
	
	@Id
	@GeneratedValue
	@Column(name = "id_usuario", columnDefinition = "INTEGER UNSIGNED")
	private Integer id;
	
	@ColumnTransformer(read = "DECODE(senha, 'WRYEL')", write = "ENCODE(?, 'WRYEL')")
	private String senha;
	
	private String email;
	
	private String nome;
	
	private String sobrenome;
	
	private String ativo;
	
	private String tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_de_cadastro")
	private Date dataDeCadastro;
	
	public Usuario() {
		
	}
	
	public Usuario(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}

	/**
	 * @param sobrenome the sobrenome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the dataDeCadastro
	 */
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	/**
	 * @param dataDeCadastro the dataDeCadastro to set
	 */
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", senha=" + senha 
				+ ", email=" + email + ", nome=" + nome + ", sobreNome="
				+ sobrenome + ", ativo=" + ativo + "]";
	}
}
