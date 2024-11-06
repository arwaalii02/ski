package tn.esprit.spring.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISkierServices;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SkierRestControllerTest {

    @Mock
    private ISkierServices skierServices;

    @InjectMocks
    private SkierRestController skierRestController;

    private Skier sampleSkier;

    @BeforeEach
    void setUp() {
        sampleSkier = new Skier();
        sampleSkier.setNumSkier(1L);
        sampleSkier.setFirstName("John");
        sampleSkier.setLastName("Doe");
    }

    @Test
    void testAddSkier() {
        // Given
        when(skierServices.addSkier(sampleSkier)).thenReturn(sampleSkier);

        // When
        Skier result = skierRestController.addSkier(sampleSkier);

        // Then
        assertEquals(sampleSkier, result);
        verify(skierServices).addSkier(sampleSkier);
    }

    @Test
    void testAddSkierAndAssignToCourse() {
        Long numCourse = 2L;
        when(skierServices.addSkierAndAssignToCourse(sampleSkier, numCourse)).thenReturn(sampleSkier);

        Skier result = skierRestController.addSkierAndAssignToCourse(sampleSkier, numCourse);

        assertEquals(sampleSkier, result);
        verify(skierServices).addSkierAndAssignToCourse(sampleSkier, numCourse);
    }

    @Test
    void testAssignToSubscription() {
        Long numSkier = 1L;
        Long numSub = 2L;
        when(skierServices.assignSkierToSubscription(numSkier, numSub)).thenReturn(sampleSkier);

        Skier result = skierRestController.assignToSubscription(numSkier, numSub);

        assertEquals(sampleSkier, result);
        verify(skierServices).assignSkierToSubscription(numSkier, numSub);
    }

    @Test
    void testAssignToPiste() {
        Long numSkier = 1L;
        Long numPiste = 3L;
        when(skierServices.assignSkierToPiste(numSkier, numPiste)).thenReturn(sampleSkier);

        Skier result = skierRestController.assignToPiste(numSkier, numPiste);

        assertEquals(sampleSkier, result);
        verify(skierServices).assignSkierToPiste(numSkier, numPiste);
    }

    @Test
    void testRetrieveSkiersBySubscriptionType() {
        TypeSubscription typeSubscription = TypeSubscription.ANNUAL;
        List<Skier> skiers = Arrays.asList(sampleSkier);
        when(skierServices.retrieveSkiersBySubscriptionType(typeSubscription)).thenReturn(skiers);

        List<Skier> result = skierRestController.retrieveSkiersBySubscriptionType(typeSubscription);

        assertEquals(skiers, result);
        verify(skierServices).retrieveSkiersBySubscriptionType(typeSubscription);
    }

    @Test
    void testGetById() {
        Long numSkier = 1L;
        when(skierServices.retrieveSkier(numSkier)).thenReturn(sampleSkier);

        Skier result = skierRestController.getById(numSkier);

        assertEquals(sampleSkier, result);
        verify(skierServices).retrieveSkier(numSkier);
    }

    @Test
    void testDeleteById() {
        Long numSkier = 1L;

        skierRestController.deleteById(numSkier);

        verify(skierServices).removeSkier(numSkier);
    }

    @Test
    void testGetAllSkiers() {
        List<Skier> skiers = Arrays.asList(sampleSkier);
        when(skierServices.retrieveAllSkiers()).thenReturn(skiers);

        List<Skier> result = skierRestController.getAllSkiers();

        assertEquals(skiers, result);
        verify(skierServices).retrieveAllSkiers();
    }
}
