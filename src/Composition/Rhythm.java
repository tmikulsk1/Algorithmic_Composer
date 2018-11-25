package Composition;

public class Rhythm {
    public static int[] RhythmicFractals(int first, int second, int repetitions){
        String[] fractals = new String[repetitions];
        String wholeFractals;

        fractals[0] = first + "-";
        wholeFractals = fractals[0];
        fractals[1] = first + "-" + second + "-";
        wholeFractals += fractals[1];
        fractals[2] = first + "-" + second + "-" + first + "-";
        wholeFractals += fractals[2];

        for (int i = 3 ; i < repetitions ; i++) {
            fractals[i] = fractals[i-1] + fractals[i-2];
            wholeFractals += fractals[i];
        }
        return ConvertFractals(wholeFractals);
    }

    //ritmo vertical

    private static int[] ConvertFractals(String fractals){
        String[] rhythmFractal = fractals.split("-");
        int[] integerFractals = new int[rhythmFractal.length];
        for (int i = 0 ; i < rhythmFractal.length ; i++) {
            integerFractals[i] = Integer.parseInt(rhythmFractal[i]);
        }
        return integerFractals;
    }
}
