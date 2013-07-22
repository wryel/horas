package br.com.wryel.horas.business;

import javax.ejb.Local;

import br.com.wryel.horas.entity.CadastroUsuario;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;

@Local
public interface UsuarioBusiness extends Business<Usuario, Integer, UsuarioFilter> {
	
	public Usuario login(String email, String senha);
	
	public void cadastrar(CadastroUsuario cadastroUsuario) throws BusinessException;
}
