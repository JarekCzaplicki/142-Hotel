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
    public List<RoomReservation> getRoomsReservationsForDate(Date date) {
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
    public List<Guest> getHotelGuests() {
        return guestRepository.findAll();
    }

    // pobieranie listy pokoi
    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        for (Room room : roomRepository.findAll()) {
            rooms.add(room);
        }
        return rooms;
    }

    /**
     * Method that is responsible for adding guests
     * Guest cannot be null and cannot add existing guest
     *
     * @param guest Guest to add
     * @return added guest from database
     */
    // dodawaj gości hotelowych
    public Guest addGuest(Guest guest) {
        if (guest == null)
            throw new RuntimeException("Guest cannot be null");
        Guest guest1 = guestRepository.findById(guest.getId()).orElse(null);

        if (guest1 != null)
            throw new RuntimeException("Guest already exist");

        return guestRepository.save(guest);
    }

    // usuwanie gości hotelowych
    public Boolean deleteGuest(long guestId) {
        guestRepository.findById(guestId).orElseThrow(() -> new RuntimeException("Guest by id not found"));
        guestRepository.deleteById(guestId);
        return guestRepository.findById(guestId).isEmpty();
    }
}
