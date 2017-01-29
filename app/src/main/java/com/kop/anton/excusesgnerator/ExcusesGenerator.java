package com.kop.anton.excusesgnerator;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * generator of the excuses
 * Created by Anton on 18.01.2017.
 */

public class ExcusesGenerator {
    private final String[] NAMES;
    private final String[] HELLO;
    private final String[] FAIL;
    private final String[] ACTION;
    private final String[] DATE;
    private final String[] GENERAL;


    public ExcusesGenerator(Context context) {
        NAMES = context.getResources().getStringArray(R.array.NAMES);
        HELLO = context.getResources().getStringArray(R.array.HELLO);
        FAIL = context.getResources().getStringArray(R.array.FAIL);
        ACTION = context.getResources().getStringArray(R.array.ACTION);
        DATE = context.getResources().getStringArray(R.array.DATE);
        GENERAL = context.getResources().getStringArray(R.array.GENERAL);
    }

    public String generateExcuse(@Nullable String name) {
        String excuse;
        if (name == null) {
            excuse = String.format(getRandomString(HELLO), getRandomString(NAMES));
        } else {
            excuse = String.format(getRandomString(HELLO), name);
        }
        excuse = excuse + " " + getRandomString(FAIL) + " " + getRandomString(ACTION) + " "
                + getRandomString(DATE) + " " + getRandomString(GENERAL);
        return excuse;
    }

    private String getRandomString(String[] strings) {
        int idx = new Random().nextInt(strings.length);
        return strings[idx];
    }

}
