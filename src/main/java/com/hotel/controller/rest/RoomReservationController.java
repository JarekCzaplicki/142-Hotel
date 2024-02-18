package com.hotel.controller.rest;

import com.hotel.entity.Guest;
import com.hotel.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class RoomReservationController {
    private final ReservationService reservationService;

    public RoomReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return reservationService.getHotelGuests();
    }
    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest){
        return reservationService.addGuest(guest);
    }
    @DeleteMapping("/guests/{guestId}")
    public boolean deleteGuest(long guestId){
        return reservationService.deleteGuest(guestId);
    }

}
