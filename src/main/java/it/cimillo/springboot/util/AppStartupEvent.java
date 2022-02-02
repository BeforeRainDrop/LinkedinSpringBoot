package it.cimillo.springboot.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import it.cimillo.springboot.data.Guest;
import it.cimillo.springboot.data.GuestRepository;
import it.cimillo.springboot.data.Reservation;
import it.cimillo.springboot.data.ReservationRepository;
import it.cimillo.springboot.data.Room;
import it.cimillo.springboot.data.RoomRepository;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;

	public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository,
			ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Iterable<Room> rooms = this.roomRepository.findAll();
		rooms.forEach(System.out::println);
		Iterable<Guest> guests = this.guestRepository.findAll();
		guests.forEach(System.out::println);
		Iterable<Reservation> reservations = this.reservationRepository.findAll();
		reservations.forEach(System.out::println);
		
		java.util.Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date sqlDate = new java.sql.Date(utilDate.getTime());
		reservations = this.reservationRepository.findReservationByReservationDate(sqlDate);
		System.out.println("*\nReservations in date 01/01/2022");
		reservations.forEach(System.out::println);
	}
}
