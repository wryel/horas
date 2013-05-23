package br.com.wryel.horas.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.dao.ClienteDAO;
import br.com.wryel.horas.entity.Cliente;
import br.com.wryel.horas.entity.Demanda;
import br.com.wryel.horas.entity.Projeto;
import br.com.wryel.horas.entity.filter.ClienteFilter;

@Stateless
public class ClienteBusinessImpl extends BusinessImpl<Cliente, Integer, ClienteFilter, ClienteDAO> implements ClienteBusiness {
	
	@EJB
	private ProjetoBusiness projetoBusiness;
	
	public ClienteBusinessImpl() {
		super(Cliente.class);
	}

	@EJB
	@Override
	public void setDAO(ClienteDAO entityDAO) {
		this.dao = entityDAO;
	}
	
	@Override
	public void insert(Cliente cliente) throws BusinessException {

		if (StringUtils.isBlank(cliente.getNome())) {
			throw new BusinessException("O nome do cliente não pode ficar em branco");
		}
	
		ClienteFilter clienteFilter = new ClienteFilter();
		
		clienteFilter.setNomeEqual(cliente.getNome());
		
		List<Cliente> clientesComNomesIguais = list(clienteFilter);
		
		if (CollectionUtils.isNotEmpty(clientesComNomesIguais)) {
			throw new BusinessException("Já existe um cliente cadastrado com este nome");
		}
		
		super.insert(cliente);
	}
	
	@Override
	public void delete(Cliente cliente) throws BusinessException {
		
		Cliente clienteParaDeletar = dao.get(cliente.getId());
		
		List<Projeto> projetos = clienteParaDeletar.getProjetos();
		
		if (CollectionUtils.isNotEmpty(projetos)) {
			
			for (Projeto projeto : projetos) {
				
				List<Demanda> demandas = projeto.getDemandas();
				
				for (Demanda demanda : demandas) {
					
					if (CollectionUtils.isNotEmpty(demanda.getApontamentos())) {
						
						throw new BusinessException("Existem apontamentos cadastrados para a demanda \"" + demanda.getNome() + "\" do projeto \"" + projeto.getNome() + "\" do cliente \"" + cliente.getNome() + "\", não será possível deletar");
						
					}
					
				}
				
			}
			
		}
		
		super.delete(clienteParaDeletar);
		
	}
}
