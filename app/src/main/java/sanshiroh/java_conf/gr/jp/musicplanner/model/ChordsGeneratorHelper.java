package sanshiroh.java_conf.gr.jp.musicplanner.model;


import java.util.ArrayList;
import java.util.List;

class ChordsGeneratorHelper {
    static List<Sequence> createSequences(MyRandom random, int numOfSequence) {
        List<Sequence> sequences = new ArrayList<>();

        if (numOfSequence == 1) {
            // [a]
            sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, 4));
        } else if (numOfSequence == 2) {
            // [aa'] or [ab] are possible.
            int barsA = random.nextInt(2) + 3;
            sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, barsA));
            sequences.add(random.nextBoolean() ?
                    new Sequence(Define.CHARACTER_OF_SEQUENCE_A_DASH, barsA) :
                    new Sequence(Define.CHARACTER_OF_SEQUENCE_B, random.nextInt(2) + 3));
        } else if (numOfSequence == 3) {
            // [aaa'], [aab] or [abc] are possible.
            int bars1 = random.nextInt(3) + 2;
            int bars2 = random.nextInt(3) + 2;
            sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, bars1));
            int value = random.nextInt(3);
            if (value == 0) {
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, bars1));
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A_DASH, bars1));
            } else if (value == 1) {
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, bars1));
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_B, bars2));
            } else if (value == 2) {
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_B, bars2));
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_C, random.nextInt(3) + 2));
            } else {
                throw new IllegalArgumentException();
            }
        } else if (numOfSequence == 4) {
            // [aaab], [aaba'] or [aabb] are possible.
            int bars1 = random.nextInt(2) + 2;
            int bars2 = random.nextInt(3) + 2;
            sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, bars1));
            sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, bars1));
            int value = random.nextInt(3);
            if (value == 0) {
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A, bars1));
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_B, bars2));
            } else if (value == 1) {
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_B, bars2));
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_A_DASH, bars1));
            } else if (value == 2) {
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_B, bars2));
                sequences.add(new Sequence(Define.CHARACTER_OF_SEQUENCE_B, bars2));
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }

        return sequences;
    }

    private static int getFunction(MyRandom random) {
        final int[] functions = new int[]{
                Define.FUNCTION_TONIC,
                Define.FUNCTION_SUB_DOMINANT,
                Define.FUNCTION_DOMINANT
        };

        return functions[random.nextInt(functions.length)];
    }

    private static int getFunction(MyRandom random, int avoidFunction) {
        return (avoidFunction + random.nextInt(2)) % 3;
    }

    static List<Chord> createRhythmPatternOfChords(int bars, MyRandom random) {
        if (bars == 1) {
            return createRhythmPatternOf1Bar(random, 1);
        } else if (bars == 2) {
            return createRhythmPatternOf2Bars(random);
        } else {
            return createRhythmPatternOfMoreThan3Bars(bars, random);
        }
    }

    private static List<Chord> createRhythmPatternOf1Bar(final MyRandom random, final int weight) {
        return new ArrayList<Chord>() {
            {
                if (random.nextBoolean(weight)) {
                    add(new Chord(Define.DURATION_HALF));
                    add(new Chord(Define.DURATION_HALF));
                } else {
                    add(new Chord(Define.DURATION_WHOLE));

                }
            }
        };
    }

    private static List<Chord> createRhythmPatternOf2Bars(final MyRandom random) {
        return new ArrayList<Chord>() {
            {
                if (random.nextInt(3) == 2) {
                    add(new Chord(Define.DURATION_DOUBLE));
                } else {
                    addAll(createRhythmPatternOf1Bar(random, 2));
                    addAll(createRhythmPatternOf1Bar(random, 2));
                }
            }
        };
    }

    private static List<Chord> createRhythmPatternOfMoreThan3Bars(final int bars,
                                                                  final MyRandom random) {
        return new ArrayList<Chord>() {
            {
                if (bars == 3) {
                    List<Chord> patternOf1Bar = createRhythmPatternOf1Bar(random, 2);
                    List<Chord> patternOf2Bars = createRhythmPatternOf2Bars(random);
                    if (random.nextBoolean()) {
                        addAll(patternOf1Bar);
                        addAll(patternOf2Bars);
                    } else {
                        addAll(patternOf2Bars);
                        addAll(patternOf1Bar);
                    }
                } else if (bars == 4) {
                    if (random.nextBoolean()) {
                        if (random.nextBoolean()) {
                            add(new Chord(Define.DURATION_QUADRUPLE));
                        } else {
                            add(new Chord(Define.DURATION_TRIPLE));
                            addAll(createRhythmPatternOf1Bar(random, 3));
                        }
                    } else {
                        addAll(createRhythmPatternOf2Bars(random));
                        addAll(createRhythmPatternOf2Bars(random));
                    }
                } else {
                    throw new RuntimeException("not supported: " + bars);
                }
            }
        };
    }

    static void assignFunction(MyRandom random, int shape, List<Chord> chords) {
        int firstFunction, lastFunction;
        switch (shape) {
            case Define.SHAPE_OF_SEQUENCES_TONIC_TO_TONIC:
                firstFunction = Define.FUNCTION_TONIC;
                lastFunction = Define.FUNCTION_TONIC;
                break;
            case Define.SHAPE_OF_SEQUENCES_TONIC_TO_SUB_DOMINANT:
                firstFunction = Define.FUNCTION_TONIC;
                lastFunction = Define.FUNCTION_SUB_DOMINANT;
                break;
            case Define.SHAPE_OF_SEQUENCES_TONIC_TO_DOMINANT:
                firstFunction = Define.FUNCTION_TONIC;
                lastFunction = Define.FUNCTION_DOMINANT;
                break;
            case Define.SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_TOINC:
                firstFunction = Define.FUNCTION_SUB_DOMINANT;
                lastFunction = Define.FUNCTION_TONIC;
                break;
            case Define.SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_SUB_DOMINANT:
                firstFunction = Define.FUNCTION_SUB_DOMINANT;
                lastFunction = Define.FUNCTION_SUB_DOMINANT;
                break;
            case Define.SHAPE_OF_SEQUENCES_SUB_DOMINANT_TO_DOMINANT:
                firstFunction = Define.FUNCTION_SUB_DOMINANT;
                lastFunction = Define.FUNCTION_DOMINANT;
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (chords.size() == 1) {
            chords.get(0).setFunction(lastFunction);
            return;
        }

        chords.get(0).setFunction(firstFunction);
        chords.get(chords.size() - 1).setFunction(lastFunction);

        if (chords.size() == 2) {
            return;
        }

        chords.get(1).setFunction(ChordsGeneratorHelper.getFunction(random));
        if (chords.size() == 3) {
            return;
        }

        for (int i = 2; i < chords.size(); i++) {
            int function;
            // 2-order Markov chain
            if (chords.get(i - 1).getFunction() == chords.get(i - 2).getFunction()) {
                function = ChordsGeneratorHelper.getFunction(random, chords.get(i - 1).getFunction());
            } else {
                function = ChordsGeneratorHelper.getFunction(random);
            }

            chords.get(i).setFunction(function);
        }
    }

    static void assignDegreeNames(MyRandom random, List<Chord> chords) {
        ChordsGeneratorHelper.assignDegreeName(random,
                chords.get(0), Define.UNDEFINED_VALUE);
        for (int i = 1; i < chords.size(); i++) {
            ChordsGeneratorHelper.assignDegreeName(random,
                    chords.get(i), chords.get(i - 1).getDegreeName());
        }
    }

    private static void assignDegreeName(MyRandom random, Chord chord,
                                         int beforeDegreeName) {
        if (chord.getFunction() == Define.FUNCTION_TONIC) {
            if (beforeDegreeName == Define.DEGREE_I) {
                chord.setDegreeName(random.nextInt(Define.DEGREE_III, Define.DEGREE_VI));
            } else if (beforeDegreeName == Define.DEGREE_III) {
                chord.setDegreeName(Define.DEGREE_VI);
            } else if (beforeDegreeName == Define.DEGREE_VI) {
                chord.setDegreeName(Define.DEGREE_I);
            } else {
                chord.setDegreeName(random.nextInt(Define.DEGREE_I, Define.DEGREE_III, Define.DEGREE_VI));
            }
        } else if (chord.getFunction() == Define.FUNCTION_SUB_DOMINANT) {
            if (beforeDegreeName == Define.DEGREE_II) {
                chord.setDegreeName(Define.DEGREE_VI);
            } else if (beforeDegreeName == Define.DEGREE_IV) {
                chord.setDegreeName(Define.DEGREE_II);
            } else if (beforeDegreeName == Define.DEGREE_VI) {
                chord.setDegreeName(random.nextInt(Define.DEGREE_II, Define.DEGREE_IV));
            } else {
                chord.setDegreeName(random.nextInt(Define.DEGREE_II, Define.DEGREE_IV, Define.DEGREE_VI));
            }
        } else if (chord.getFunction() == Define.FUNCTION_DOMINANT) {
            if (beforeDegreeName == Define.DEGREE_III) {
                chord.setDegreeName(Define.DEGREE_VII);
            } else if (beforeDegreeName == Define.DEGREE_V) {
                chord.setDegreeName(Define.DEGREE_III);
            } else if (beforeDegreeName == Define.DEGREE_VII) {
                chord.setDegreeName(Define.DEGREE_V);
            } else {
                chord.setDegreeName(random.nextInt(Define.DEGREE_III, Define.DEGREE_V, Define.DEGREE_VII));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    static void assignChords(MyRandom random, List<Chord> chords, int key) {
        for (Chord chord : chords) {
            chord.setPrefix(Define.CHORD_NAME_PREFIXES[(chord.getDegreeName() + key) % 12]);

            switch (chord.getDegreeName()) {
                case Define.DEGREE_I:
                    chord.setSuffix(
                            Define.SUFFIX_MAJOR_SEVENTH);
                    break;
                case Define.DEGREE_SHARP_1:
                    chord.setSuffix(random.nextString(
                            new int[] {1, 2},
                            Define.SUFFIX_SEVENTH, Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_II:
                    chord.setSuffix(random.nextString(
                            new int[] {2, 2, 5},
                            Define.SUFFIX_MAJOR_SEVENTH,
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_FLAT_III:
                    chord.setSuffix(random.nextString(
                            new int[] {1, 1, 1},
                            Define.SUFFIX_MAJOR_SEVENTH,
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_III:
                    chord.setSuffix(random.nextString(
                            new int[] {1, 4},
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_IV:
                    chord.setSuffix(random.nextString(
                            new int[] {4, 1, 2},
                            Define.SUFFIX_MAJOR_SEVENTH,
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_SHARP_IV:
                    chord.setSuffix(random.nextString(
                            new int[] {1, 3, 3},
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH_FLAT_FIVE));
                    break;
                case Define.DEGREE_V:
                    chord.setSuffix(random.nextString(
                            new int[] {3, 2},
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_FLAT_VI:
                    chord.setSuffix(random.nextString(
                            new int[] {5, 2, 3},
                            Define.SUFFIX_MAJOR_SEVENTH,
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_VI:
                    chord.setSuffix(random.nextString(
                            new int[] {2, 2, 5},
                            Define.SUFFIX_MAJOR_SEVENTH,
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_FLAT_VII:
                    chord.setSuffix(random.nextString(
                            new int[] {5, 3, 2},
                            Define.SUFFIX_MAJOR_SEVENTH,
                            Define.SUFFIX_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH));
                    break;
                case Define.DEGREE_VII:
                    chord.setSuffix(random.nextString(
                            new int[] {2, 1},
                            Define.SUFFIX_MINOR_SEVENTH,
                            Define.SUFFIX_MINOR_SEVENTH_FLAT_FIVE));
                    break;
            }
        }
    }

    static int createKey(MyRandom random) {
        return random.nextInt(12);
    }

    static void replaceDegreeNames(MyRandom random, List<Chord> chords, int times) {
        // TODO: implement this
    }

    private static String toDegreeName(int degreeName) {
        switch (degreeName) {
            case Define.DEGREE_I:
                return "I";
            case Define.DEGREE_SHARP_1:
                return "#I";
            case Define.DEGREE_II:
                return "II";
            case Define.DEGREE_FLAT_III:
                return "bIII";
            case Define.DEGREE_III:
                return "III";
            case Define.DEGREE_IV:
                return "IV";
            case Define.DEGREE_SHARP_IV:
                return "#IV";
            case Define.DEGREE_V:
                return "V";
            case Define.DEGREE_FLAT_VI:
                return "bVI";
            case Define.DEGREE_VI:
                return "VI";
            case Define.DEGREE_FLAT_VII:
                return "bVII";
            case Define.DEGREE_VII:
                return "VII";
            default:
                return "N.F.";
        }
    }
}
