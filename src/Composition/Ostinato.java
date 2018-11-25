package Composition;

public class Ostinato {
    public static String[] SimpleOstinato(int duration, String firstNote, String secondNote, int size) {
        String[] ostinato = new String[size];

        ostinato[0] = firstNote + String.valueOf(duration) + " " + secondNote + " ";

        for (int i = 1 ; i < size ; i++) {
            ostinato[i] = firstNote + " " + secondNote + " ";
        }
        return ostinato;
    }

    public static String[] Grupeto(int duration, String[] notes, int size) {
        String[] ostinato = new String[size];
        int counter = 1;
        boolean isAscending = true;

        ostinato[0] = notes[0] + String.valueOf(duration);

        for (int i = 1 ; i < size ; i++ ) {
            if (isAscending) {
                if (counter < notes.length) {
                    ostinato[i] = notes[counter];
                    counter++;
                } else {
                    counter -= 2;
                    isAscending = false;

                    if (counter != -1) {
                        ostinato[i] = notes[counter];
                        counter--;
                    }
                }
            } else {
                if (counter != -1) {
                    ostinato[i] = notes[counter];
                    counter--;
                } else {
                    counter = 1;
                    isAscending = true;

                    if (counter < notes.length) {
                        ostinato[i] = notes[counter];
                        counter++;
                    }
                }
            }
        }
        return ostinato;
    }
}
