package com.example.tom.kryptotool3000.cipherlibrary;
import java.util.Arrays;


/**
 * Transpozicni
 */
public class TranspositionCipher implements ICipher {
    public String getName() {
        return "Trpasličí";
    }

    public boolean validateKey(String key)
    {
        // klicem muze byt libovolna posloupnost cisel od 1 do 9

        if (key.length() == 0)
            return false;

        for (int i = 0; i < key.length(); i++)
        {
            if (key.charAt(i) < '1' || key.charAt(i) > '9')
                return false;
        }
        return true;
    }

    public String encrypt(String text, String key) {
        String res = "";

        text = text.replace(" ", "");
        key = key.replace(" ", "");

        int[] keyPattern = getKeyPattern(key);

        // sifrovani / desifrovani
        int i = 0;
        while (i < text.length()) {
            char[] newBlock = new char[key.length()];
            char[] tmpBlock = new char[key.length()];

            Arrays.fill(newBlock, '#');
            Arrays.fill(tmpBlock, '#');

            for (int j = 0; j < key.length(); j++) {
                if (i >= text.length())
                    break;

                tmpBlock[j] = text.charAt(i);
                i++;
            }

            for (int j = 0; j < key.length(); j++) {
                if (keyPattern[j] < key.length())
                    newBlock[j] = tmpBlock[keyPattern[j]];
            }

            res += new String(newBlock);
        }

        return res;
    }

    public String decrypt(String text, String key) {
        String res = "";

        text = text.replace(" ", "");
        key = key.replace(" ", "");

        int[] keyPattern = getKeyPattern(key);

        // sifrovani / desifrovani
        int i = 0;
        while (i < text.length()) {
            char[] newBlock = new char[key.length()];
            Arrays.fill(newBlock, '#');

            for (int j = 0; j < key.length(); j++) {
                if (i >= text.length())
                    break;

                if (keyPattern[j] < key.length())
                    newBlock[keyPattern[j]] = text.charAt(i);
                i++;
            }

            res += new String(newBlock);
        }

        return res;
    }

    private int[] getKeyPattern(String key) {
        // vytvorit klic
        // pokud nepujde zparsovat, opise se cislo pozice
        int[] keyPattern = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            try {
                keyPattern[i] = Integer.parseInt(key.charAt(i) + "") - 1; // -1 -> index od 0
            } catch (Exception e) {
                keyPattern[i] = i;
            }
        }

        return keyPattern;
    }
}