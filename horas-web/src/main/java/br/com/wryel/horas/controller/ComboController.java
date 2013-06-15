package br.com.wryel.horas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.wryel.horas.business.ClienteBusiness;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.util.FacesUtil;


@ViewScoped
@ManagedBean
public class ComboController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* INICIO CLIENTES */
	
	@EJB
	private ClienteBusiness clienteBusiness;
	
	private List<SelectItem> clientes = new ArrayList<>();
	
	public List<SelectItem> getClientes() {
		if (this.clientes.isEmpty()) {
			List<Cliente> clientes = clienteBusiness.list();
			for (Cliente cliente : clientes) {
				this.clientes.add(new SelectItem(cliente, cliente.getNome()));
			}
		}
		return this.clientes;
	}
	
	/* FIM CLIENTES */

	/* INICIO TIPOS USUARIO */
	
	private List<SelectItem> tiposUsuario = new ArrayList<>();

	/**
	 * @return the tiposUsuario
	 */
	public List<SelectItem> getTiposUsuario() {
		if (tiposUsuario.isEmpty()) {
			tiposUsuario.add(new SelectItem(Usuario.TIPO_USUARIO, FacesUtil.getInstance().getLabelKey("usuario.tipo.U")));
			tiposUsuario.add(new SelectItem(Usuario.TIPO_SUPERVISOR, FacesUtil.getInstance().getLabelKey("usuario.tipo.S")));
			tiposUsuario.add(new SelectItem(Usuario.TIPO_ADMINISTRADOR, FacesUtil.getInstance().getLabelKey("usuario.tipo.A")));
		}
		return tiposUsuario;
	}

	/**
	 * @param tiposUsuario the tiposUsuario to set
	 */
	public void setTiposUsuario(List<SelectItem> tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}
	
	/* FIM TIPOS USUARIO */
	
	/* INICO SIM NAO */
	
	private List<SelectItem> simNao = new ArrayList<>();

	/**
	 * @return the tiposUsuario
	 */
	public List<SelectItem> getSimNao() {
		if (simNao.isEmpty()) {
			simNao.add(new SelectItem(Usuario.ATIVO_SIM, FacesUtil.getInstance().getLabelKey(Usuario.ATIVO_SIM)));
			simNao.add(new SelectItem(Usuario.ATIVO_NAO, FacesUtil.getInstance().getLabelKey(Usuario.ATIVO_NAO)));
		}
		return simNao;
	}

	/**
	 * @param tiposUsuario the tiposUsuario to set
	 */
	public void setSimNao(List<SelectItem> simNao) {
		this.simNao = simNao;
	}
	
	/* INICO SIM NAO */
}
