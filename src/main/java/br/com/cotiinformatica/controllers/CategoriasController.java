package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.enums.Categoria;

@Controller
public class CategoriasController {

	// atributo para armazenar o endereço do serviço
	private static final String ENDPOINT = "/api/categorias";

	// método para consultar categorias
	@CrossOrigin
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<String>> get() {

		List<String> result = Stream.of(Categoria.values())
								.map(Categoria::name)
								.collect(Collectors.toList());
		
		return ResponseEntity
				.status(HttpStatus.OK) //HTTP 200
				.body(result);		
	}
}



