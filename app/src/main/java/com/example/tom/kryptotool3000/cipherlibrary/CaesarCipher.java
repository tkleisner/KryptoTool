package com.example.tom.kryptotool3000.cipherlibrary;

/**
 * Created by Tom on 17.06.2016.
 */
public class CaesarCipher implements ICipher {

    public String getName() {
        return "De Fleurs";
    }

    public boolean validateKey(String key)
    {
        // klicem muze byt libovolne cele kladne cislo (ac ma smysl pouze 0-26)

        if (key.length() == 0)
            return false;

        for (int i = 0; i < key.length(); i++)
        {
            if (key.charAt(i) < '0' || key.charAt(i) > '9')
                return false;
        }
        return true;
    }

    public String encrypt(String text, String key) {
        int shift = 0;

        try {
            shift = Integer.parseInt(key);
        }
        catch (Exception e){
            return "";
        }

        return caesar(text, shift);
    }

    public String decrypt(String text, String key) {
        int shift = 0;

        try {
            shift = Integer.parseInt(key);
        }
        catch (Exception e){
            return "";
        }

        shift *= -1;

        return caesar(text, shift);
    }

    private String caesar(String text, int shift) {
        char[] chars = text.replace(" ", "").toLowerCase().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char letter = chars[i];

            letter = (char) (letter + shift);
            if (letter > 'z') {
                letter = (char) (letter - 26);
            } else if (letter < 'a') {
                letter = (char) (letter + 26);
            }

            chars[i] = letter;
        }

        return new String(chars);
    }
}