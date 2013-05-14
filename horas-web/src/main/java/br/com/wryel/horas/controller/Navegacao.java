package br.com.wryel.horas.controller;

public final class Navegacao {
	
	public static final String ATUAL = "";
		
	public interface Cliente {
			
		public String LISTAGEM = "/view/cliente/listagem";
		
		public String ENTRADA = "/view/cliente/entrada";
	}
	
	public interface Projeto {
		
		public String LISTAGEM = "/view/projeto/listagem";
		
		public String ENTRADA = "/view/projeto/entrada";
	}
	
	public interface Demanda {
		
		public String LISTAGEM = "/view/demanda/listagem";
		
		public String ENTRADA = "/view/demanda/entrada";
	}
	
	public interface Apontamento {
		
		public String PESQUISAR_DEMANDA_PARA_LANCAR_APONTAMENTO = "/view/apontamento/pesquisarDemandaParaLancarApontamento";

		public String VISUALIZAR_APONTAMENTOS_DE_DEMANDA = "/view/apontamento/visualizarApontamentosDeDemanda";
		
		public String ENTRADA = "/view/apontamento/entrada";
		
	}
	
	private Navegacao() {
		
	}
}
