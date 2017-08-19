package sanshiroh.java_conf.gr.jp.musicplanner.model;

import java.util.List;
import java.util.Random;

public class MyRandom extends Random {
    public boolean nextBoolean(int weight) {
        return nextInt(weight + 1) == weight;
    }

    public int nextInt(int... candidates) {
        return candidates[nextInt(candidates.length)];
    }

    public String nextString(int[] weights, String... candidates) {
        if (weights.length != candidates.length) {
            throw new IllegalArgumentException();
        }

        int total = 0;
        for (int weight : weights) {
            total += weight;
        }

        int offset = 0;
        int value = nextInt(total + 1);
        for (int i = 0; i < weights.length; i++) {
            if (offset + weights[i] <= value) {
                return candidates[i];
            } else {
                offset += weights[i];
            }
        }

        return candidates[weights.length - 1];
    }
}
