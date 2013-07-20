package br.com.wryel.horas.business;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.dao.UsuarioDAO;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.UsuarioFilter;
import br.com.wryel.horas.service.Email;
import br.com.wryel.horas.service.EmailService;

@Stateless
public class UsuarioBusinessImpl extends BusinessImpl<Usuario, Integer, UsuarioFilter, UsuarioDAO> implements UsuarioBusiness {
	
	@EJB
	private EmailService emailService;
	
	@Resource(name = "email.assunto")
	private String assunto;
	
	@Resource(name = "email.corpo.template")
	private String corpoEmail;
	
	public UsuarioBusinessImpl() {
		super(Usuario.class);
	}
	
	private void validateRequiredInformation(Usuario entity) throws BusinessException {
		
		if (StringUtils.isBlank(entity.getNome()) || entity.getNome().length() <= 4) {
			throw new BusinessException("Nome precisa ter pelo menos 5 caracteres");
		}
	
		if (StringUtils.isBlank(entity.getEmail())) {
			throw new BusinessException("E-mail inválido");
		} else {
			try {
				InternetAddress internetAddress = new InternetAddress(entity.getEmail());
				internetAddress.validate();
			} catch (AddressException e) {
				throw new BusinessException("E-mail inválido");
			}
		}
		
		if (StringUtils.isBlank(entity.getAtivo())) {
			throw new BusinessException("Informe se o usuário está ativo ou inativo");
		}
		
		if (StringUtils.isBlank(entity.getSenha()) || entity.getSenha().length() <= 4) {
			throw new BusinessException("Senha precisa ter pelo menos 5 caracteres");
		}
		
		if (StringUtils.isBlank(entity.getTipo())) {
			throw new BusinessException("Tipo do usuário precisa ser informado");
		}
		
	}
	
	@Override
	protected boolean validateInsert(Usuario entity) throws BusinessException {
		
		validateRequiredInformation(entity);
		
		UsuarioFilter usuarioFilter = new UsuarioFilter();
		usuarioFilter.setEmailEquals(entity.getEmail());

		List<Usuario> usuarios = list(usuarioFilter);
		
		if (CollectionUtils.isNotEmpty(usuarios)) {
			throw new BusinessException("Já existe um usuário cadastrado com o e-mail informado");
		}
		
		return super.validateInsert(entity);		
	}
	
	@Override
	protected boolean validateUpdate(Usuario entity) throws BusinessException {

		validateRequiredInformation(entity);
		
		return super.validateUpdate(entity);
	}

	@Override
	public void insert(Usuario usuario) throws BusinessException {
		
		super.insert(usuario);
		
		Email email = new Email();
		
		email.setAssunto(assunto);
		
		email.setConteudo(MessageFormat.format(corpoEmail, usuario.getNome(), usuario.getSenha()));
		
		email.setDestinatario(usuario.getEmail());
		
		emailService.enviar(email);
		
	}
	
	@EJB
	@Override
	public void setDAO(UsuarioDAO entityDAO) {
		super.dao = entityDAO;
	}

	@Override
	public Usuario login(String email, String senha) {
		
		UsuarioFilter usuarioFilter = new UsuarioFilter();
		
		usuarioFilter.setEmailEquals(email);
		usuarioFilter.setSenhaEquals(senha);
		
		List<Usuario> usuarios = dao.list(usuarioFilter);
		
		Usuario usuario = null;
		
		if (CollectionUtils.isNotEmpty(usuarios)) {
			usuario = usuarios.get(0);
		}
		
		return usuario;
	}
}
