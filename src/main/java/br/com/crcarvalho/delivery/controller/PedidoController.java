package br.com.crcarvalho.delivery.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.crcarvalho.delivery.dto.RespostaDTO;
import br.com.crcarvalho.delivery.model.entity.Cliente;
import br.com.crcarvalho.delivery.model.entity.Item;
import br.com.crcarvalho.delivery.model.entity.Pedido;
import br.com.crcarvalho.delivery.model.repository.ClienteRepository;
import br.com.crcarvalho.delivery.model.repository.ItemRepository;
import br.com.crcarvalho.delivery.model.repository.PedidoRepository;

@RestController
public class PedidoController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId, @PathVariable("listaDeItens") String listaDeItens) {
		
		RespostaDTO dto = new RespostaDTO();
		
		try {
			Cliente cliente = clienteRepository.findOne(clienteId);
			
			// cria um array de id dos itens
			String[] listaDeItensID = listaDeItens.split(",");
			
			Pedido pedido = new Pedido();
			double valorTotal = 0;
			
			List<Item> itensPedidos = new ArrayList<>();
			
			// popula uma lista de itens a partir do array de ids
			for(String itemId : listaDeItensID) {
				Item item = itemRepository.findOne(Long.parseLong(itemId));
				itensPedidos.add(item);
				valorTotal += item.getPreco();
			}
			
			// popula o objeto pedido
			pedido.setItens(itensPedidos);
			pedido.setValorTotal(valorTotal);
			pedido.setData(new Date());
			pedido.setCliente(cliente);
			
			// adiciona o novo pedido ao array de pedidos do cliente
			cliente.getPedidos().add(pedido);
			
			// salva a nova lista de pedidos do cliente
			clienteRepository.saveAndFlush(cliente);
			
			// cria uma lista com os ids dos pedidos do cliente
			List<Long> pedidosId = new ArrayList<>();
			for(Pedido p : cliente.getPedidos()) {
				pedidosId.add(p.getId());
			}
			
			// pega o último id inserido
			Long ultimoPedido = Collections.max(pedidosId);
			
			// Cria o objeto com as informações do novo pedido criado
			dto = new RespostaDTO(ultimoPedido, valorTotal, "Pedido efetuado com sucesso");
			
		}catch (Exception ex) {
			dto.setMensagem("Erro: " + ex.getMessage());
		}
		
		return dto;
		
	}
	
}
