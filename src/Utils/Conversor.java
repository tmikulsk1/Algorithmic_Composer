package Utils;

import Composition.Staff;
import Finals.NoteDuration;
import Objects.Note;

public class Conversor {
    public static Note[] NoteConversor(int[] pitches) {
        Note[] notes = new Note[pitches.length];

        for (int i = 0 ; i < pitches.length ; i++) {
            notes[i] = new Note();
            notes[i].setPitchClass(pitches[i] % 12);
            notes[i].setOctave(pitches[i] / 12);
        }
        return notes;
    }

    public static String[] LilypondConversor(Note[] notes) {
        String[] output = new String[notes.length];

        for (int i = 0 ; i < notes.length ; i++) {
            int pitch = notes[i].getPitchClass();
            int octave = notes[i].getOctave();
            int duration = notes[i].getDuration();

            output[i] = convertToPitch(pitch);
            output[i] += convertToDuration(octave, duration);
        }
        return output;
    }

    public static Staff StaffConversor(Note[] notes) {
        Staff output = new Staff();

        for (int i = 0 ; i < notes.length ; i++) {
            String note = convertToPitch(notes[i].getPitchClass()) + convertToLilypondOctave( notes[i].getOctave());
            output.component1().add(i, note);
            NoteDuration duration = new NoteDuration(notes[i].getDuration(), String.valueOf(notes[i].getDuration()));
            output.component2().add(i,duration);
            output.component3().add(i, "");
        }

        return output;
    }

    public static String[] LilypondConversorWithRandomRest(Note[] notes, int randomLevel) {
        String[] output = new String[notes.length];

        for (int i = 0 ; i < notes.length ; i++) {
            int pitch = notes[i].getPitchClass();
            int octave = notes[i].getOctave();
            int duration = notes[i].getDuration();

            if (Randomic.RandomGenerator(randomLevel)) {
                output[i] = "r";
            } else output[i] = convertToPitch(pitch);

            output[i] += convertToDuration(octave, duration);
        }
        return output;
    }

    public static String[] LilypondConversorWithRandomDuration(Note[] notes, int randomLevel, int randomDuration) {
        String[] output = new String[notes.length];

        for (int i = 0 ; i < notes.length ; i++) {
            int pitch = notes[i].getPitchClass();
            int octave = notes[i].getOctave();
            int duration = notes[i].getDuration();

            output[i] = convertToPitch(pitch);

            if (Randomic.RandomGenerator(randomLevel)) {
                output[i] += String.valueOf(randomDuration);
            } else output[i] += convertToDuration(octave, duration);
        }
        return output;
    }

    private static String convertToPitch(int pitch) {
        String output = "";

        switch (pitch) {
            case 0:
                output = "c";
                break;
            case 1:
                output = "cis";
                break;
            case 2:
                output = "d";
                break;
            case 3:
                output = "dis";
                break;
            case 4:
                output = "e";
                break;
            case 5:
                output = "f";
                break;
            case 6:
                output = "fis";
                break;
            case 7:
                output = "g";
                break;
            case 8:
                output = "gis";
                break;
            case 9:
                output = "a";
                break;
            case 10:
                output = "ais";
                break;
            case 11:
                output = "b";
                break;
        }
        return output;
    }

    private static String convertToDuration(int octave, int duration) {
        String output;

        if (duration == 0) {
            duration = 4;
        }

        switch (octave) {
            case 0:
                output = ",,," + duration;
                break;
            case 1:
                output = ",," + duration;
                break;
            case 2:
                output = "," + duration;
                break;
            case 3:
                output = "" + duration;
                break;
            case 4:
                output = "'" + duration;
                break;
            case 5:
                output = "''" + duration;
                break;
            case 6:
                output = "'''" + duration;
                break;
            default:
                output = "" + duration;
                break;
        }
        return output;
    }

    private static String convertToLilypondOctave(int octave) {
        String output;
        switch (octave) {
            case 0:
                output = ",,,";
                break;
            case 1:
                output = ",,";
                break;
            case 2:
                output = ",";
                break;
            case 3:
                output = "";
                break;
            case 4:
                output = "'";
                break;
            case 5:
                output = "''";
                break;
            case 6:
                output = "'''";
                break;
            default:
                output = "";
                break;
        }
        return output;
    }
}
