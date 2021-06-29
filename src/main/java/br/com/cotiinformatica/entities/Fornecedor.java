
package br.com.cotiinformatica.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	@Column(name = "idfornecedor")
	private Integer idFornecedor;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "cnpj", length = 20, nullable = false, unique = true)
	private String cnpj;

	//One (Fornecedor) To Many (Produtos) -> 1 Fornecedor para MUITOS Produtos
	@OneToMany(mappedBy = "fornecedor") //nome do atributo na classe Produto onde foi mapeado a foreign key
	private List<Produto> produtos;

	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	public Fornecedor(Integer idFornecedor, String nome, String cnpj, List<Produto> produtos) {
		super();
		this.idFornecedor = idFornecedor;
		this.nome = nome;
		this.cnpj = cnpj;
		this.produtos = produtos;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nome=" + nome + ", cnpj=" + cnpj + "]";
	}

}
