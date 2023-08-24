package com.algaworks.coelhofood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne//(fetch = FetchType.LAZY)//ele só faz o select no banco quando é necessário
	//manyToOne é EAGER-lerdo ManyTomany é LAZY-rápido
	//@JsonIgnoreProperties("hibernateLazyInitializer") neste caso aqui, daria erro sem o ignore e sem está
	//anotação, então nós colocamos a anotação q ignora o que está dentro de cozinha
	//@JsonIgnore
	//@JoinColumn(name = "nome_da_coluna", nullable = false) ele cria uma coluna no sql - BD - , nullable = false indica que este campo não pode ser null
	private Cozinha cozinha;

	@JsonIgnore
	@Embedded
	private Endereço endereço;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
				joinColumns = @JoinColumn (name = "restaurante_id"),
				inverseJoinColumns = @JoinColumn (name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
