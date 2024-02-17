package com.hotel.repository;


import com.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository - redundantna adnotacja
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
