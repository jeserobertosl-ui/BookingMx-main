package com.bookingmx.reservations.repo;

import com.bookingmx.reservations.model.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    @Test
    void findAll(){
        Reservation reservation = new Reservation(
            1L,
                "Juan",
                "Playa",
                LocalDate.now(),
                LocalDate.of(2025, 11, 25)
        );
        ReservationRepository res_rep = new ReservationRepository();
        res_rep.save(reservation);
        List<Reservation> res_list = new ArrayList<>();
        res_list.add(reservation);

        assertEquals(res_list, res_rep.findAll());
    }

    @Test
    void findById() {
        Reservation reservation = new Reservation(
                1L,
                "Juan",
                "Playa",
                LocalDate.now(),
                LocalDate.of(2025, 11, 25)
        );
        ReservationRepository res_rep = new ReservationRepository();
        res_rep.save(reservation);
        Optional<Reservation> opt_res = res_rep.findById(reservation.getId());

        assertEquals(opt_res, res_rep.findById(reservation.getId()));
        assertEquals(Optional.empty(), res_rep.findById(10L));
    }

    @Test
    void save() {
        Reservation reservation = new Reservation(
                1L,
                "Juan",
                "Playa",
                LocalDate.now(),
                LocalDate.of(2025, 11, 25)
        );

        Reservation reservation2 = new Reservation();

        reservation2.setGuestName("Paco");
        reservation2.setHotelName("Riu");
        reservation2.setCheckIn(LocalDate.now());
        reservation2.setCheckOut(LocalDate.of(2025, 11, 25));

        ReservationRepository res_rep = new ReservationRepository();

        assertEquals(reservation, res_rep.save(reservation));
        reservation2.setId(2L);
        assertEquals(reservation2, res_rep.save(reservation2));
    }

    @Test
    void delete() {
        Reservation reservation = new Reservation(
                1L,
                "Juan",
                "Playa",
                LocalDate.now(),
                LocalDate.of(2025, 11, 25)
        );

        Reservation reservation2 = new Reservation(
                2L,
                "Paco",
                "Riu",
                LocalDate.now(),
                LocalDate.of(2025, 11, 25)
        );

        List<Reservation> res_list = new ArrayList<>();
        res_list.add(reservation);
        res_list.add(reservation2);
        res_list.remove(1);

        ReservationRepository res_rep = new ReservationRepository();
        res_rep.save(reservation);
        res_rep.save(reservation2);
        res_rep.delete(2L);

        assertEquals(res_list, res_rep.findAll());
    }
}