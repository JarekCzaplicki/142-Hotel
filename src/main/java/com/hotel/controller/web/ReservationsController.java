package com.hotel.controller.web;

import com.hotel.service.ReservationService;
import com.hotel.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/webreservations")
public class ReservationsController {
    private final DateUtil dateUtil;
    private final ReservationService reservationService;

    public ReservationsController(DateUtil dateUtil, ReservationService reservationService) {
        this.dateUtil = dateUtil;
        this.reservationService = reservationService;
    }

    @GetMapping("/v1")
    public String getReservationsV1(@RequestParam(required = false, value = "date") String stringDate, Model model) {
        Date date = dateUtil.createDateFromString(stringDate);
        model.addAttribute("roomReservations", reservationService.getRoomsReservationsForDate(date));
        return "room-res";
    }

    @GetMapping("v2")
    public String getReservationsV2(@RequestParam(required = false, value = "date") String stringDate, Model model) {
        Date date = dateUtil.createDateFromString(stringDate);
        model.addAttribute("roomReservations", reservationService.getReservationByReservationDate(date));
        return "room-res-date";
    }
}