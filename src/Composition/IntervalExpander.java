package Composition;

import static Utils.Normalizer.OctaveNormalizer;

public class IntervalExpander {
    public static int[] Expand(int pitch, int inicialOctave, int interval, int repetitions, int limitNote) {
        int[] generated_list = new int[repetitions];

        pitch = pitch+(OctaveNormalizer(inicialOctave));

        generated_list[0] = pitch;
        generated_list[1] = pitch + interval;
        generated_list[2] = pitch - interval;

        for (int i = 3 ; i < repetitions ; i++) {
            if (generated_list[i] >= 0) {
                if (i % 2 == 1) {
                    generated_list[i] = generated_list[i - 2] + interval;
                } else {
                    generated_list[i] = generated_list[i - 2] - interval;
                }

                if (generated_list[i] > limitNote) {
                    int hitter = generated_list[i] - limitNote;
                    generated_list[i] -= hitter;
                }
            }
        }
        return generated_list;
    }
}