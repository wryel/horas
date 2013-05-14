package br.com.wryel.horas.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wryel.horas.entity.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		super(Usuario.class);
	}
	
	
}
