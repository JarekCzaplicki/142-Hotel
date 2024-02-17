package com.hotel.service;

import com.hotel.entity.Room;
import com.hotel.model.RoomReservation;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        }
    }

    // pobierz gości hotelowych
    // dodawaj gości hotelowych
    // usuwanie gości hotelowych
}
