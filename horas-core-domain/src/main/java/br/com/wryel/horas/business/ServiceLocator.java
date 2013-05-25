package br.com.wryel.horas.business;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.wryel.horas.exception.HorasRuntimeException;

public final class ServiceLocator {

	private static final ServiceLocator INSTANCE = new ServiceLocator();
	
	public static ServiceLocator getInstance() {
		return INSTANCE;
	}
	
	public <EJB extends Business<?, ?, ?>> EJB lookup(Class<EJB> ejbInterface) {
		Context context = getContext();
		try {
			Object lookup = context.lookup("java:global/horas-ear/horas-core-0.0.1-SNAPSHOT/" + ejbInterface.getSimpleName() + "Impl");
			EJB ejb = ejbInterface.cast(lookup);
			return ejb;
		} catch (NamingException namingException) {
			throw new HorasRuntimeException("Erro ao tentar acessar objeto de negócio " + ejbInterface.getSimpleName(), namingException);
		}
	}
	
	private Context getContext() {
		try {
			Context context = new InitialContext();
			return context;
		} catch (NamingException namingException) {
			throw new HorasRuntimeException("Erro ao acessar servico de nomes", namingException);
		}
	}
	
	private ServiceLocator() {
		
	}
}
