package be.vdab.services;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import be.vdab.entities.Order;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {
	
	private final OrderRepository orderRepository = new OrderRepository();

	public List<Order> findAll(int vanafRij, int aantalRijen) {
		return orderRepository.findAll(vanafRij, aantalRijen);
	}

	public  Order read(long id) {
		return orderRepository.read(id);
	}

	public boolean setAsShipped(long id) {
		beginTransaction();
		orderRepository.read(id).ship();
		try {
			
			commit();
			return true;
		} catch (RollbackException ex) {
			if(ex.getCause() instanceof OptimisticLockException)
				throw new RecordAangepastException();
			return false;
		}
	}
	

	

}
