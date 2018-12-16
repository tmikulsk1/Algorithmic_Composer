package Utils;

import Composition.Staff;

public class Rest {

    public static String[] RestGenerator(int[] rhythm, int size, int beginAt) {
        String[] restList = new String[size];

        for (int i = 0 ; i < (size) ; i++ ) {
            restList[i] = "r" + rhythm[i+beginAt] + " ";
        }
        return restList;
    }

    public static Staff RestGeneratorForStaff(Staff baseStaff) {
        Staff output = new Staff();

        for (int i = 0 ; i < baseStaff.getDuration().size() ; i++) {
            output.component1().add(i, "r");
            output.component2().add(i, baseStaff.getDuration().get(i));
            output.component3().add(i, baseStaff.getSymbol().get(i));
        }

        return output;
    }
}