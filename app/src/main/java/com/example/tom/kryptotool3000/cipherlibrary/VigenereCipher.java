package com.example.tom.kryptotool3000.cipherlibrary;

/**
 * Vigenere
 */
public class VigenereCipher implements ICipher {

    public String getName() {
        return "Weissental";
    }

    public boolean validateKey(String key)
    {
        // klicem muze byt libovolne slovo slozene pouze z pismen anglicke abecedy

        if (key.length() == 0)
            return false;

        key = key.toUpperCase().replace(" ", "");
        for (int i = 0; i < key.length(); i++)
        {
            if (key.charAt(i) < 'A' || key.charAt(i) > 'Z')
                return false;
        }
        return true;
    }

    public String encrypt(String text, String key) {
        String res = "";
        text = text.toUpperCase().replace(" ", "");
        key = key.toUpperCase().replace(" ", "");
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }


    public String decrypt(String text, String key) {
        String res = "";
        text = text.toUpperCase().replace(" ", "");
        key = key.toUpperCase().replace(" ", "");
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
}
