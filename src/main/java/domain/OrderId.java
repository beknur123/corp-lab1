package domain;
public record OrderId(String value) {
    public OrderId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Id cannot be null or blank");
        }
    }
}