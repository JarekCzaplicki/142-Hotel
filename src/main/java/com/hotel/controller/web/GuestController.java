package com.hotel.controller.web;

import com.hotel.entity.Guest;
import com.hotel.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/webguests")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getGuests(Model model) {
        model.addAttribute("guests", reservationService.getHotelGuests());
        return "hotel-guests";
    }

}
