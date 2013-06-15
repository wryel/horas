package br.com.wryel.horas.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import br.com.wryel.horas.exception.HorasRuntimeException;

@Stateless
public class EmailServiceImpl implements EmailService {

	@Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName = "java:/queue/test")
	private Destination destination;
	
	@Override
	public void enviar(Email email) {
		
		try {
			
			Connection connection = connectionFactory.createConnection();
			
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer messageProducer = session.createProducer(destination);
			
			ObjectMessage objectMessage = session.createObjectMessage();		
		
			objectMessage.setObject(email);
			
			messageProducer.send(objectMessage);
			
		} catch (JMSException jmsException) {
			
			throw new HorasRuntimeException("Ocorreu um erro ao tentar conectar na fila de mensagens " + jmsException.getMessage(), jmsException);
			
		}
		
	}
}
