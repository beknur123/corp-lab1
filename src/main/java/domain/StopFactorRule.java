package domain;

public class StopFactorRule implements Rule {
    @Override
    public void check(OrderStatus from, OrderStatus to) {
        if (from == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Нельзя изменять отмененный заказ.");
        }
    }
}