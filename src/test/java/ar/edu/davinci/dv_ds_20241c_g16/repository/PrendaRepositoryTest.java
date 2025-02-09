package ar.edu.davinci.dv_ds_20241c_g16.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
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
class PrendaRepositoryTest {
	private final Logger LOGGER = LoggerFactory.getLogger(PrendaRepositoryTest.class);
	@Autowired
	private PrendaRepository prendaRepository;
	@Test
	void testFindAll() {
		assertNotNull(prendaRepository, "El repositorio es nulo.");
		List<Prenda> prendas = prendaRepository.findAll();
		LOGGER.info("Prendas encontradas: " + prendas.size());
		assertNotNull(prendas, "prendas es nulo");
		assertTrue(prendas.size() > 0, "No existen prendas.");
	}
	@Test
	void testFindById() {
		Long id = 4L;
		Prenda prenda = null;
		Optional<Prenda> prendaOptional = prendaRepository.findById(id);
		if (prendaOptional.isPresent()) {
			prenda = prendaOptional.get();
			LOGGER.info("Prenda encontrada para el id: " + prenda.getId());
			LOGGER.info("Prenda Tipo: " + prenda.getTipo());
			LOGGER.info("Prenda Tipo.Descripcion: " + prenda.getTipo().getDescripcion());
			assertEquals(TipoPrenda.PANTALON, prenda.getTipo());
		} else {
			LOGGER.info("Prenda no encontrada para el id: " + id);
			prenda = prendaOptional.get();
			assertNull(prenda);
		}
	}
}
