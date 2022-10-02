package space.moontalk.mc.cpspeed.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.val;

import space.moontalk.mc.cpspeed.message.MessageProvider;
import space.moontalk.mc.cpspeed.message.MessageProviderHolder;

public class FilePersistenceManager implements PersistenceManager,
                                               MessageProviderHolder {
    private final @NotNull File       dbFolder;
    private final @NotNull Set<Entry> entries = new EntrySet();
    private                boolean    changed = false;

    @Getter
    private final @NotNull MessageProvider messageProvider;

    @Getter
    private final @NotNull File folder;

    public FilePersistenceManager(@NotNull MessageProvider messageProvider, @NotNull File folder) throws IOException {
        this.messageProvider = messageProvider;
        this.folder          = folder;
        dbFolder             = new File(folder, "db");
        dbFolder.mkdirs();
    }

    @Override
    public void load() throws IOException, ClassNotFoundException {
        entries.clear();

        loadGlobal();
        loadPlayers(); 

        changed = false;
    }

    private void loadGlobal() throws IOException, ClassNotFoundException {
        try {
            val stream = createGlobalInputStream();
            val count  = stream.readInt();

            for (int i = 0; i < count; ++i) {
                val entry = (Entry) stream.readObject();
                entries.add(entry);
            }
        } catch (EOFException exception) {}
    }

    private void loadPlayers() throws IOException, ClassNotFoundException {
        for (val uniqueId : collectActualPlayerUniqueIds())
            loadPlayer(uniqueId);
    }

    private @NotNull Set<UUID> collectActualPlayerUniqueIds() {
        val set   = new HashSet<UUID>();
        val names = dbFolder.list();

        for (val name : names) 
            try {
                val uuid = UUID.fromString(name);
                set.add(uuid);
            } catch (Exception exception) {}

        return set;
    }

    private void loadPlayer(@NotNull UUID playerUniqueId) throws IOException, ClassNotFoundException {
        try {
            val stream = createPlayerInputStream(playerUniqueId);
            val count  = stream.readInt();

            for (int i = 0; i < count; ++i) {
                val entry = (PlayerEntry) stream.readObject();
                entries.add(entry);
            }
        } catch (EOFException exception) {}
    }

    @Override
    public void save() throws IOException {
        if (!changed)
            return;

        saveGlobal();
        savePlayers();

        changed = false;
    }

    private void saveGlobal() throws IOException {
        val entries = getGlobalEntries();
        val stream  = createGlobalOutputStream();
        
        stream.writeInt(entries.size());

        for (val entry : entries) 
            stream.writeObject(entry); 
    }

    private void savePlayers() throws IOException {
        for (val uniqueId : collectPlayerUniqueIds())
            savePlayer(uniqueId);        
    }

    private void savePlayer(@NotNull UUID playerUniqueId) throws IOException {
        // Copying for better performance
        val entries = new HashSet<>(getPlayerEntries(playerUniqueId)); 
        val stream  = createPlayerOutputStream(playerUniqueId);

        stream.writeInt(entries.size());

        for (val entry : entries) 
            stream.writeObject(entry);
    }

    private @NotNull BukkitObjectInputStream createGlobalInputStream() throws IOException {
        val file   = getGlobalFile();
        val stream = new FileInputStream(file);
        return new BukkitObjectInputStream(stream);
    }

    private @NotNull BukkitObjectOutputStream createGlobalOutputStream() throws IOException {
        val file   = getGlobalFile();
        val stream = new FileOutputStream(file);
        return new BukkitObjectOutputStream(stream);
    }

    private @NotNull File getGlobalFile() throws IOException {
        val file = new File(dbFolder, "global");
        file.createNewFile();
        return file;
    }

    private @NotNull BukkitObjectInputStream createPlayerInputStream(@NotNull UUID playerUniqueId) throws IOException {
        val file   = getPlayerFile(playerUniqueId);
        val stream = new FileInputStream(file);
        return new BukkitObjectInputStream(stream);
    }

    private @NotNull BukkitObjectOutputStream createPlayerOutputStream(@NotNull UUID playerUniqueId) throws IOException {
        val file   = getPlayerFile(playerUniqueId);
        val stream = new FileOutputStream(file);
        return new BukkitObjectOutputStream(stream);
    }

    private @NotNull File getPlayerFile(@NotNull UUID playerUniqueId) throws IOException {
        val file = new File(dbFolder, playerUniqueId.toString());
        file.createNewFile();
        return file;
    }

    @Override
    public @NotNull Set<Entry> getEntries() {
        return entries;
    }

    private class EntrySet implements Set<Entry> {
        private final @NotNull Set<Entry> set = new HashSet<>();

        @Override
        public int size() {
            return set.size();
        }

        @Override
        public boolean isEmpty() {
            return set.isEmpty();
        }

        @Override
        public boolean contains(Object object) {
            return set.contains(object);
        }

        @Override
        public Iterator<Entry> iterator() {
            return set.iterator();
        }

        @Override
        public Object[] toArray() {
            return set.toArray();
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return set.toArray(array);
        }

        @Override
        public boolean add(Entry entry) {
            val added = set.add(entry);

            if (added)
                changed = true;

            return added;
        }

        @Override
        public boolean remove(Object object) {
            val removed = set.remove(object);

            if (removed)
                changed = true;

            return removed;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return set.containsAll(collection);
        }

        @Override
        public boolean addAll(Collection<? extends Entry> collection) {
            val added = set.addAll(collection);

            if (added)
                changed = true;

            return added;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            val removed = set.retainAll(collection);

            if (removed)
                changed = true;

            return removed;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            val removed = set.removeAll(collection);

            if (removed)
                changed = true;

            return changed;
        }

        @Override
        public void clear() {
            set.clear();
            changed = true; 
        }
    }
}
