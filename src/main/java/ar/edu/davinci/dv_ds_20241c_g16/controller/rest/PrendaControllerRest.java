package ar.edu.davinci.dv_ds_20241c_g16.controller.rest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dv_ds_20241c_g16.controller.TiendaAppRest;
import ar.edu.davinci.dv_ds_20241c_g16.controller.request.PrendaInsertRequest;
import ar.edu.davinci.dv_ds_20241c_g16.controller.request.PrendaUpdateRequest;
import ar.edu.davinci.dv_ds_20241c_g16.controller.response.PrendaResponse;
import ar.edu.davinci.dv_ds_20241c_g16.domain.Prenda;
import ar.edu.davinci.dv_ds_20241c_g16.exceptions.BusinessException;
import ar.edu.davinci.dv_ds_20241c_g16.mapper.PrendaMapper;
import ar.edu.davinci.dv_ds_20241c_g16.service.PrendaService;

@RestController
public class PrendaControllerRest extends TiendaAppRest {

	private final Logger LOGGER = LoggerFactory.getLogger(PrendaControllerRest.class);

	@Autowired
	private PrendaService service;

	private final PrendaMapper mapper = PrendaMapper.instance;


	/**
	 * Listar Prendas
	 */
	@GetMapping(path = "/prendas/all")
	public List<Prenda> getList(){
		LOGGER.info("Listado de todas las prendas");
		return service.list();
	}

	@GetMapping(path = "/prendas")
	public ResponseEntity<Page<PrendaResponse>> getList(Pageable pageable) {
		LOGGER.info("Listar todas las prendas paginadas");
		LOGGER.info("Pageable: " + pageable);


		Page<PrendaResponse> prendaResponse = null;
		Page<Prenda> prendas = null;

		try {
			prendas = service.list(pageable);
		} catch (Exception e) {
			LOGGER.error("Error: " +  e.getMessage());
			e.printStackTrace();
		}

		// Mapear Prendas a PrendaRespose
		// No lo vamos a hacer
		// Podría haber una clase por entidad que haga este pasaje de Objeto
		// de Modelo a Objeto DTO
		//for (Prenda prenda : prendas) {
		//	PrendaResponse response = PrendaResponse.builder()
		//			.id(prenda.getId())
		//			.descripcion(prenda.getDescripcion())
		//			.tipo(prenda.getTipo().toString())
		//			.precioBase(prenda.getPrecioBase())
		//			.build();
		//}

		// Mapear Prendas a PrendaResponse con Orika
		try {
			//prendaResponse = prendas.map(prenda -> mapper.map(prenda, PrendaResponse.class));

			LOGGER.info(">>>>> prendas >>>> " + prendas.toString());
			showPrendas(prendas);
			prendaResponse = prendas.map(mapper::mapToPrendaResponse);

			showPrendaResonse(prendaResponse);
			LOGGER.info(">>>>> prendaResponse >>>> " + prendaResponse.toString());

		} catch (Exception e) {
			LOGGER.error("Error: " +  e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(prendaResponse, HttpStatus.OK);
	}

	private void showPrendaResonse(Page<PrendaResponse> prendaResponse) {
		System.out.println(prendaResponse.getContent().toString());

	}

	private void showPrendas(Page<Prenda> prendas) {
		System.out.println(prendas.getContent().toString());

	}

	/**
	 * Buscar prenda por id
	 * @param id identificador del prenda
	 * @return retorna el prenda
	 */
	@GetMapping(path = "/prendas/{id}")
	public ResponseEntity<Object> getPrenda(@PathVariable Long id) {
		LOGGER.info("lista al prenda solicitado");

		PrendaResponse prendaResponse = null;
		Prenda prenda = null;
		try {

			prenda = service.findById(id);

		} catch (BusinessException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			//prendaResponse = mapper.map(prenda, PrendaResponse.class);
			prendaResponse = mapper.mapToPrendaResponse(prenda);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(prendaResponse, HttpStatus.OK);
	}

	/**
	 * Grabar una nueva prenda
	 *
	 * @param datosPrenda son los datos para una nueva prenda
	 * @return un prenda nueva
	 */
	@PostMapping(path = "/prendas")
	public ResponseEntity<PrendaResponse> createPrenda(@RequestBody PrendaInsertRequest datosPrenda) {
		Prenda prenda = null;
		PrendaResponse prendaResponse = null;

		// Convertir PrendaInsertRequest en Prenda
		try {
			//prenda = mapper.map(datosPrenda, Prenda.class);
			prenda = mapper.matToPrenda(datosPrenda);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		// Grabar el nuevo Prenda
		try {
			prenda = service.save(prenda);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

		// Convertir Prenda en PrendaResponse
		try {
			//prendaResponse = mapper.map(prenda, PrendaResponse.class);
			prendaResponse = mapper.mapToPrendaResponse(prenda);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		return new ResponseEntity<>(prendaResponse, HttpStatus.CREATED);
	}


	/**
	 * Modificar los datos de un prenda
	 *
	 * @param id identificador de una prenda
	 * @param datosPrenda datos a modificar de la prenda
	 * @return los datos de una prenda modificada
	 */
	@PutMapping("/prendas/{id}")
	public ResponseEntity<Object> updatePrenda(@PathVariable("id") long id,
			@RequestBody PrendaUpdateRequest datosPrenda) {

		Prenda prendaModifar = null;
		Prenda prendaNuevo = null;
		PrendaResponse prendaResponse = null;

		// Convertir PrendaInsertRequest en Prenda
		try {
			//prendaNuevo = mapper.map(datosPrenda, Prenda.class);
			prendaNuevo = mapper.matToPrenda(datosPrenda);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		try {

			prendaModifar = service.findById(id);

		} catch (BusinessException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		if (Objects.nonNull(prendaModifar)) {
			prendaModifar.setDescripcion(prendaNuevo.getDescripcion());
			prendaModifar.setTipo(prendaNuevo.getTipo());
			prendaModifar.setPrecioBase(prendaNuevo.getPrecioBase());
			// Grabar el Prenda Nuevo en Prenda a Modificar
			try {
				prendaModifar = service.update(prendaModifar);
			} catch (BusinessException e) {
				LOGGER.error(e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());

				e.printStackTrace();

				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			LOGGER.error("Prenda a modificar es null");

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		// Convertir Prenda en PrendaResponse
		try {
			//prendaResponse = mapper.map(prendaModifar, PrendaResponse.class);
			prendaResponse = mapper.mapToPrendaResponse(prendaModifar);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		return new ResponseEntity<>(prendaResponse, HttpStatus.CREATED);
	}

	/**
	 * Borrado de la  prenda
	 * @param id identificador de una prenda
	 * @return
	 */
	@DeleteMapping("/prendas/{id}")
	public ResponseEntity<HttpStatus> deletePrenda(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}


}
