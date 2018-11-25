package Utils;

public class Rest {

    public static String[] RestGenerator(int[] rhythm, int size, int beginAt) {
        String[] restList = new String[size];

        for (int i = 0 ; i < (size) ; i++ ) {
            restList[i] = "r" + rhythm[i+beginAt] + " ";
        }
        return restList;
    }
}