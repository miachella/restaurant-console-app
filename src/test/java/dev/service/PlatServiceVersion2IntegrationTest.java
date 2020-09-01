package dev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;
import dev.exception.PlatException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PlatServiceVersion2.class, PlatDaoMemoire.class})
@ActiveProfiles("Mem2")
public class PlatServiceVersion2IntegrationTest {
	
	@Autowired
	private PlatServiceVersion2 subject;
	@Autowired
	private IPlatDao platDao;
	
	@Test
	void ajouterPlatValide() {
		//Given
		String nomPlat = "couscous";
		int prixPlat = 1600;
		//When
		//Then
		subject.ajouterPlat(nomPlat, prixPlat);
		assertEquals(1, platDao.listerPlats().size());
	}
	
	@Test
	void ajouterPlatPrixInvalide() throws PlatException {
		PlatException exception = assertThrows(PlatException.class, () -> subject.ajouterPlat("couscous", 5));
		assertEquals("le prix d'un plat doit être supérieur à 10 €", exception.getMessage());
	}

}
