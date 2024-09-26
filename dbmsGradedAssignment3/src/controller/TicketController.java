package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.tickettracker.model.Ticket;
import com.springboot.tickettracker.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String home(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "home";
    }

    @GetMapping("/create")
    public String createTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "create";
    }

    @PostMapping("/create")
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editTicket(@PathVariable Long id, @ModelAttribute Ticket ticket) {
        ticket.setId(id);
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("/search")
    public String searchTickets(@RequestParam String query, Model model) {
        List<Ticket> searchResults = ticketService.searchTickets(query);
        model.addAttribute("tickets", searchResults);
        return "home";
    }

    @GetMapping("/tickets/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "tickets/view";
    }
}