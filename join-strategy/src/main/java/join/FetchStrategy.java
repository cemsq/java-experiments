package join;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FetchStrategy<F extends FetchStrategy<?>> {

    private final List<FetchEntry> entries = Lists.newArrayList();

    public void forEach(Consumer<FetchEntry> consumer) {
        entries.forEach(consumer);
    }

    public void applyFetch(PartialQuery query) {

    }

    public List<FetchEntry> getEntries() {
        return entries;
    }

    public F join(CharSequence joinName, FetchType fetchType) {
        Preconditions.checkNotNull(joinName, "null joinName");
        return join(joinName.toString(), fetchType);
    }

    public F join(String joinName, FetchType fetchType) {
        Preconditions.checkNotNull(fetchType, "fetchType");
        Preconditions.checkNotNull(joinName, "null joinName");
        Preconditions.checkArgument(!joinName.isEmpty(), "empty joinName");

        FetchEntry entry = findByName(joinName);
        if (entry == null) {
            addNewEntry(joinName, fetchType);
        } else {
            entry.setFetchType(fetchType);
        }

        return castThis();
    }

    private void addNewEntry(String joinName, FetchType fetchType) {
        if (findByPrefix(joinName) != null) {
            return;
        }

        entries.removeIf(e -> joinName.startsWith(e.getJoinName()));
        entries.add(new FetchEntry(joinName, fetchType));
    }

    private FetchEntry findByName(String joinName) {
        return find(e -> e.getJoinName().equals(joinName));
    }

    private FetchEntry findByPrefix(String prefix) {
        return find(e -> e.getJoinName().startsWith(prefix));
    }

    private FetchEntry find(Predicate<FetchEntry> predicate) {
        for (FetchEntry entry : entries) {
            if (predicate.test(entry)) {
                return entry;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    protected F castThis() {
        return (F) this;
    }
}
