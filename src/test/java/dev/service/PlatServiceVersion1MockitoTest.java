package dev.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

public class PlatServiceVersion1MockitoTest {
	
	private PlatServiceVersion1 subject;
	private IPlatDao mockedPlatDao;
	
	@BeforeEach
	public void setUp() {
		mockedPlatDao = mock(IPlatDao.class);
		subject = new PlatServiceVersion1(mockedPlatDao);
	}
	
	@Test
	void ajouterPlatNomInvalide() throws PlatException {
		PlatException exception = assertThrows(PlatException.class, () -> subject.ajouterPlat("a", 500));
		assertEquals("un plat doit avoir un nom de plus de 3 caractères", exception.getMessage());
	}
	
	@Test
	void ajouterPlatPrixInvalide() throws PlatException {
		PlatException exception = assertThrows(PlatException.class, () -> subject.ajouterPlat("couscous", 5));
		assertEquals("le prix d'un plat doit être supérieur à 5 €", exception.getMessage());
	}
	
	@Test
	void methodeInvoquee() {
		subject.ajouterPlat("couscous", 600);
		verify(mockedPlatDao).ajouterPlat("couscous", 600);
	}
	
}
