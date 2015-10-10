package home.vu.ercommerce.common.util;

import java.util.Random;

public class SSSTestUtils {

    /**
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static float randFloat(float min, float max) {

        Random rand = new Random();

        float randomNum = min + rand.nextFloat() * (max - min);

        return randomNum;
    }
}
