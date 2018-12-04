import Composition.IntervalExpander;
import Composition.NoteExtender;
import Composition.Rhythm;
import Composition.Staff;
import Objects.Note;
import Utils.Result;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static Composition.CrossoverKt.SimpleCrossover;
import static Composition.NoteExtenderKt.extendNotes;
import static Composition.Ostinato.*;
import static Utils.Conversor.*;
import static Utils.Rest.*;

import static Utils.SplitterKt.splitDuration;
import static Utils.SymbolKt.insertSlur;



public class Main {
    public static void main(String[] args) {
        /**
         *
         * GENERATE .LY
         */

        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream("output.ly"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);

        /**
         * START WRITE
         */
        GenerateHeader();

        GenerateExtendedNotes1();
        GenerateGrupetos();

        /**
         * INTERVAL EXPANDER
         */
        int[] firstOperation = IntervalExpander.Expand(5, 4, 1, 30, 82);
        Note[] notes = NoteConversor(firstOperation);
        int[] rhythmFractals = Rhythm.RhythmicFractals(16, 8, 10);

        for (int i = 0 ; i < notes.length ; i++) {
            notes[i].setDuration(rhythmFractals[i]);
        }

        String[] finalConversion = LilypondConversor(notes);

        System.out.println("partONE = { ");

        for (String aFinalConversion : finalConversion) {
            System.out.print(" " + aFinalConversion + " ");
        }

        System.out.println("}");

        /**
         * SIMPLE OSTINATO
         * ONE
         */

        String[] ostinatoOne = SimpleOstinato(32, "d", "f", 20);

        System.out.println("ostinatoOne = {");

        for (String anOstinatoOne : ostinatoOne) {
            System.out.print(anOstinatoOne);
        }
        System.out.println("}");

        System.out.println("\n");

        /**
         * SIMPLE OSTINATO
         * TWO
         */

        String[] ostinatoTwo = SimpleOstinato(32, "a", "g", finalConversion.length);
        System.out.println("ostinatoTwo = {");

        for (String anOstinatoTwo : ostinatoTwo) {
            System.out.print(anOstinatoTwo);
        }
        System.out.println("}");

        System.out.println("\n");

        /**
         * REST GENERATOR
         */

        System.out.println("partTWO = {");

        String[] firstRest = RestGenerator(rhythmFractals, 5, 5);

        for (String aFirstRest : firstRest) {
            System.out.print(aFirstRest);
        }

        for (int j = 20 ; j > 0 ; j--) {
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
        System.out.println("\t#(layout-set-staff-size 20)");
        System.out.println("}");
        System.out.println("\n");
        System.out.println("\\header {");
        System.out.println("\ttitle = \"Primeiro Quarteto\"");
        System.out.println("\tcomposer = \"Tahbata Ali\"");
        System.out.println("}");
        System.out.println("\n");
    }

    private static void GenerateFooter() {
        System.out.println("\n");
        System.out.println("\t\\layout{");
        System.out.println("\t\tindent = 20\\mm");
        System.out.println("\t\tline-width = 190\\mm");
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
        System.out.println("\t\t\t\t\\clef bass");
        System.out.println("\t\t\t\t\\extendedNotesONE");
        System.out.println("\t\t\t}");

        // Cello 2
        System.out.println("\t\t\t\\new Staff = \"CelloTwo\" {");
        System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 2\"");
        // INSERT NOTES
        System.out.println("\t\t\t\t\\clef bass");
        System.out.println("\t\t\t\t\\extendedNotesTWO");
        System.out.println("\t\t\t}");

        //Cello 3
        System.out.println("\t\t\t\\new Staff = \"CelloThree\" {");
        System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 3\"");
        // INSERT NOTES
        System.out.println("\t\t\t\t\\clef bass");
        System.out.println("\t\t\t\t\\extendedNotesTHREE");
        System.out.println("\t\t\t}");

        //Cello 4
        System.out.println("\t\t\t\\new Staff = \"CelloFour\" {");
        System.out.println("\t\t\t\t\\set Staff.instrumentName = #\"Cello 4\"");
        // INSERT NOTES
        System.out.println("\t\t\t\t\\clef bass");
        System.out.println("\t\t\t\t\\extendedNotesFOUR");
        System.out.println("\t\t\t}");

        System.out.println("\t\t\t>>");
        System.out.println("\t\t>>");
        System.out.println("\t}");
    }


    private static void GenerateExtendedNotes1() {
        List<String> listOfNotes = Arrays.asList("a","b", "cis", "g", "gis");
        List<Staff> extendedNotes = extendNotes(55, 4, listOfNotes);

        Result cello1 = splitDuration(extendedNotes.get(0));
        Result cello2 = splitDuration(extendedNotes.get(1));
        Result cello3 = splitDuration(extendedNotes.get(2));
        Result cello4 = splitDuration(extendedNotes.get(3));

        Staff cello1WithSlur = insertSlur(cello1.getStaff());
        Staff cello2WithSlur = insertSlur(cello2.getStaff());
        Staff cello3WithSlur = insertSlur(cello3.getStaff());
        Staff cello4WithSlur = insertSlur(cello4.getStaff());

        System.out.println("extendedNotesONE = { ");
        for (int i = 0 ; i < cello1.getStaff().getNote().size() ; i++) {
            System.out.print(
                    cello1.getStaff().getNote().get(i) +
                            cello1.getStaff().getDuration().get(i).getLilypondDuration() +
                            cello1WithSlur.getSymbol().get(i) + " "

            );
        }
        System.out.println("}");

        System.out.println("extendedNotesTWO = { ");
        for (int i = 0 ; i < cello2.getStaff().getNote().size() ; i++) {
            System.out.print(
                    cello2.getStaff().getNote().get(i) +
                            cello2.getStaff().getDuration().get(i).getLilypondDuration() +
                            cello2WithSlur.getSymbol().get(i) + " "
            );
        }
        System.out.println("}");

        System.out.println("extendedNotesTHREE = { ");
        for (int i = 0 ; i < cello3.getStaff().getNote().size() ; i++) {
            System.out.print(
                    cello3.getStaff().getNote().get(i) +
                            cello3.getStaff().getDuration().get(i).getLilypondDuration() +
                            cello3WithSlur.getSymbol().get(i) + " "
            );
        }
        System.out.println("}");

        System.out.println("extendedNotesFOUR = { ");
        for (int i = 0 ; i < cello4.getStaff().getNote().size() ; i++) {
            System.out.print(
                    cello4.getStaff().getNote().get(i) +
                            cello4.getStaff().getDuration().get(i).getLilypondDuration() +
                            cello4WithSlur.getSymbol().get(i) + " "
            );
        }
        System.out.println("}");

        System.out.println("\n");

        /**
         * TRY CROSSOVER
         */
        GenerateCrossOver(cello1.getStaff(), cello2.getStaff(), cello3.getStaff(), cello3.getStaff());
    }

    private static void OLDGenerateExtendedNotes() {
        String[] notesSequence = new String[] {"d", "b", "a", "ais", "c"};
        String[] noteExtenderSequence = NoteExtender.Extend(25, 0, 6, notesSequence, 4);

        System.out.println("voiceOne = { ");
        System.out.println(noteExtenderSequence[0]);
        System.out.println("}");

        System.out.println("voiceTwo = { ");
        System.out.println(noteExtenderSequence[1]);
        System.out.println("}");

        System.out.println("voiceThree = { ");
        System.out.println(noteExtenderSequence[2]);
        System.out.println("}");

        System.out.println("voiceFour = { ");
        System.out.println(noteExtenderSequence[3]);
        System.out.println("}");
    }

    private static void GenerateGrupetos() {
        String[] notesOstinato = new String[]{"d", "e", "f"};
        String[] grupeto = Grupeto(32, notesOstinato, 15);

        String[] notesOstinato2 = new String[]{"a","b","c","d","e","f","g"};
        String[] grupeto2 = Grupeto(16, notesOstinato2, 50);

        System.out.println("grupetoONE = { ");
        for (String grupetoNotes : grupeto) {
            System.out.print(grupetoNotes + " ");
        }
        System.out.println("}");

        System.out.println("\n");

        System.out.println("grupetoTWO = { ");
        for (String grupetoNotes : grupeto2) {
            System.out.print(grupetoNotes + " ");
        }
        System.out.println("}");

        System.out.println("\n");
    }

    private static void GenerateCrossOver(Staff one, Staff two, Staff three, Staff four) {

        Result crossover1 = SimpleCrossover(one, two);
        Result crossover2 = SimpleCrossover(two, three);
        Result crossover3 = SimpleCrossover(three, four);
        Result crossover4 = SimpleCrossover(four, one);

        System.out.println("crossoverONE = { ");
        for (int i = 0 ; i < crossover1.getStaff().getNote().size() ; i++) {
            System.out.print(crossover1.getStaff().getNote().get(i) + crossover1.getStaff().getDuration().get(i).getLilypondDuration() + " ");
        }
        System.out.println("}");

        System.out.println("crossoverTWO = { ");
        for (int i = 0 ; i < crossover2.getStaff().getNote().size() ; i++) {
            System.out.print(crossover2.getStaff().getNote().get(i) + crossover2.getStaff().getDuration().get(i).getLilypondDuration() + " ");
        }
        System.out.println("}");

        System.out.println("crossoverTHREE = { ");
        for (int i = 0 ; i < crossover3.getStaff().getNote().size() ; i++) {
            System.out.print(crossover3.getStaff().getNote().get(i) + crossover3.getStaff().getDuration().get(i).getLilypondDuration() + " ");
        }
        System.out.println("}");

        System.out.println("crossoverFOUR = { ");
        for (int i = 0 ; i < crossover4.getStaff().getNote().size() ; i++) {
            System.out.print(crossover4.getStaff().getNote().get(i) + crossover4.getStaff().getDuration().get(i).getLilypondDuration() + " ");
        }
        System.out.println("}");

        System.out.println("\n");
    }
}
