package sanshiroh.java_conf.gr.jp.musicplanner.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    /**
     * The character of this sequence.
     */
    public int character = Define.UNDEFINED_VALUE;

    /**
     * The num of bar of this sequence.
     */
    public int bars;

    /**
     * The list of chord
     */
    private List<Chord> mChords;

    /**
     * Creates a new instance.
     *
     * @param character the character
     * @param bars num of bars
     */
    public Sequence(int character, int bars) {
        this.character = character;
        this.bars = bars;
    }

    public List<Chord> getChords() {
        return mChords;
    }

    public void setChords(@NonNull List<Chord> chords) {
        mChords = chords;
    }

    /**
     * Sets the deep copy of the given chords.
     *
     * @param chords the list of chord
     */
    public void setCopiedChord(@NonNull List<Chord> chords) {
        mChords = new ArrayList<Chord>();
        for (Chord chord : chords) {
            mChords.add(chord.copy());
        }
    }
}
