package space.moontalk.mc.cpspeed.persistence;

import org.jetbrains.annotations.NotNull;

public interface PersistenceManagerHolder {
    @NotNull PersistenceManager getPersistenceManager();
}
