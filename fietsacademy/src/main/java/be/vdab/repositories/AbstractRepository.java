package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;
//import be.vdab.services.AbstractService;

public abstract class AbstractRepository {
	protected EntityManager getEntityManager() { 
		return JPAFilter.getEntityManager(); 
		}
}
