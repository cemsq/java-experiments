package com.jtlog.model;

import com.google.common.collect.Sets;
import com.jtlog.serialization.Separator;
import com.jtlog.serialization.Writable;
import com.jtlog.serialization.Writer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 *
 */
@Data
@Accessors(chain = true)
public class Exercise implements Writable {
    private String id;
    private String note;
    private Set<String> labels = Sets.newHashSet();

    public Exercise addLabel(String label) {
        if (label != null && !label.isEmpty()) {
            labels.add(label);
        }
        return this;
    }

    @Override
    public void write(Writer w) {
        w.add(Separator.START_RECORD);
        w.addValue(id);
        w.add(Separator.VALUE);
        w.addValue(note);
        w.add(Separator.VALUE);

        boolean first = true;
        for (String label : labels) {
            if (first) {
                first = false;
            } else {
                w.add(Separator.SUB_VALUE);
            }

            w.addValue(label);
        }

        w.add(Separator.END_RECORD);
    }
}
