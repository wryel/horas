package br.com.wryel.horas.dto;

import java.io.Serializable;

public class Somatoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String resultado;
	
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return resultado;
	}
}
