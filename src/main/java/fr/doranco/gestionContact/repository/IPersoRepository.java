package fr.doranco.gestionContact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.doranco.gestionContact.model.entity.Perso;

@Repository
public interface IPersoRepository extends JpaRepository<Perso, Integer> {
	
	@Query("SELECT p FROM Perso p WHERE p.firstName LIKE %:emailOrPhone% OR p.lastName LIKE %:emailOrPhone%")
	public Perso findByEmailOrPhone(String emailOrPhone);
	
	

//	public List<Perso> findAllByNomOrPrenomStartWith(String nomOrPrenomStartWith);

}
