package br.com.wryel.horas.entity;

public class CadastroUsuario extends Usuario {

	private static final long serialVersionUID = 1L;

	private String confirmaSenha;
	
	private String confirmaEmail;

	/**
	 * @return the confirmaSenha
	 */
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	/**
	 * @param confirmaSenha the confirmaSenha to set
	 */
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	/**
	 * @return the confirmaEmail
	 */
	public String getConfirmaEmail() {
		return confirmaEmail;
	}

	/**
	 * @param confirmaEmail the confirmaEmail to set
	 */
	public void setConfirmaEmail(String confirmaEmail) {
		this.confirmaEmail = confirmaEmail;
	}
}
