package fr.doranco.gestionContact.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.doranco.gestionContact.controller.api.payload.Payload;
import fr.doranco.gestionContact.model.entity.Perso;
import fr.doranco.gestionContact.repository.IPersoRepository;


@RestController
@RequestMapping("/api/perso")
public class PersoControllerAPI {
	
	private final IPersoRepository persoRepository;
	
	public PersoControllerAPI(IPersoRepository persoRepository) {
		this.persoRepository = persoRepository;
	}
	
	@GetMapping("/")
	public ResponseEntity<Payload> getAll() {
		List<Perso> persos = persoRepository.findAll();
		Payload payload = new Payload();
		if (persos.isEmpty()) {
			payload.setMessage("Liste des personnes est vide");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
		}
		payload.setMessage("Liste des personnes :");
		payload.setData(persos);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{emailPhone}")
	public ResponseEntity<Payload> getByEmailOrPhone(String emailPhone) {
		Perso perso = persoRepository.findByEmailOrPhone(emailPhone);
		Payload payload = new Payload();
		if (perso == null) {
			payload.setMessage("Personne introuvable");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
		}
		payload.setMessage("Personne trouvée :");
		payload.setData(perso);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	
	/*
	@GetMapping("/{nomOrPrenomStartWith}")
	public ResponseEntity<Payload> getByNomOrPrenomStartWith(String nomOrPrenomStartWith) {
		List<Perso> persos = persoRepository.findAllByNomOrPrenomStartWith(nomOrPrenomStartWith);
		Payload payload = new Payload();
		if (persos.isEmpty()) {
			payload.setMessage("Personne introuvable");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
		}
		payload.setMessage("Personne trouvée :");
		payload.setData(persos);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	} */
	
	@PostMapping("/create")
	public ResponseEntity<Payload> createContact(@RequestBody Perso perso) {
		Payload payload = new Payload();
		perso = persoRepository.save(perso);
		payload.setData(payload);
		payload.setMessage("Personne ajoutée avec succès");
				
		return new ResponseEntity<>(payload, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}/update")
	public ResponseEntity<Payload> updateContact(@RequestBody Perso perso) {
		Payload payload = new Payload();
		perso = persoRepository.save(perso);
		payload.setData(payload);
		payload.setMessage("Personne modifiée avec succès");

		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Payload> deleteContact(@RequestBody Perso perso) {
		Payload payload = new Payload();
		var persoToDelete = persoRepository.findById(perso.getId());
		
		if (persoToDelete.isEmpty()) {
			payload.setMessage("Personne introuvable");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
		}
		
		persoRepository.delete(perso);
		payload.setData(payload);
		payload.setMessage("Personne supprimée avec succès");

		return new ResponseEntity<>(payload, HttpStatus.OK);

	}
	
	

}
