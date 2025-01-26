package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("Админ", "Admin"),
    MODERATOR("Дискорд Модер", "Moderator"),
    NONAME("НН", "No name");

    private final String displayName;
    private final String serverName;
}
