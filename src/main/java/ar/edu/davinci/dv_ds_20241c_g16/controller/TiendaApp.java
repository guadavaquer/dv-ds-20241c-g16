package ar.edu.davinci.dv_ds_20241c_g16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(path = "/tienda")
public abstract class TiendaApp {
	// Esto es equivalente a hacer
	// http://localhost:8090/tienda
	// El puerto 8090 está configurado en la propiedad server.port
}

