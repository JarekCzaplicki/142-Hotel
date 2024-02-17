package com.hotel.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    // id rezerwaci
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;
    @Column(name = "room_id")
    private long roomId;
    @Column(name = "guest_id")
    private long guestId;
    @Column(name = "reservation_date")
    private Date reservationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
