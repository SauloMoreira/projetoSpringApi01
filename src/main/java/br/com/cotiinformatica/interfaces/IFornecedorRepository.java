package br.com.cotiinformatica.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Fornecedor;

public interface IFornecedorRepository extends CrudRepository<Fornecedor, Integer> {

	@Query("from Fornecedor f where f.cnpj = :param") //JPQL
	Fornecedor findByCnpj(@Param("param") String cnpj);

}
