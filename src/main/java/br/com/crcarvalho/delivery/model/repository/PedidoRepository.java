package br.com.crcarvalho.delivery.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.crcarvalho.delivery.model.entity.Pedido;

@RepositoryRestResource(collectionResourceRel="pedidos", path="pedidos")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
