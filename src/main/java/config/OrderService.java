package config;

import domain.DuplicateTicket;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persistence.TicketJdbc;

import java.util.UUID;

@Service
public class OrderService {
    private final TicketJdbc tickets;

    public OrderService(TicketJdbc tickets) {
        this.tickets = tickets;
    }

    @Transactional
    public void insertTwice(String key) {
        tickets.insert(UUID.randomUUID(), key, "OPEN", "Initial title");
        try {
            tickets.insert(UUID.randomUUID(), key, "OPEN", "Duplicate title");
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateTicket(key);
        }
    }

    @Transactional
    public void register(UUID id, String key, String title) {
        try {
            tickets.insert(id, key, "OPEN", title);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateTicket(key);
        }
    }
}