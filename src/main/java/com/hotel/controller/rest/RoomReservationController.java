package com.hotel.controller.rest;

import com.hotel.entity.Guest;
import com.hotel.entity.Room;
import com.hotel.model.RoomReservation;
import com.hotel.service.ReservationService;
import com.hotel.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class RoomReservationController {
    private final ReservationService reservationService;
    private final DateUtil dateUtil;

    public RoomReservationController(ReservationService reservationService, DateUtil dateUtil) {
        this.reservationService = reservationService;
        this.dateUtil = dateUtil;
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest) {
        return reservationService.addGuest(guest);
    }

    @DeleteMapping("/guests/{guestId}")
    public boolean deleteGuest(@PathVariable long guestId) {
        return reservationService.deleteGuest(guestId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/rooms")
    public List<Room> getRooms() {
        return reservationService.getRooms();
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(required = false, value = "date") String stringDate) {
        Date date = dateUtil.createDateFromString(stringDate);
        System.out.println("_______________________________");
        System.out.println(stringDate);
        System.out.println(date);
        System.out.println("_______________________________");
        return reservationService.getRoomsReservationsForDate(date);
    }

}
