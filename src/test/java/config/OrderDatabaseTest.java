package config;

import domain.DuplicateTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import persistence.TicketJdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OrderDatabaseTest {

    @Autowired
    private OrderService service;

    @Autowired
    private TicketJdbc tickets;

    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    void resetDatabase() throws IOException {
        jdbc.execute("DROP TABLE IF EXISTS ticket");
        jdbc.execute(Files.readString(Path.of("src/main/resources/db/schema.sql")));
    }

    @Test
    void secondStatementRollsBack() {
        assertThrows(DuplicateTicket.class,
                () -> service.insertTwice("HD-1042"));
        assertEquals(0, tickets.count("HD-1042"));
    }

    @Test
    void secondRequestKeepsTheFirst() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        service.register(id1, "HD-1042", "Printer");
        assertThrows(DuplicateTicket.class,
                () -> service.register(id2, "HD-1042", "Again"));
        assertEquals(1, tickets.count("HD-1042"));
    }
}