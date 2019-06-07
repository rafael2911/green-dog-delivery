package br.com.crcarvalho.delivery.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crcarvalho.delivery.model.entity.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTeste {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	public void buscaClientesCadastrados() {
		
		Page<Cliente> clientes = this.clienteRepository.findAll(new PageRequest(0, 10));
		
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);
		
	}
	
	@Test
	public void buscaClienteFernando() {
		
		Cliente clienteNaoEncontrado = this.clienteRepository.findByNome("fernando");
		assertThat(clienteNaoEncontrado).isNull();
		
		Cliente cliente = this.clienteRepository.findByNome("Fernando");
		assertThat(cliente).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Fernando");
		assertThat(cliente.getEndereco()).isEqualTo("Sampa");
		
	}
	
}
