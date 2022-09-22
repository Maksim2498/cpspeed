package space.moontalk.mc.cpspeed.message;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
@AllArgsConstructor
public class Time {
    private int totalSeconds;

    public void setMinutes(int minutes) {
        if (minutes < 0)
            throw new IllegalArgumentException("minutes has to be positive");

        totalSeconds = 60 * minutes + getSeconds();
    }

    public int getMinutes() {
        return totalSeconds / 60;
    }

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds >= 60)
            throw new IllegalArgumentException("seconds has to be in range [0, 60)");

        totalSeconds = 60 * getMinutes() + seconds;
    }

    public int getSeconds() {
        return totalSeconds % 60;
    }

    @Override
    public @NotNull String toString() {
        return toString(new SecondsMessageProvider() {});
    }

    public @NotNull String toString(@NotNull SecondsMessageProvider secondsProvider) {
        val minutes = getMinutes();
        val seconds = getSeconds();

        if (minutes == 0) {
            val secondsString = secondsProvider.makeSecondsMessage();
            return String.format("%d %s", seconds, secondsString);
        }

        return String.format("%d:%02d", minutes, seconds);
    }
}
