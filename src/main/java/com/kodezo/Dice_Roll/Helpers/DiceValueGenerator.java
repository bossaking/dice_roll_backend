package com.kodezo.Dice_Roll.Helpers;

import java.util.Random;

public class DiceValueGenerator {

    public static int Generate(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

}
