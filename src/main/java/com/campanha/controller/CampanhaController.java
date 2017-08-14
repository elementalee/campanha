package com.campanha.controller;

import com.campanha.entity.Campanha;
import com.campanha.entity.request.CampanhaRequest;
import com.campanha.exception.CampanhaException;
import com.campanha.exception.DataException;
import com.campanha.repository.CampanhaRepository;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class CampanhaController {

	private CampanhaRepository campanhaRepo;
	
	private final  DateTimeFormatter DTF =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public CampanhaController(CampanhaRepository campanhaRepo) {
		this.campanhaRepo = campanhaRepo;
	}

	/**
	 * Obtêm todas as campanhas - sem filtro.
	 * @return todas campanhas cadastradas
	 */
	@RequestMapping(value = "/todasCampanhas", method = RequestMethod.GET)
	public List<Campanha> buscaTodasCampanhas() {
		return filtrarCampanhasVencidas(campanhaRepo.findAll());
	}

	/**
	 * 
	 * @param id da campanha - Opcional - Ex: http://localhost:8080/campanhas?id=42
	 * @param idCampanha - Opcional - Ex: http://localhost:8080/campanhas/42
	 * @return campanha por ID informada - no caso de não informar obterá todas campanhas.
	 */
	@RequestMapping(value = { "/campanhas", "/campanhas/idCampanha}" }, method = RequestMethod.GET)
	public List<Campanha> buscaCampanhaPorId(
						@RequestParam(value = "id", required = false) Optional<Long> id, 
						@PathVariable(value = "idCampanha", required = false) Optional<Long> idCampanha) {
		
		if (id.isPresent()) {
			return filtrarCampanhasVencidas(Arrays.asList(campanhaRepo.findOne(id.get())));
		} else if (idCampanha.isPresent()) {
			return filtrarCampanhasVencidas(Arrays.asList(campanhaRepo.findOne(idCampanha.get())));
		} else {
			return buscaTodasCampanhas();
		}
	}

	/**
	 * 
	 * @param campanhas
	 * @return campanhas não vencidas
	 */
	private List<Campanha> filtrarCampanhasVencidas(List<Campanha> campanhas) {
		List<Campanha> campanhasNaoVencidas = new ArrayList<Campanha>();
		for(Campanha c : campanhas) {
			if (!c.getDtInicioVigencia().isAfter((LocalDate.now()))) {
				campanhasNaoVencidas.add(c);
			}
		}
		return campanhasNaoVencidas;
	}

	/**
	 * Requisição para incluir nova campanha
	 * @param campanhaReq - objeto da requisição para inclusão
	 * @throws FormatoDataInvalidaException 
	 * @throws DataException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void incluirCampanha(@RequestBody CampanhaRequest campanhaReq) throws DataException {
		Campanha campanha = new Campanha();
		campanha.setNome(campanhaReq.getNome());
		campanha.setIdTimeCoracao(campanhaReq.getIdTimeCoracao());

		if(!StringUtils.isEmpty(campanhaReq.getDtInicioVigencia()) && !StringUtils.isEmpty(campanhaReq.getDtFimVigencia())) {
			campanha.setDtInicioVigencia(LocalDate.parse(campanhaReq.getDtInicioVigencia(), DTF));
			campanha.setDtFimVigencia(LocalDate.parse(campanhaReq.getDtFimVigencia(), DTF));
			atualizarDataVigenciaDuplicada(campanha, campanhaRepo.findAll());
			campanhaRepo.save(campanha);
		} else {
			throw new DataException("Periodo de vigência não informada.");
		}
	}

	/**
	 * Atualiza todas datas finais que possam comflitar
	 * @param campanha
	 * @param campanhasExistentes 
	 */
	private void atualizarDataVigenciaDuplicada(Campanha campanha, List<Campanha> campanhasExistentes) {
		for (Campanha c : campanhasExistentes) {
			if (c.getDtFimVigencia().isEqual(campanha.getDtFimVigencia()) && (campanha.getId() == null || c.getId().compareTo(campanha.getId()) != 0)) {
				c.setDtFimVigencia(c.getDtFimVigencia().plusDays(1l));
				c.setAlterado(true);
				atualizarDataVigenciaDuplicada(c, campanhasExistentes);
			} 
		}
		
		campanhaRepo.save(campanhasExistentes);
	}

	/**
	 * Exclui a campanha a partir de um ID da campanha.
	 * @param id da campanha a ser excluida - Ex: localhost:8080/campanhas/excluir?id=42
	 * @param idCampanha - id da campanha a ser excluida - Ex: localhost:8080/campanhas/excluir/42
	 */
	@RequestMapping(value = { "/campanhas/excluir", "/campanhas/excluir/{idCampanha}" }, method = RequestMethod.DELETE)
	public void excluirCampanha(
								@RequestParam(value = "id", required = false) Optional<Long> id,  
								@PathVariable(value = "idCampanha", required = false) Optional<Long> idCampanha) {  

		if (id.isPresent()) {
			campanhaRepo.delete(id.get());
		} else if (idCampanha.isPresent()) {
			campanhaRepo.delete(idCampanha.get());
		}
	}

	/**
	 * Apenas será atualizado caso a campanha da requisição esteja em nossa base de dados. 
	 * @param id
	 * @param idCampanha
	 * @param campanhaReq
	 * @throws CampanhaException 
	 */
	@RequestMapping(value = { "/campanhas/atualizar", "/campanhas/atualizar/{idCampanha}" }, method = RequestMethod.PUT)
	public void atualizarCampanha(
			@RequestParam(value = "id", required = false) Optional<Long> id,  
			@PathVariable(value = "idCampanha", required = false) Optional<Long> idCampanha,
			@RequestBody CampanhaRequest campanhaReq) throws CampanhaException {

		Campanha campanha = new Campanha();
		
		if (id.isPresent()) {
			campanha = campanhaRepo.findOne(id.get());
		} else if (idCampanha.isPresent()) {
			campanha = campanhaRepo.findOne(idCampanha.get());
		}

		if (campanha != null && campanha.getId() > 0) {
			campanha.setNome(campanhaReq.getNome());
			campanha.setIdTimeCoracao(campanhaReq.getIdTimeCoracao());
			campanhaRepo.save(campanha);
		} else {
			throw new CampanhaException("Campanha de id: (:" + id + ":" + idCampanha + ") inexistente. Não foi possível atualizar." );
		}
	}
}
