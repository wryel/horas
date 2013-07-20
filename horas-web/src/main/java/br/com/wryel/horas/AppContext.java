package br.com.wryel.horas;

public final class AppContext {
	
	public static final String ACAO = "acao";
	
	public static final String EDITAR = "editar";
	
	public static final String ADICIONAR = "adicionar";
	
	public static final String JSF_EXTENSION = "faces";
	
	public interface Session {
		
		public static final String USUARIO = "usuario";
		
		public static final int TIME_OUT = 30 * 60;
	}

	private AppContext() {
		
	}
}
