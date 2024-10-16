package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GestionStationSkiApplicationTests {

    @Mock
    ISubscriptionRepository subRepository;

    @InjectMocks
    SubscriptionServicesImpl subServices;

    Subscription s = new Subscription(1L, LocalDate.now(), LocalDate.now().plusMonths(6), 150.0f,TypeSubscription.ANNUAL
    );


    List<Subscription> lc = new ArrayList<Subscription>()
    {{
        add(new Subscription(
                1L,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 31),
                250.0f,
                TypeSubscription.ANNUAL
        ));
        add(new Subscription(
                2L,
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 11, 30),
                120.0f,
                TypeSubscription.SEMESTRIEL
        ));
        add(new Subscription(
                3L,
                LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 10, 31),
                30.0f,
                TypeSubscription.MONTHLY
        ));
    }};


    @Test
    void testRetrieveSubscription() {
        when(subRepository.findById(1L)).thenReturn(Optional.of(s));
        Subscription retrievedCourse = subServices.retrieveSubscriptionById(1L);
        assertNotNull(retrievedCourse);
        assertEquals(s.getNumSub(), retrievedCourse.getNumSub());
        verify(subRepository).findById(1L);
    }

    @Test
    void testRetrieveNonExistentSubscription() {
        when(subRepository.findById(999L)).thenReturn(Optional.empty());
        Subscription retrievedSub = subServices.retrieveSubscriptionById(999L);
        assertNull(retrievedSub);  // Expecting a null result
        verify(subRepository).findById(999L);
    }

    // @Test
    // void testRetrieveAllSubscriptions() {
    //     when(subRepository.findAll()).thenReturn(lc);
    //     List<Subscription> subscriptions = subServices.retrieveAllSubscriptions();
    //     assertEquals(3, subscriptions.size());
    //     assertEquals(lc, subscriptions);
    //     verify(subRepository).findAll();
    // }





}