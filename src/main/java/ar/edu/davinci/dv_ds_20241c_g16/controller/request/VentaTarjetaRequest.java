package ar.edu.davinci.dv_ds_20241c_g16.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaTarjetaRequest {

	private Long clienteId;

	private Integer cantidadCuotas;

}
