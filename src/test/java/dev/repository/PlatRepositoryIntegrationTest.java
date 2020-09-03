package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.JdbcTestConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = { JdbcTestConfig.class, JpaConfig.class })
@ActiveProfiles("jpa")
public class PlatRepositoryIntegrationTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatRepository subject;

	@Test
	void testFindAll() {
		List<Plat> p = subject.findAll();
		assertThat(p.size()).isEqualTo(6);
	}

	@Test
	void testFindAllSortAsc() {
		List<Plat> p = subject.findAll(Sort.by(Sort.Direction.ASC, "prixEnCentimesEuros"));
		assertThat(p.get(0).getNom()).isEqualTo("Côte de boeuf");
	}

	@Test
	void testFindAllSortDesc() {
		List<Plat> p = subject.findAll(Sort.by(Sort.Direction.DESC, "prixEnCentimesEuros"));
		assertThat(p.get(0).getNom()).isEqualTo("Gigot d'agneau");
	}

	@Test
	void testFindAllPageable() {
		Pageable p = PageRequest.of(0, 2, Sort.by("nom").ascending());
		Page<Plat> pagePlat = subject.findAll(p);
		assertThat(pagePlat.getContent().get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(pagePlat.getContent().get(1).getNom()).isEqualTo("Couscous");
	}

	@Test
	void testFindById() {
		Optional<Plat> p = subject.findById(1);
		assertThat(p.get().getNom()).isEqualTo("Magret de canard");
	}

	@Test
	@Transactional
	void testGetOne() {
		Plat p = subject.getOne(1);
		assertThat(p.getNom()).isEqualTo("Magret de canard");
	}

	@Test
	void testCount() {
		long c = subject.count();
		assertThat(c).isEqualTo(6);
	}

	@Test
	void testFindByPrix() {
		List<Plat> p = subject.findByPrixEnCentimesEuros(1300);
		assertThat(p.get(0).getNom()).isEqualTo("Magret de canard");
	}

	@Test
	void testAvgPrix() {
		int a = subject.avgPrix();
		List<Plat> p = subject.findAll();
		int sum = 0;
		long c = subject.count();
		for (Plat plat : p) {
			sum = sum + plat.getPrixEnCentimesEuros();
		}
		long moy = sum / c;
		assertThat(a).isEqualTo(moy);
	}

	@Test
	@Transactional
	void testFindByNomWithIngredients() {
		List<Plat> p = subject.findByNomWithIngredient("Moules-frites");
		assertThat(p.get(0).getIngredients().size()).isEqualTo(6);
	}

	@Test
	void testSave() {
		Plat p = new Plat();
		p.setId(7);
		p.setNom("Tielle à la Sétoise");
		p.setPrixEnCentimesEuros(1550);
		subject.save(p);
		List<Plat> liste = subject.findAll();
		assertThat(liste.size()).isEqualTo(7);
	}

	@Test
	@Transactional
	void testChangerNom() {
		subject.changerNom("Gigot d'agneau", "Méchoui");
		List<Plat> liste = subject.findAll();
		assertThat(liste).extracting(Plat::getNom).contains("Méchoui");
	}

}
