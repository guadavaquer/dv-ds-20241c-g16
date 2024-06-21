package ar.edu.davinci.dv_ds_20241c_g16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.davinci.dv_ds_20241c_g16.domain.Venta;
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
