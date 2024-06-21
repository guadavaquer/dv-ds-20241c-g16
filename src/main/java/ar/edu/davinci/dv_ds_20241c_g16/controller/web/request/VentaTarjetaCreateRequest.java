package ar.edu.davinci.dv_ds_20241c_g16.controller.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaTarjetaCreateRequest {

	private Long clienteId;

	private String fecha;

	private Integer cantidadCuotas;

}
