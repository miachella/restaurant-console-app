package dev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.service.PlatServiceVersion2;

@SpringJUnitConfig(classes = {PlatServiceVersion2.class, PlatDaoFichier.class})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {
	
	@Autowired
	private PlatDaoFichier subject;
	
	@Test
	void ajouterPlatValide() {
		//Given
		String nomPlat = "couscous";
		int prixPlat = 1600;
		//When
		//Then
		subject.ajouterPlat(nomPlat, prixPlat);
		assertEquals(1, subject.listerPlats().size());
	}

}
