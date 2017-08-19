package sanshiroh.java_conf.gr.jp.musicplanner.model;


public class Chord {
    /**
     * The prefix of chord name
     */
    private String mPrefix;

    /**
     * The suffix of chord name
     */
    private String mSuffix;

    /**
     * The duration (MIDI tick)
     */
    private int mDuration;

    /**
     * The degree name
     */
    private int mDegreeName = Define.UNDEFINED_VALUE;

    /**
     * The function
     */
    private int mFunction = Define.UNDEFINED_VALUE;

    /**
     * Creates a new instance.
     *
     * @param duration the duration
     */
    public Chord(int duration) {
        mDuration = duration;
    }

    public int getDuration() {
        return mDuration;
    }

    public int getFunction() {
        return mFunction;
    }

    public void setFunction(int function) {
        mFunction = function;
    }

    public void setPrefix(String prefix) {
        mPrefix = prefix;
    }

    public void setSuffix(String suffix) {
        mSuffix = suffix;
    }

    public int getDegreeName() {
        return mDegreeName;
    }

    public void setDegreeName(int degreeName) {
        mDegreeName = degreeName;
    }

    @Override
    public String toString() {
        return mPrefix + mSuffix;
    }

    /**
     * Creates a deep copy of this instance.
     */
    public Chord copy() {
        Chord chord = new Chord(mDuration);
        chord.mPrefix = mPrefix;
        chord.mSuffix = mSuffix;
        chord.mDegreeName = mDegreeName;
        chord.mFunction = mFunction;
        return chord;
    }
}
