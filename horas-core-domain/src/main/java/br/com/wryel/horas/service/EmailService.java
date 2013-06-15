package br.com.wryel.horas.service;

import javax.ejb.Local;

@Local
public interface EmailService {
	
	public void enviar(Email email);
}
