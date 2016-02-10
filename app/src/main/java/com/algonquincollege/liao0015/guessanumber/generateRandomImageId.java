package com.algonquincollege.liao0015.guessanumber;

import java.util.Random;

/**
 * Created by boliao on 2015-10-06.
 */
public class generateRandomImageId {
    Random randomNumGenerator = new Random();

    int logNum (){
        int randomInt = randomNumGenerator.nextInt(6);

        return randomInt;
    }
}
