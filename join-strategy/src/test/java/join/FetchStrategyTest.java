package join;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.StringJoiner;
import java.util.function.Supplier;

public class FetchStrategyTest {

    @Test
    public void shouldAddDifferent() {
        Supplier<StringJoiner> joiner = () -> new StringJoiner("\n");

        StringJoiner values = joiner.get();
        new FetchStrategy<>()
                .join("a.b.c", FetchType.A)
                .join("a", FetchType.A)
                .join("a.b", FetchType.A)
                .join("a.b.c.d", FetchType.A)
                .join("a.x.c.d", FetchType.A)
                .forEach(e -> values.add(e.getJoinName()));

        String expected = joiner.get()
                .add("a.b.c.d")
                .add("a.x.c.d")
                .toString();

        Assert.assertEquals(values.toString(), expected);
    }

    @Test
    public void should() {
        Supplier<StringJoiner> joiner = () -> new StringJoiner("\n");

        StringJoiner values = joiner.get();
        new FetchStrategy<>()
                .join("subsets.subsetItems.itemUnit.unit", FetchType.A)
                .join("subsets.subsetItems.item", FetchType.A)
                .forEach(e -> values.add(e.getJoinName()));

        String expected = joiner.get()
                .add("subsets.subsetItems.itemUnit.unit")
                .add("subsets.subsetItems.item")
                .toString();

        Assert.assertEquals(values.toString(), expected);
    }

    @Test
    public void should2() {
        Supplier<StringJoiner> joiner = () -> new StringJoiner("\n");

        StringJoiner values = joiner.get();
        new FetchStrategy<>()
                .join("subsets.subsetItems.item", FetchType.A)
                .join("subsets.subsetItems.itemUnit.unit", FetchType.A)
                .forEach(e -> values.add(e.getJoinName()));

        String expected = joiner.get()
                .add("subsets.subsetItems.item")
                .add("subsets.subsetItems.itemUnit.unit")
                .toString();

        Assert.assertEquals(values.toString(), expected);
    }
}
