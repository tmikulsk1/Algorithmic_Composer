package Composition;

import Finals.NoteDurations;
import java.util.Random;

public class NoteExtender {
    public static String[] Extend(int size, int minDuration, int maxDuration, String[] notes, int staffQuantity) {
        String[] duration = NoteDurations.durations();
        Random durationSelector = new Random();

        String[] staffs = new String[staffQuantity];
        Random staffSelector = new Random();

        Random slurs = new Random();
        int firstRest = staffQuantity - 1;

        for (int j = 0 ; j < staffs.length ; j ++) {
            staffs[j] = "";
        }

        int noteCounter = 0;

        for (int i = 0 ; i <= size ; i++) {
            String slurSymbol = "~";
            String actualNote = notes[noteCounter];
            int actualStaff = staffSelector.nextInt(staffQuantity);
            int quantitySlurs = slurs.nextInt(4)+1;

            if (firstRest != 0) {

                if (staffs[actualStaff] == "" || firstRest != staffQuantity -1) {
                    for (int slur = 0 ; slur <= quantitySlurs ; slur++) {
                        staffs[actualStaff] += "r";
                        staffs[actualStaff] += duration[durationSelector.nextInt(maxDuration)] + " ";
                    }

                    for (int slur = 0 ; slur <= quantitySlurs ; slur++) {
                        staffs[actualStaff] += actualNote;
                        if (slur == quantitySlurs) {
                            slurSymbol = "";
                        }
                        staffs[actualStaff] += duration[durationSelector.nextInt(maxDuration)] + slurSymbol + " ";
                    }
                }

                firstRest -= 1;

                if (noteCounter == notes.length-1) {
                    noteCounter = 0;
                } else {
                    noteCounter += 1;
                }

                continue;
            }

            for (int slur = 0 ; slur <= quantitySlurs ; slur++) {
                staffs[actualStaff] += actualNote;
                if (slur == quantitySlurs) {
                    slurSymbol = "";
                }
                staffs[actualStaff] += duration[durationSelector.nextInt(maxDuration)] + slurSymbol + " ";
            }

            if (noteCounter == notes.length-1) {
                noteCounter = 0;
            } else {
                noteCounter += 1;
            }

        }

        return staffs;
    }
}
