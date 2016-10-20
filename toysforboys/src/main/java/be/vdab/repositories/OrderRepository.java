package be.vdab.repositories;

import java.util.List;



import be.vdab.entities.Order;


public class OrderRepository extends AbstractRepository {

	public List<Order> findAll(int vanafRij, int aantalRijen) {
		return getEntityManager().createNamedQuery("Order.findAll", Order.class).setFirstResult(vanafRij)
				.setMaxResults(aantalRijen).getResultList();
	}

	public Order read(long id) {
		return getEntityManager().find(Order.class, id);
	}

	
}

