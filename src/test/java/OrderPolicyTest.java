import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.OrderId;
import domain.OrderPolicy;
import domain.OrderStatus;

public class OrderPolicyTest {

    private final OrderPolicy policy = new OrderPolicy();

    @ParameterizedTest
    @CsvSource({
        "DRAFT, PAID",
        "PAID, SHIPPED"
    })
    void testAllowedTransitions(OrderStatus from, OrderStatus to) {
        OrderStatus result = policy.move(from, to);
        Assertions.assertEquals(to, result);
    }

    @ParameterizedTest
    @CsvSource({
        "SHIPPED, DRAFT",
        "CANCELLED, PAID"
    })
    void testForbiddenTransitions(OrderStatus from, OrderStatus to) {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            policy.move(from, to);
        });
    }

    @Test
    void testNullOrBlankIdThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new OrderId(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new OrderId("   ");
        });
    }
}