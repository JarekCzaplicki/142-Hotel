package com.hotel.repository;

import com.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Iterable<Reservation> findReservationByReservationDate(Date date);
    Iterable<Reservation> findReservationByRoomIdAndReservationDate(long id, Date date);

}
