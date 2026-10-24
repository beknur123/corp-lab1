package domain;

public class DuplicateTicket extends RuntimeException {
    public DuplicateTicket(String businessKey) {
        super("duplicate ticket: " + businessKey);
    }
}