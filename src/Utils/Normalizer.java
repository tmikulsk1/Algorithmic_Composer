package Utils;

public class Normalizer {
    public static int OctaveNormalizer(int initialOctave) {
        int octaveAdd;

        switch (initialOctave) {
            case 0:
                octaveAdd = 0;
                break;
            case 1:
                octaveAdd = 12;
                break;
            case 2:
                octaveAdd = 24;
                break;
            case 3:
                octaveAdd = 36;
                break;
            case 4:
                octaveAdd = 48;
                break;
            case 5:
                octaveAdd = 60;
                break;
            case 6:
                octaveAdd = 72;
                break;
            case 7:
                octaveAdd = 84;
                break;
            default:
                octaveAdd = 48;
                break;
        }
        return octaveAdd;
    }
}
