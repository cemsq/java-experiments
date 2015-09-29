package com.cgm.storm.utils.common;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class StreamGobbler extends Thread {
    InputStream is;
    StringBuilder sb;
    boolean suppressOutput;

    public StreamGobbler() {

    }

    public boolean isSuppressOutput() {
        return suppressOutput;
    }

    public void setSuppressOutput(boolean suppressOutput) {
        this.suppressOutput = suppressOutput;
    }

    public void setInputStream(InputStream is) {
        Preconditions.checkNotNull(is, "InputStream must be not null");
        this.is = is;
    }

    public void run() {
        Preconditions.checkNotNull(is, "StreamGobbler without InputStream. Please call the method setInputStream");

        try {
            sb = new StringBuilder();

            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            System.out.print("\n[");
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());

                if (!suppressOutput) {
                    System.out.println(line);
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("]\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getText() {
        return sb != null ? sb.toString() : "";
    }
}
