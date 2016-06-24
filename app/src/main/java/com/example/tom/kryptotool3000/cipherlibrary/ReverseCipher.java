package com.example.tom.kryptotool3000.cipherlibrary;

/**
 * Created by Tom on 24.06.2016.
 */
public class ReverseCipher implements ICipher {
    @Override
    public String getName() {
        return "Bombora";
    }

    public boolean validateKey(String key) { return true; }

    @Override
    public String encrypt(String text, String key) {
        return new StringBuilder(text).reverse().toString();
    }

    @Override
    public String decrypt(String text, String key) {
        return new StringBuilder(text).reverse().toString();
    }
}
