package com.bookingmx.reservations.service;

import com.bookingmx.reservations.dto.ReservationRequest;
import com.bookingmx.reservations.exception.BadRequestException;
import com.bookingmx.reservations.exception.NotFoundException;
import com.bookingmx.reservations.model.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private final String name = "Juan";
    private final String hotel = "Playa";
    private final LocalDate check_in = LocalDate.now();
    private final LocalDate check_out = LocalDate.now().plusDays(2);

    private Reservation get_sample_reservation(){
        Reservation reservation = new Reservation();

        reservation.setId(1L);
        reservation.setGuestName(name);
        reservation.setHotelName(hotel);
        reservation.setCheckIn(check_in);
        reservation.setCheckOut(check_out);

        return reservation;
    }

    private ReservationRequest get_sample_reservation_request(){
        ReservationRequest res_req = new ReservationRequest();

        res_req.setGuestName(name);
        res_req.setHotelName(hotel);
        res_req.setCheckIn(check_in);
        res_req.setCheckOut(check_out);

        return res_req;
    }

    @Test
    void list() {
        Reservation reservation = get_sample_reservation();
        ReservationRequest res_req = get_sample_reservation_request();

        ReservationService res_srv = new ReservationService();
        List<Reservation> reservationList = new ArrayList<>();
        assertEquals(reservationList, res_srv.list());

        reservationList.add(reservation);
        res_srv.create(res_req);
        assertEquals(reservationList, res_srv.list());
    }

    @Test
    void create() {
        ReservationRequest res_req = get_sample_reservation_request();

        ReservationService res_srv = new ReservationService();
        Reservation reservation = new Reservation(1L, name, hotel, check_in, check_out);
        assertEquals(reservation, res_srv.create(res_req));
    }

    @Test
    void update() {
        ReservationRequest res_req = get_sample_reservation_request();

        ReservationService res_srv = new ReservationService();
        res_srv.create(res_req);

        assertThrows(NotFoundException.class, () -> res_srv.update(100L, res_req));

        res_srv.cancel(1L);

        assertThrows(BadRequestException.class, () -> res_srv.update(1L, res_req));

        res_req.setGuestName("Pedro");
        res_req.setHotelName("Riu");
        res_req.setCheckIn(check_in);
        res_req.setCheckOut(check_out);
        res_srv.create(res_req);

        Reservation reservation = new Reservation(2L, "Pedro", "Ramada", check_in, check_out);
        res_req.setHotelName("Ramada");
        assertEquals(reservation, res_srv.update(2L, res_req));
    }

    @Test
    void cancel() {
        Reservation reservation = get_sample_reservation();

        ReservationRequest res_req = get_sample_reservation_request();

        ReservationService res_srv = new ReservationService();
        res_srv.create(res_req);

        assertThrows(NotFoundException.class, () -> res_srv.cancel(100L));
        assertEquals(reservation, res_srv.cancel(1L));
    }
}