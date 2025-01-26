package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    ORDERED("Заказан", "Ordered"),
    IN_DELIVERY("Доставляется", "In delivery"),
    DELIVERED("Доставлен", "Delivered");

    private final String displayName;
    private final String serverName;
}
