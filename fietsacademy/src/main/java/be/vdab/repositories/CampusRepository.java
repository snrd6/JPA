package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Campus;

public class CampusRepository extends AbstractRepository {

	public List<Campus> findByGemeente(String gemeente) {
return getEntityManager()
.createNamedQuery("Campus.findByGemeente", Campus.class)
.setParameter("gemeente", gemeente)
.getResultList();
}

	
	
	public List<Campus> findAll() { // voor later in de cursus
return getEntityManager().createNamedQuery("Campus.findAll", Campus.class)
.getResultList();
}

	
	
	
	public Campus read(long id) { // voor later in de cursus
return getEntityManager().find(Campus.class, id);

	}
}


