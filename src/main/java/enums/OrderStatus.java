package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    ORDERED("", ""),
    IN_DELIVERY("", ""),
    DELIVERY("", "");

    private final String displayName;
    private final String serverName;
}
