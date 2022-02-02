package it.cimillo.springboot.data;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
//	default Reservation getReservationInDate(String dateString) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate date = LocalDate.parse(dateString, formatter);
//		Reservation matchRes = StreamSupport.stream(this.findAll().spliterator(), false)
//				.filter(r -> r.getDate().equals(date)).findFirst().orElse(null);
//		return matchRes;
//	}
	
	Iterable<Reservation> findReservationByReservationDate(Date date);
}
