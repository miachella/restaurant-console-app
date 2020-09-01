package dev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

public class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;
	
	@BeforeEach
	void setUp() {
	this.platDaoMemoire = new PlatDaoMemoire();
	}
	
	@Test
	void listerPlatsVideALInitialisation() {
		List<Plat> l = new ArrayList<Plat>();
		assertEquals(l, platDaoMemoire.listerPlats());
	}
	
	@Test
	void ajouterPlatCasPassants() {
		platDaoMemoire.ajouterPlat("Curry", 1000);
		List<Plat> l = new ArrayList<Plat>();
		Plat p = new Plat("Curry", 1000);
		l.add(p);
		assertEquals(l, platDaoMemoire.listerPlats());
		}
}
