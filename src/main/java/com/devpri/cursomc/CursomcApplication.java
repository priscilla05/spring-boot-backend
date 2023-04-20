package com.devpri.cursomc;

import com.devpri.cursomc.domain.*;
import com.devpri.cursomc.domain.enums.EstadoPagamento;
import com.devpri.cursomc.domain.enums.TipoCliente;
import com.devpri.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null,"Informatica");
		Categoria categoria2 = new Categoria(null,"Escritorio");

		Produto produto1 = new Produto(null, "Computador",2000.00);
		Produto produto2 = new Produto(null, "Impressora",800.00);
		Produto produto3 = new Produto(null, "Mouse",80.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));

		Estado estado1 = new Estado(null,"Minas Gerais");
		Estado estado2 = new Estado(null,"Paraiba");

		Cidade cidade1 = new Cidade(null,"Uberlandia", estado1);
		Cidade cidade2 = new Cidade(null,"Joao Pessoa", estado2);
		Cidade cidade3 = new Cidade(null,"Campina Grande", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));


		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2)); //categorias sendo salvas
		produtoRepository.saveAll(Arrays.asList(produto1,produto2, produto3)); // produtos sendo salvos
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2,cidade3));

		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "0115458547", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("89987521447","8932256998"));

		Endereco endereco1 = new Endereco(null, "Rua Flores","86", "Apto 304","Jardim","58012452", cliente1,cidade1);
		Endereco endereco2 = new Endereco(null, "Av Matos","99", "Apto 801","Rurais","58877452",cliente1, cidade2);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));

		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido pedido1 = new Pedido(null,simpleDateFormat.parse("12/07/2022 09:50"),cliente1, endereco1);
		Pedido pedido2 = new Pedido(null,simpleDateFormat.parse("21/10/2022 09:50"),cliente1, endereco2);

		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,pedido1,6);
		pedido1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, pedido2,simpleDateFormat.parse("12/07/2022 00:00"),null);
		pedido2.setPagamento(pagto2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));





	}
}
