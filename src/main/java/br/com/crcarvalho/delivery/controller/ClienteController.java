package br.com.crcarvalho.delivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.crcarvalho.delivery.model.entity.Cliente;
import br.com.crcarvalho.delivery.model.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private final String CLIENTE_URI = "clientes/";
	
	@GetMapping({"/", ""})
	public ModelAndView list() {
		
		return new ModelAndView("clientes/list", "clientes", clienteRepository.findAll());
	}
	
	@GetMapping("{idCliente}")
	public ModelAndView view(@PathVariable("idCliente") Cliente cliente) {
		
		return new ModelAndView("clientes/view", "cliente", cliente);
	}
	
	@GetMapping("novo")
	public String form(@ModelAttribute Cliente cliente) {
		return "clientes/form";
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return new ModelAndView("clientes/form");
		}
		
		clienteRepository.save(cliente);
		
		attr.addFlashAttribute("globalMessage", "Cliente cadastrado com sucesso");
		
		return new ModelAndView("redirect:/" + CLIENTE_URI + cliente.getId());
	}
	
	@GetMapping("alterar/{idCliente}")
	public ModelAndView alterarForm(@PathVariable("idCliente") Cliente cliente) {
		
		return new ModelAndView("clientes/form", "cliente", cliente);
	}
	
	@GetMapping("remover/{idCliente}")
	public ModelAndView remover(@PathVariable("idCliente") Long idCliente, RedirectAttributes attr) {
		
		clienteRepository.delete(idCliente);
		attr.addFlashAttribute("globalMessage", "Cliente removido com sucesso");
		
		return new ModelAndView("redirect:/clientes/");
	}
	
}
