package cem.etraining.model;

public enum Scale {
    C, D, E, F, G, A, B,
    C_sharp("C#"), D_sharp("D#"), F_sharp("F#"), G_sharp("G#"), A_sharp("A#"),
    D_flat("Db"), E_flat("Eb"), G_flat("Gb"), A_flat("Ab"), B_flat("Bb"),
    ;
    private final String note;

    Scale() {
        this.note = this.name();
    }

    Scale(String value) {
        this.note = value;
    }

    public String getNote() {
        return note;
    }
}
