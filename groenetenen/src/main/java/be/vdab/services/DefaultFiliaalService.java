package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.data.domain.Sort;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.valueobjects.PostcodeReeks;
import repositories.FiliaalRepository;

@ReadOnlyTransactionalService
class DefaultFiliaalService implements FiliaalService{
private final FiliaalRepository filiaalRepository;

DefaultFiliaalService(FiliaalRepository filiaalRepository) { 
this.filiaalRepository = filiaalRepository;
}

@Override
@ModifyingTransactionalServiceMethod
public void create(Filiaal filiaal) {
filiaalRepository.save(filiaal);
}


@Override
public Optional<Filiaal> read(long id) {
return Optional.ofNullable(filiaalRepository.findOne(id));
}

@Override
@ModifyingTransactionalServiceMethod
public void update(Filiaal filiaal) {
filiaalRepository.save(filiaal);
}


@Override
@ModifyingTransactionalServiceMethod
public void delete(long id) {
	Optional<Filiaal> optionalFiliaal = Optional.ofNullable(filiaalRepository.findOne(id));
	if (optionalFiliaal.isPresent()) {
	if ( ! optionalFiliaal.get().getWerknemers().isEmpty()) {
	throw new FiliaalHeeftNogWerknemersException();
	}
	Optional.ofNullable(filiaalRepository.findOne(id));
	}
}


@Override
public List<Filiaal> findAll() {
return filiaalRepository.findAll(new Sort("naam"));
}


@Override
public long findAantalFilialen() {
return filiaalRepository.count();
}

@Override 
public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks){
	return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(reeks.getVanpostcode(),reeks.getTotpostcode());
}

//Methode nodig voor afschrijven --> waarde op nul zetten

@Override 
public List<Filiaal> findNietAfgeschreven(){
	return filiaalRepository.findByWaardeGebouwNot(BigDecimal.ZERO);
}

@Override
@ModifyingTransactionalServiceMethod
public void afschrijven(Filiaal filiaal) {
filiaal.afschrijven(); // je wijzigt een entity binnen een transactie.
// JPA wijzigt dan automatisch het bijbehorende record bij de commit
}
}
