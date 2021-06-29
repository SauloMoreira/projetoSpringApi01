package br.com.cotiinformatica.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.ProdutoPostDTO;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.enums.Categoria;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.IFornecedorRepository;
import br.com.cotiinformatica.interfaces.IProdutoRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@Transactional //acessar repositorios do Hibernate
public class ProdutosController {

	// atributo para armazenar o endereço do serviço
	private static final String ENDPOINT = "/api/produtos";
	
	@Autowired //injeção de dependência
	private IFornecedorRepository fornecedorRepository;
	
	@Autowired //injeção de dependência
	private IProdutoRepository produtoRepository;	

	// método para cadastrar produtos
	@CrossOrigin
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post(@RequestBody ProdutoPostDTO dto) {

		try {						
			//resgatar os dados do DTO e transferi-los para a entidade
			Produto produto = new Produto();
			
			produto.setNome(dto.getNome());
			produto.setPreco(dto.getPreco());
			produto.setQuantidade(dto.getQuantidade());
			produto.setDataCompra(DateHelper.toDate(dto.getDataCompra()));
			produto.setCategoria(Categoria.valueOf(dto.getCategoria()));
			produto.setFornecedor(fornecedorRepository.findById(dto.getFornecedor()).get());
			
			//gravar no banco de dados
			produtoRepository.save(produto);
			
			return ResponseEntity
					.status(HttpStatus.OK) //HTTP 200
					.body("Produto " + produto.getNome() +", cadastrado com sucesso.");
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR) //HTTP 500
					.body("Erro: " + e.getMessage());
		}	
	}

	// método para atualizar produtos
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> put() {
		// TODO
		return ResponseEntity.status(HttpStatus.OK).body("Atualização de produto executado com sucesso!");
	}

	// método para excluir produtos
	@RequestMapping(value = ENDPOINT, method = RequestMethod.DELETE)
	public ResponseEntity<String> delete() {
		// TODO
		return ResponseEntity.status(HttpStatus.OK).body("Exclusão de produto executado com sucesso!");
	}

	// método para consultar produtos
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<String> get() {
		// TODO
		return ResponseEntity.status(HttpStatus.OK).body("Consulta de produto executado com sucesso!");
	}

}



