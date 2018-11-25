package Utils;

import java.util.Random;

class Randomic {
    static Boolean RandomGenerator(int levelAdjust) {
        Random willPass = new Random();
        boolean isRandom = false;

        switch (levelAdjust) {
            case 1:
                if (willPass.nextBoolean()) isRandom = true;
                break;
            case 2:
                if (willPass.nextBoolean()) {
                    if (willPass.nextBoolean()) isRandom = true;
                }
                break;
            case 3:
                if (willPass.nextBoolean()) {
                    if (willPass.nextBoolean()) {
                        if(willPass.nextBoolean()) isRandom = true;
                    }
                }
                break;
            case 4:
                if (willPass.nextBoolean()) {
                    if (willPass.nextBoolean()) {
                        if(willPass.nextBoolean()) {
                            if(willPass.nextBoolean()) isRandom = true;
                        }
                    }
                }
                break;
            case 5:
                if (willPass.nextBoolean()) {
                    if (willPass.nextBoolean()) {
                        if(willPass.nextBoolean()) {
                            if(willPass.nextBoolean()) {
                                if(willPass.nextBoolean()) isRandom = true;
                            }
                        }
                    }
                }
                break;
        }
        return isRandom;
    }
}
