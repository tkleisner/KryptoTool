package com.example.tom.kryptotool3000.cipherlibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Registrace sifer. K pridani nove sifry do aplikace je potřeba upravit JEN tuto tridu.
 * Staci pridat do seznamu instanci tridy implementujici ICipher.
 */
public class CipherRegister {

    private static List<ICipher> cipherList = new ArrayList<ICipher>();

    public static List<ICipher> getCipherList() {
        cipherList.add(new AtbasCipher());
        cipherList.add(new CaesarCipher());
        cipherList.add(new VigenereCipher());
        cipherList.add(new TranspositionCipher());

        return cipherList;
    }
}
