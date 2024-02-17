package com.hotel.service;

import com.hotel.entity.Guest;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.model.RoomReservation;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(GuestRepository guestRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    // pobierz rezerwacje dla wskazanej daty
    List<RoomReservation> getRoomsReservationsForDate(Date date) {
        List<RoomReservation> roomReservations = new ArrayList<>();

        Iterable<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomType(room.getRoomType());
            roomReservation.setRoomNumber(room.getRoomNumber());
            Iterable<Reservation> reservations = reservationRepository.findReservationByRoomIdAndReservationDate(
                    room.getId(), new java.sql.Date(date.getTime()));
            for (Reservation reservation : reservations) {
                roomReservation.setReservationDate(reservation.getReservationDate());
                Optional<Guest> optionalGuest = guestRepository.findById(reservation.getGuestId());
                if (optionalGuest.isPresent()) {
                    Guest guest = optionalGuest.get();
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setId(guest.getId());
                }
                roomReservations.add(roomReservation);
                /**
                 Inna wersja powyzszego wyciągania danych
                 Guest guest = guestRepository.findById(reservation.getGuestId()).orElse(null);
                 if (guest != null) {
                 roomReservation.setFirstName(guest.getFirstName());
                 roomReservation.setLastName(guest.getLastName());
                 roomReservation.setId(guest.getId());
                 }
                 */

            }
            roomReservations.sort(Comparator.comparing(RoomReservation::getRoomType)
                    .thenComparing(RoomReservation::getRoomNumber));
        }
        return roomReservations;
    }

    // pobierz gości hotelowych
    // dodawaj gości hotelowych
    // usuwanie gości hotelowych
}
