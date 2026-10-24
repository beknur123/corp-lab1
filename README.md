# Corporate Software Development — Lab 2

## Package Structure
- **`domain`** — pure domain model (`OrderId`, `OrderStatus`, `OrderPolicy`, `Rule` interface, and implementations `TransitionRule`, `StopFactorRule`). Completely isolated from Spring.
- **`config`** — application configuration, `Application` class with the `main` method, and `OrderService`.
- **`dto`** — data transfer objects.
- **`client`** — external service integrations.
- **`handler`** — incoming request handlers.