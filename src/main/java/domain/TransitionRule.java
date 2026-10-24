package domain;

public class TransitionRule implements Rule {
    private final OrderPolicy policy = new OrderPolicy();

    @Override
    public void check(OrderStatus from, OrderStatus to) {
        policy.move(from, to);
    }
}