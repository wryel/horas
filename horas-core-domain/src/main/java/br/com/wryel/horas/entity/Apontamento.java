package br.com.wryel.horas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Apontamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_apontamento", columnDefinition = "INTEGER UNSIGNED")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_demanda", nullable = false, columnDefinition = "INTEGER UNSIGNED")
	private Demanda demanda;
	
	@Column(nullable = false)
	private String descricao;
	
	private Date inicio;
	
	private Date fim;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, columnDefinition = "INTEGER UNSIGNED")
	private Usuario usuario;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the demanda
	 */
	public Demanda getDemanda() {
		return demanda;
	}
	/**
	 * @param demanda the demanda to set
	 */

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the inicio
	 */
	public Date getInicio() {
		return inicio;
	}
	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return the fim
	 */
	public Date getFim() {
		return fim;
	}
	/**
	 * @param fim the fim to set
	 */
	public void setFim(Date fim) {
		this.fim = fim;
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Apontamento other = (Apontamento) obj;
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
		return "Apontamento [id=" + id + ", demanda=" + demanda
				+ ", descricao=" + descricao + ", inicio=" + inicio + ", fim="
				+ fim + ", usuario=" + usuario + "]";
	}
}
