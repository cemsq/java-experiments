package ds;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GraphImplTest {

    @Test
    public void shouldLink() {
        GraphImpl<String, City> graph = new GraphImpl<>(City::getName);
        City caracas = new City("Caracas");
        City home = new City("San Cristobal");

        graph.linkNodes(home, caracas);
        boolean connected = graph.isConnected("San Cristobal", "Caracas");
        Assert.assertEquals(connected, true);
    }

    private class City {
        private String name;

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
