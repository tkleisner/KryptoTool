package com.example.tom.kryptotool3000.cipherlibrary;

/**
 * Created by Tom on 17.06.2016.
 */
public class AtbasCipher implements ICipher {

    public String getName() {
        return "Atbas";
    }

    public String encrypt(String text, String key) {
        return atbas(text);
    }

    public String decrypt(String text, String key) {
        return atbas(text);
    }

    private String atbas(String text) {
        char[] chars = text.replace(" ", "").toLowerCase().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char letter = chars[i];

            int alphabetIndex = letter - 'a';
            alphabetIndex = 25 - alphabetIndex;

            letter = (char) (alphabetIndex + 'a');

            chars[i] = letter;
        }

        return new String(chars);
    }
}
