package sanshiroh.java_conf.gr.jp.musicplanner.model;

public class Define {
    public static final int UNDEFINED_VALUE = -1;

    // Characters of sequence
    public static final int CHARACTER_OF_SEQUENCE_A = 0;
    public static final int CHARACTER_OF_SEQUENCE_A_DASH = 1;
    public static final int CHARACTER_OF_SEQUENCE_B = 2;
    public static final int CHARACTER_OF_SEQUENCE_C = 3;

    // Functions of chord
    public static final int FUNCTION_TONIC = 0;
    public static final int FUNCTION_SUB_DOMINANT = 1;
    public static final int FUNCTION_DOMINANT = 2;

    // The functional shapes of sequence
//    public static final int SHAPE_OF_SEQUENCES_MODAL = 0;
    public static final int SHAPE_OF_SEQUENCES_TONIC_TO_TONIC = 1;
    public static final int SHAPE_OF_SEQUENCES_TONIC_TO_SUB_DOMINANT = 2;
    public static final int SHAPE_OF_SEQUENCES_TONIC_TO_DOMINANT = 3;
    public static final int SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_TOINC = 4;
    public static final int SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_SUB_DOMINANT = 5;
    public static final int SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_DOMINANT = 6;

    public static final int EXTENSION_INSERT = 0;
    public static final int EXTENSION_DELETE = 1;
    public static final int EXTENSION_REPLACE = 2;

    // Durations (MIDI tick)
    public static final int DURATION_WHOLE = 1920;
    public static final int DURATION_DOUBLE = DURATION_WHOLE * 2;
    public static final int DURATION_TRIPLE = DURATION_WHOLE * 3;
    public static final int DURATION_QUADRUPLE = DURATION_WHOLE * 4;
    public static final int DURATION_HALF = DURATION_WHOLE / 2;
//    public static final int DURATION_QUOTE = DURATION_WHOLE / 4;

    // Degree names
    public static final int DEGREE_I = 0;
    public static final int DEGREE_SHARP_1 = 1;
    public static final int DEGREE_II = 2;
    public static final int DEGREE_FLAT_III = 3;
    public static final int DEGREE_III = 4;
    public static final int DEGREE_IV = 5;
    public static final int DEGREE_SHARP_IV = 6;
    public static final int DEGREE_V = 7;
    public static final int DEGREE_FLAT_VI = 8;
    public static final int DEGREE_VI = 9;
    public static final int DEGREE_FLAT_VII = 10;
    public static final int DEGREE_VII = 11;

    // Notes
    public static final int C = 0;
    public static final int Cis = 1;
    public static final int D = 2;
    public static final int Es = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int Fis = 6;
    public static final int G = 7;
    public static final int As = 8;
    public static final int A = 9;
    public static final int B = 10;
    public static final int H = 11;

    // Prefixes of chord name
    public static final String[] CHORD_NAME_PREFIXES = {
            "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B"
    };

    // Suffixes of chord name
    public static final String SUFFIX_MAJOR = "";
    public static final String SUFFIX_MAJOR_SEVENTH = "M7";
    public static final String SUFFIX_SEVENTH = "7";
    public static final String SUFFIX_MINOR = "m";
    public static final String SUFFIX_MINOR_SEVENTH = "m7";
    public static final String SUFFIX_MINOR_MAJOR_SEVENTH = "mM7";
    public static final String SUFFIX_DIMINISH = "(-5)";
    public static final String SUFFIX_MINOR_SEVENTH_FLAT_FIVE = "7(-5)";
    public static final String SUFFIX_ARGUMENT = "(+5)";
    public static final String SUFFIX_MAJOR_SEVENTH_SHARP_FIVE = "M7(+5)";
}
