package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("", ""),
    MODERATOR("", ""),
    NONAME("", "");

    private final String displayName;
    private final String serverName;
}
