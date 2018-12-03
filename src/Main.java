import Composition.IntervalExpander;
import Composition.NoteExtender;
import Composition.Rhythm;
import Composition.Staff;
import Objects.Note;
import Utils.Duration;
import Utils.Result;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static Composition.NoteExtenderKt.extendNotes;
import static Composition.Ostinato.*;
import static Utils.Conversor.*;
import static Utils.Rest.*;
import static Utils.SplitterKt.splitDuration;

public class Main {
    public static void main(String[] args) {
        int[] firstOperation = IntervalExpander.Expand(5, 4, 1, 30, 82);
        Note[] notes = NoteConversor(firstOperation);
        int[] rhythmFractals = Rhythm.RhythmicFractals(16, 8, 10);

        for (int i = 0 ; i < notes.length ; i++) {
            notes[i].setDuration(rhythmFractals[i]);
        }

        String[] finalConversion = LilypondConversor(notes);




        // TESTE EXPANSOR NOTAS
        List<String> listOfNotes = Arrays.asList("a","b", "c", "d");
        List<Staff> testeee = extendNotes(20, 2, listOfNotes);

        Result tatatata = splitDuration(testeee.get(0));

        GenerateHeader();

//        String[] notesSequence = new String[] {"d", "b", "a", "ais", "c"};
//        String[] noteExtenderSequence = NoteExtender.Extend(25, 0, 6, notesSequence, 4);

        System.out.println("voiceOne = { ");
        String printtedNote = "";
        for (int i = 0 ; i < tatatata.getStaff().getNote().size() ; i++) {
            printtedNote += tatatata.getStaff().getNote().get(i) + tatatata.getStaff().getDuration().get(i).getLilypondDuration() + " ";
        }
        System.out.println(printtedNote);
       // System.out.println(noteExtenderSequence[0]);
        System.out.println("}");

        System.out.println("voiceTwo = { ");
       // System.out.println(noteExtenderSequence[1]);
        System.out.println("}");

        System.out.println("voiceThree = { ");
     //   System.out.println(noteExtenderSequence[2]);
        System.out.println("}");

        System.out.println("voiceFour = { ");
      //  System.out.println(noteExtenderSequence[3]);
        System.out.println("}");

        System.out.println("partOne = { \\clef bass");

        for (String aFinalConversion : finalConversion) {
            System.out.print(" " + aFinalConversion + " ");
        }

        System.out.println("}");

        int len = finalConversion.length -1;

        String[] ostinatoOne = SimpleOstinato(32, "d", "f", finalConversion.length);

        System.out.println("ostinatoOne = {");

        for (String anOstinatoOne : ostinatoOne) {
            System.out.print(anOstinatoOne);
        }
        System.out.println("}");

        System.out.println("\n");

        String[] notesOstinato = new String[]{"d", "e", "f"};
        String[] grupeto = Grupeto(32, notesOstinato, 15);
        String[] notesOstinato2 = new String[]{"a","b","c","d","e","f","g"};
        String[] grupeto2 = Grupeto(16, notesOstinato2, 50);

        System.out.println("grupeto = { ");
        for (String grupetoNotes : grupeto) {
            System.out.print(grupetoNotes + " ");
        }
        System.out.println("}");

        System.out.println("\n");

        System.out.println("grupetoTwo = { ");
        for (String grupetoNotes : grupeto2) {
            System.out.print(grupetoNotes + " ");
        }
        System.out.println("}");

        System.out.println("\n");

        String[] ostinatoTwo = SimpleOstinato(32, "a", "g", finalConversion.length);
        System.out.println("ostinatoTwo = {");

        for (String anOstinatoTwo : ostinatoTwo) {
            System.out.print(anOstinatoTwo);
        }
        System.out.println("}");

        System.out.println("\n");

        System.out.println("partTwo = {");

        String[] firstRest = RestGenerator(rhythmFractals, 5, 5);

        for (String aFirstRest : firstRest) {
            System.out.print(aFirstRest);
        }

        for (int j = len ; j > 0 ; j--) {
            System.out.print( " " + finalConversion[j] + " ");
        }

        System.out.println("}");

        GenerateBody();
        GenerateFooter();
    }
    private static void GenerateHeader() {
        System.out.println("% LilyBin");
        System.out.println("\\paper {");
        System.out.println("\t#(set-paper-size \"a4\")");
        System.out.println("\t#(layout-set-staff-size 15)");
        System.out.println("}");
        System.out.println("\n");
        System.out.println("\\header {");
        System.out.println("\ttitle = \"Rascunhos p/ quarteto\"");
        System.out.println("\tcomposer = \"Tahbata Ali\"");
        System.out.println("}");
        System.out.println("\n");
    }

    private static void GenerateFooter() {
        System.out.println("\n");
        System.out.println("\t\\layout{");
        System.out.println("\t\tindent = 10\\mm");
        System.out.println("\t\tline-width = 180\\mm");
        System.out.println("\t\tragged-last = ##t");
        System.out.println("\t}");
        System.out.println("\t\\midi{}");
        System.out.println("}");
    }

    private static void GenerateBody() {
        System.out.println("\n");
        System.out.println("\\score{");
        System.out.println("\t{");
        System.out.println("\t<<");
        System.out.println("\t\t\\new StaffGroup");
        System.out.println("\t\t\t<<");
            // Cello 1
            System.out.println("\t\t\t\\new Staff = \"CelloOne\" {");
            System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 1\"");
            // INSERT NOTES
            System.out.println("\t\t\t\t\\partOne");
            System.out.println("\t\t\t}");
            // Cello 2
            System.out.println("\t\t\t\\new Staff = \"CelloTwo\" {");
            System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 2\"");
            // INSERT NOTES
            System.out.println("\t\t\t\t\\partTwo");
            System.out.println("\t\t\t}");
            //Cello 3
            System.out.println("\t\t\t\\new Staff = \"CelloThree\" {");
            System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 3\"");
            // INSERT NOTES
            System.out.println("\t\t\t\t\\clef bass \\grupeto  \\grupetoTwo");
            System.out.println("\t\t\t}");
            //Cello 4
            System.out.println("\t\t\t\\new Staff = \"CelloFour\" {");
            System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 4\"");
            // INSERT NOTES
            System.out.println("\t\t\t\t\\partFour");
            System.out.println("\t\t\t}");
        System.out.println("\t\t\t>>");
        System.out.println("\t\t>>");
        System.out.println("\t}");
    }
}
