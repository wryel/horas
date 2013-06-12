package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import br.com.wryel.horas.dao.ApontamentoDAO;
import br.com.wryel.horas.dto.Somatoria;
import br.com.wryel.horas.entity.Apontamento;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Usuario;
import br.com.wryel.horas.entity.filter.ApontamentoFilter;

@Stateless
public class ApontamentoBusinessImpl extends BusinessImpl<Apontamento, Integer, ApontamentoFilter, ApontamentoDAO> implements ApontamentoBusiness {

	@EJB
	private DemandaBusiness demandaBusiness;
	
	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	public ApontamentoBusinessImpl() {
		super(Apontamento.class);
	}

	@EJB
	@Override
	public void setDAO(ApontamentoDAO entityDAO) {
		this.dao = entityDAO;
	}

	@Override
	public void insert(Apontamento apontamento) throws BusinessException {	
		if (apontamento.getDemanda() != null) {
			Demanda demanda = demandaBusiness.get(apontamento.getDemanda().getId());
			apontamento.setDemanda(demanda);
		}
		if (apontamento.getUsuario() != null) {	
			Usuario usuario = usuarioBusiness.get(apontamento.getUsuario().getId());
			apontamento.setUsuario(usuario);
		}		
		super.insert(apontamento);
	}
	
	@Override
	protected boolean validateInsert(Apontamento apontamento) throws BusinessException {
		if (apontamento.getDemanda() == null) {
			throw new BusinessException("Uma demanda é requerida para lançar apontamento");
		}
		return super.validateInsert(apontamento);
	}
	
	@Override
	public List<Apontamento> listApontamentosPorDemanda(Demanda demanda) {
		List<Apontamento> apontamentos = dao.listApontamentosPorDemanda(demanda);
		return apontamentos;
	}

	@Override
	public Somatoria somar(ApontamentoFilter apontamentoFilter) {
		
		List<Apontamento> apontamentos = list(apontamentoFilter);
		
		Somatoria somatoria = new Somatoria();
		
		Duration duration = Duration.ZERO;
		
		for (Apontamento apontamento : apontamentos) {
			
			if (apontamento.getInicio() != null && apontamento.getFim() != null) {

				Duration durationToPlus = new Duration(apontamento.getInicio().getTime(), apontamento.getFim().getTime());
				
				duration = duration.plus(durationToPlus);
				
			}
			
		}
		
		PeriodFormatter periodFormatter = 
			new PeriodFormatterBuilder()
				.printZeroAlways()
				.minimumPrintedDigits(2)
				.appendHours()
				.appendSeparator(":")
				.appendMinutes()
				.toFormatter();

		somatoria.setResultado(duration.toPeriod().toString(periodFormatter));
		
		return somatoria;
	}	
}
