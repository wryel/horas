package br.com.wryel.horas.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import br.com.wryel.horas.exception.HorasRuntimeException;

@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "2")
	}
)
public class GMailMessageListener implements MessageListener {

	@Resource(mappedName = "java:jboss/mail/gmail")
	private Session session;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {		
			Email email = (Email) objectMessage.getObject();
			enviar(email);			
		} catch (JMSException | MessagingException e) {
			throw new HorasRuntimeException("Ocorreu um erro ao tentar processar envio de e-mail" + e.getMessage(), e);
		}
	}
	
	private void enviar(Email email) throws MessagingException {

		javax.mail.Message message = new MimeMessage(session);

		message.setRecipient(RecipientType.TO, new InternetAddress(email.getDestinatario()));
		message.setSubject(email.getAssunto());
		message.setText(email.getConteudo());
		message.setSentDate(new Date());
		
		Transport.send(message);
		
	}
}
