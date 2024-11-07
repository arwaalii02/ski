package tn.esprit.spring.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PisteTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;

    private Piste piste;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        piste = new Piste(1L, "Piste A", Color.BLUE, 200, 30, null); // Example piste
    }

    @Test
    void testRetrieveAllPistes() {
        // Arrange
        List<Piste> pistes = new ArrayList<>();
        pistes.add(piste);
        when(pisteRepository.findAll()).thenReturn(pistes);

        // Act
        List<Piste> result = pisteServices.retrieveAllPistes();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    void testAddPiste() {
        // Arrange
        when(pisteRepository.save(piste)).thenReturn(piste);

        // Act
        Piste result = pisteServices.addPiste(piste);

        // Assert
        assertNotNull(result);
        assertEquals(piste.getNumPiste(), result.getNumPiste());
        verify(pisteRepository, times(1)).save(piste);
    }

    @Test
    void testRemovePiste() {
        // Act
        pisteServices.removePiste(piste.getNumPiste());

        // Assert
        verify(pisteRepository, times(1)).deleteById(piste.getNumPiste());
    }

    @Test
    void testRetrievePiste() {
        // Arrange
        when(pisteRepository.findById(piste.getNumPiste())).thenReturn(Optional.of(piste));

        // Act
        Piste result = pisteServices.retrievePiste(piste.getNumPiste());

        // Assert
        assertNotNull(result);
        assertEquals(piste.getNumPiste(), result.getNumPiste());
        verify(pisteRepository, times(1)).findById(piste.getNumPiste());
    }
}