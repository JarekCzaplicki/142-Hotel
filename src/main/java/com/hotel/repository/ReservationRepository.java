package com.hotel.repository;

import com.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Iterable<Reservation> findReservationByReservationDate(Date date);
    Iterable<Reservation> findReservationByRoomIdAndReservationDate(long id, Date date);

}
