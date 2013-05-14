package br.com.wryel.horas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.wryel.horas.business.ClienteBusiness;
import br.com.wryel.horas.entity.Cliente;


@ViewScoped
@ManagedBean
public class ComboController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* INICIO CLIENTES */
	
	@EJB
	private ClienteBusiness clienteBusiness;

	@PreDestroy
	public void debugPreDestroy() {
		System.out.println("Criando instancia de " + getClass().getSimpleName());
	}
	
	@PostConstruct 
	public void debugPostConstruct() {
		System.out.println("Removendo instancia de " + getClass().getSimpleName());
	}
	
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
}
