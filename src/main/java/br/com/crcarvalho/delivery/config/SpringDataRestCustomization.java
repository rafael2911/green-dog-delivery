package br.com.crcarvalho.delivery.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import br.com.crcarvalho.delivery.model.entity.Cliente;
import br.com.crcarvalho.delivery.model.entity.Item;
import br.com.crcarvalho.delivery.model.entity.Pedido;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Item.class, Pedido.class, Cliente.class);
	}
	
}
