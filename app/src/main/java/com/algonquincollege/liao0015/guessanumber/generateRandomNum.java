package com.algonquincollege.liao0015.guessanumber;

import java.util.Random;

/**
 * Created by boliao on 2015-10-04.
 */
public class generateRandomNum {

    Random randomNumGenerator = new Random();

    int logNum (){
        int randomInt = randomNumGenerator.nextInt(1000)+1;

        return randomInt;
    }

}
