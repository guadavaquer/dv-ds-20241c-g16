package ar.edu.davinci.dv_ds_20241c_g16.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ar.edu.davinci.dv_ds_20241c_g16.domain.Prenda;
import ar.edu.davinci.dv_ds_20241c_g16.domain.TipoPrenda;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class PrendaServiceTest {
	private final Logger LOGGER = LoggerFactory.getLogger(PrendaServiceTest.class);
	@Autowired
	private PrendaService prendaService;
	@Test
	void testList() {
		assertNotNull(prendaService, "El servico es nulo.");
		List<Prenda> prendas = prendaService.list();
		LOGGER.info("Prendas encontradas: " + prendas.size());
		assertNotNull(prendas, "prendas es nulo");
		assertTrue(prendas.size() > 0, "No existen prendas.");
	}
	@Test
	void testTipoPrenda() {
		List<TipoPrenda> list = prendaService.getTipoPrendas();
		LOGGER.info("Tipo Prendas encontradas: " + list.size());
		assertNotNull(list, "tipo prendas es nulo");
		assertTrue(list.size() == 8, "La cantidad de tipos es distinta");
	}
//	private final PrendaMapper mapper = PrendaMapper.instance;
//
//	@Test
//	void testMapToPrendaResponse() {
//		Prenda prenda = Prenda.builder()
//				.id(10L)
//				.descripcion("prueba dto")
//				.tipo(TipoPrenda.BUFANDA)
//				.precioBase(new BigDecimal(100.2))
//				.build();
//		LOGGER.info("Prenda: " + prenda.toString());
//
//		PrendaResponse prendaResponse = mapper.mapToPrendaResponse(prenda);
//		LOGGER.info("prendaResponse: " + prendaResponse.toString());
//	}
//
//	@Test
//	void testPrendaInsertRequestMatToPrenda() {
//		PrendaInsertRequest prendaInsertRequest = PrendaInsertRequest.builder()
//				.descripcion("prueba dto")
//				.tipo("BUFANDA")
//				.precioBase(new BigDecimal(100.2))
//				.build();
//		LOGGER.info("prendaInsertRequest: " + prendaInsertRequest.toString());
//
//		Prenda prenda = mapper.matToPrenda(prendaInsertRequest);
//		LOGGER.info("Prenda: " + prenda.toString());
//	}
//
//	@Test
//	void testPrendaUpdateRequestMatToPrenda() {
//		PrendaUpdateRequest prendaUpdateRequest = PrendaUpdateRequest.builder()
//				.descripcion("prueba dto")
//				.tipo("BUFANDA")
//				.precioBase(new BigDecimal(100.2))
//				.build();
//		LOGGER.info("prendaUpdateRequest: " + prendaUpdateRequest.toString());
//
//		Prenda prenda = mapper.matToPrenda(prendaUpdateRequest);
//		LOGGER.info("Prenda: " + prenda.toString());
//	}
}

