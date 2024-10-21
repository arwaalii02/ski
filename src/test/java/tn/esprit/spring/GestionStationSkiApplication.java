package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.spring.controllers.PisteRestController;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootApplication
@EnableScheduling
@ExtendWith(MockitoExtension.class)
public class GestionStationSkiApplication {
	@Mock
	IPisteServices pisteServices;

	@InjectMocks
	PisteRestController pisteRestController;

	Piste piste1 = new Piste(1L, "Piste A", Color.RED, 500, 20, null);
	Piste piste2 = new Piste(2L, "Piste B", Color.BLUE, 800, 25, null);

	@Test
	void testAddPiste() {
		when(pisteServices.addPiste(piste1)).thenReturn(piste1);

		Piste result = pisteRestController.addPiste(piste1);
		assertNotNull(result);
		assertEquals(piste1.getNumPiste(), result.getNumPiste());
		verify(pisteServices).addPiste(piste1);
	}

	@Test
	void testGetAllPistes() {
		List<Piste> pistes = Arrays.asList(piste1, piste2);
		when(pisteServices.retrieveAllPistes()).thenReturn(pistes);

		List<Piste> result = pisteRestController.getAllPistes();
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(pisteServices).retrieveAllPistes();
	}

	@Test
	void testGetPisteById() {
		when(pisteServices.retrievePiste(1L)).thenReturn(piste1);

		Piste result = pisteRestController.getById(1L);
		assertNotNull(result);
		assertEquals(piste1.getNumPiste(), result.getNumPiste());
		verify(pisteServices).retrievePiste(1L);
	}

	@Test
	void testDeletePisteById() {
		doNothing().when(pisteServices).removePiste(1L);

		pisteRestController.deleteById(1L);
		verify(pisteServices).removePiste(1L);
	}

	@Test
	void testGetPisteById_NotFound() {
		when(pisteServices.retrievePiste(999L)).thenReturn(null);

		Piste result = pisteRestController.getById(999L);
		assertNull(result);
		verify(pisteServices).retrievePiste(999L);
	}
}
