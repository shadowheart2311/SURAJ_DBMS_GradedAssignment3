package service;

import java.util.List;

import com.springboot.tickettracker.model.Ticket;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);

    Ticket saveTicket(Ticket ticket);

    void deleteTicket(Long id);

    List<Ticket> searchTickets(String query);
}