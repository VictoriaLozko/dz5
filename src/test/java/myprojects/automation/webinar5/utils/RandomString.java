package myprojects.automation.webinar5.utils;

import java.util.Random;

public class RandomString {

    public static String getRandomString(int cnt) {
        String STRING_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < cnt) { // length of the random string.
            int index = (int)((rnd.nextFloat() * STRING_CHARS.length())%52);
            str.append(STRING_CHARS.charAt(index));
        }
        String string = str.toString();
        return string;

    }
}