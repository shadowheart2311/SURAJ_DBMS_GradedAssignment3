package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tickettracker.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTitleContainingOrShortDescriptionContaining(String title, String shortDescription);
}