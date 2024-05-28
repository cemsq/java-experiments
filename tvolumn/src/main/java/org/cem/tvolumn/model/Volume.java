package org.cem.tvolumn.model;

import com.google.common.collect.Maps;
import lombok.ToString;

import java.util.Map;

/**
 *
 */
@ToString
public class Volume {

    private final Map<Muscle, Double> vol = Maps.newEnumMap(Muscle.class);

    public void put(Muscle key, Double value) {
        vol.merge(key, value, Double::sum);
    }
}
