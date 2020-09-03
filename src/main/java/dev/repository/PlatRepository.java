package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(int prixEnCentimesEuros);

	@Modifying
	@Query("update Plat p set p.nom = :newNom where p.nom = :oldNom")
	void changerNom(@Param("oldNom") String oldNom, @Param("newNom") String newNom);

	@Query("SELECT AVG(p.prixEnCentimesEuros) FROM Plat p")
	int avgPrix();

	List<Plat> findByNom(String nom);

	@Query("SELECT p FROM Plat p WHERE p.nom= ?1")
	List<Plat> findByNomWithIngredient(String nom);

}
