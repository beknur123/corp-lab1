public class OrderPolicy {
    public OrderStatus move(OrderStatus current, OrderStatus target) {
        if (current == OrderStatus.DRAFT && target == OrderStatus.PAID) {
            return OrderStatus.PAID;
        }
        if (current == OrderStatus.PAID && target == OrderStatus.SHIPPED) {
            return OrderStatus.SHIPPED;
        }

        throw new IllegalStateException("Transition from " + current + " to " + target + " is forbidden.");
    }
}