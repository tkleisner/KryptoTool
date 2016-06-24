package com.example.tom.kryptotool3000.cipherlibrary;

/**
 * Spolecne rozhrani pro vsechny sifry. Pokud sifra nepracuje s klicem, klic se jednoduse ignoruje.
 */
public interface ICipher {
    //implementovat metodu tak aby vracela nazev, ktery bude videt v aplikaci
    // public String getName() {
    //  return "Vigenére";
    //}
    String getName();

    String encrypt(String text, String key);

    String decrypt(String text, String key);

    /**
     * Validace klice
     * @param key
     * @return true = v poradku, false = chybny klic
     */
    boolean validateKey(String key);
}
