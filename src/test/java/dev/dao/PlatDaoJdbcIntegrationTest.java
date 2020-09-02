package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;
import dev.service.PlatServiceVersion2;

@SpringJUnitConfig(classes = {JdbcTestConfig.class, PlatServiceVersion2.class, PlatDaoJdbc.class})
@ActiveProfiles("jdbc")
public class PlatDaoJdbcIntegrationTest {
	
	@Autowired
	private PlatDaoJdbc subject;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void ajouterPlatConforme() {
		//Given
		String nomPlat = "Blanquette";
		int prixPlat = 1600;
		//When
		//Then
		subject.ajouterPlat(nomPlat, prixPlat);
		Integer prix = jdbcTemplate.queryForObject("SELECT prixEnCentimesEuros FROM plat WHERE nom='Blanquette'", Integer.class);
		assertThat(prix).isEqualTo(1600);
	}
	
	@Test
	void listerPlatsNonVide() {
		List<Plat> p = subject.listerPlats();
		assertThat(p).isNotEmpty();
		
	}

}
