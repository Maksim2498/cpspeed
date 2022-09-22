package space.moontalk.mc.cpspeed.message;

import org.jetbrains.annotations.NotNull;

public interface SecondsMessageProvider {
    default @NotNull String makeSecondsMessage() {
        return "seconds";
    }
}
