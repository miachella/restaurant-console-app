package dev.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import dev.entite.Plat;
import dev.exception.PlatException;
import dev.service.PlatServiceVersion1;

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
		assertThrows(PlatException.class, () -> subject.ajouterPlat("a", 500));
	}
	
	@Test
	void ajouterPlatPrixInvalide() throws PlatException {
		assertThrows(PlatException.class, () -> subject.ajouterPlat("couscous", 5));
	}
	
	@Test
	void methodeInvoquee() {
		subject.ajouterPlat("couscous", 600);
		verify(mockedPlatDao, atLeastOnce()).ajouterPlat(anyString(), anyInt());
	}

}
