package com.hotel.controller.rest;

import com.hotel.entity.Guest;
import com.hotel.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
