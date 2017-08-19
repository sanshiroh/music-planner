package sanshiroh.java_conf.gr.jp.musicplanner.model;


import java.util.List;

public class ChordsGenerator {

    /**
     * Generates chord progression automatically.
     *
     * @return the string of chord progression
     */
    public String generate() {
        StringBuilder builder = new StringBuilder();

        MyRandom random = new MyRandom();

        int key = ChordsGeneratorHelper.createKey(random);

        int numOfSequence = random.nextInt(4) + 1;

        Sequence cachedSequenceA = null;
        List<Sequence> sequences = ChordsGeneratorHelper.createSequences(random, numOfSequence);
        for (int j = 0; j < sequences.size(); j++) {
            Sequence sequence = sequences.get(j);
            if (sequence.character != Define.CHARACTER_OF_SEQUENCE_A_DASH) {
                // Creates rhythm patterns of chord progression
                sequence.setChords(ChordsGeneratorHelper.createRhythmPatternOfChords(sequence.bars, random));

                int shapeOfSequence = random.nextInt(
                        Define.SHAPE_OF_SEQUENCES_TONIC_TO_TONIC,
                        Define.SHAPE_OF_SEQUENCES_TONIC_TO_SUB_DOMINANT,
                        Define.SHAPE_OF_SEQUENCES_TONIC_TO_DOMINANT,
                        Define.SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_TOINC,
                        Define.SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_SUB_DOMINANT,
                        Define.SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_DOMINANT);

                // Assigns functions
                ChordsGeneratorHelper.assignFunction(random, shapeOfSequence, sequence.getChords());

                // Assigns degree names
                ChordsGeneratorHelper.assignDegreeNames(random, sequence.getChords());

                // Assigns chords
                ChordsGeneratorHelper.assignChords(random, sequence.getChords(), key);

                // Caches sequence A
                if (sequence.character == Define.CHARACTER_OF_SEQUENCE_A) {
                    cachedSequenceA = sequence;
                }
            } else if (cachedSequenceA != null) {
                // Creates sequence A' from the copy of sequence A
                sequence.setCopiedChord(cachedSequenceA.getChords());
                // TODO: modifies sequence
            } else {
                throw new IllegalStateException();
            }

            appendSequenceString(builder, sequence);
        }

        return builder.toString();
    }

    private void appendSequenceString(StringBuilder builder, Sequence sequence) {
        int duration = 0;
        builder.append("| ");
        for (Chord chord : sequence.getChords()) {
            builder.append(chord.toString());
            duration += chord.getDuration();
            if (duration % Define.DURATION_WHOLE == 0) {
                builder.append(" | ");
            } else {
                builder.append(" / ");
            }

            if (chord.getDuration() == Define.DURATION_DOUBLE) {
                builder.append("% | ");
            } else if (chord.getDuration() == Define.DURATION_TRIPLE) {
                builder.append("% | % | ");
            } else if (chord.getDuration() == Define.DURATION_QUADRUPLE) {
                builder.append("% | % | % | ");
            }
        }
        builder.append("\n");
    }
}
