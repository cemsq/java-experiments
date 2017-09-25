import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GuitarSpec {
    private Builder builder;
    private Type type;
    private Wood bodyWood;
    private Wood neckWood;

    public GuitarSpec(Builder builder, Type type, Wood bodyWood, Wood neckWood) {
        this.builder = builder;
        this.type = type;
        this.bodyWood = bodyWood;
        this.neckWood = neckWood;
    }

    public Builder getBuilder() {
        return builder;
    }

    public Type getType() {
        return type;
    }

    public Wood getBodyWood() {
        return bodyWood;
    }

    public Wood getNeckWood() {
        return neckWood;
    }

    public boolean match(GuitarSpec spec) {
        Preconditions.checkNotNull(spec, "GuitarSpec must be not null");

        if (this.builder != spec.builder) {
            return false;
        }
        if (this.type != spec.type) {
            return false;
        }
        if (this.bodyWood != spec.bodyWood) {
            return false;
        }
        if (this.neckWood != spec.neckWood) {
            return false;
        }

        return true;
    }

    public List<Guitar> search(List<Guitar> guitars) {
        List<Guitar> list = new ArrayList<>();

        for (Guitar guitar : guitars) {
            if (this.match(guitar.getSpec())) {
                list.add(guitar);
            }
        }

        return list;
    }
}
