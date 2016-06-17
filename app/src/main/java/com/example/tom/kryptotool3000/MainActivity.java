package com.example.tom.kryptotool3000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tom.kryptotool3000.cipherlibrary.CipherRegister;
import com.example.tom.kryptotool3000.cipherlibrary.ICipher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * Seznam sifer prevzatych z tridy registru
     */
    private List<ICipher> ciphers;

    /**
     * Ukazatel do seznamu. Tlacitkem se cykluje pres seznam a meni sifry
     */
    private int cipherIndex = 0;

    /**
     * Aktualni zvolena sifra. pro volani metod encrypt a decrypt
     */
    private ICipher selectedCipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //seznam šifer
        ciphers = CipherRegister.getCipherList();

        //nastavit tlacitko
        Button b = (Button)findViewById(R.id.buttonSwitch);
        selectedCipher = ciphers.get(cipherIndex);
        b.setText(selectedCipher.getName());
    }

    /**
     * Obsluha tlacitka pro zmenu sifrovaci metody
     * @param v
     */
    protected void changeCipher(View v) {
        Button b = (Button)findViewById(R.id.buttonSwitch);

        selectedCipher = ciphers.get((cipherIndex++) % ciphers.size());

        b.setText(selectedCipher.getName());
    }

    /**
     * Tlacitko pro sifrovani
     * @param v
     */
    protected void viewEncrypt(View v) {
        EditText plain = (EditText)findViewById(R.id.plainText);
        EditText cipher = (EditText)findViewById(R.id.cipherText);
        EditText key = (EditText)findViewById(R.id.keyText);

        String encrypted = selectedCipher.encrypt(plain.getText().toString(), key.getText().toString());

        cipher.setText(encrypted);
    }

    /**
     * Tlacitko pro desifrovani
     * @param v
     */
    protected void viewDecrypt(View v) {
        EditText plain = (EditText)findViewById(R.id.plainText);
        EditText cipher = (EditText)findViewById(R.id.cipherText);
        EditText key = (EditText)findViewById(R.id.keyText);

        String decrypted = selectedCipher.decrypt(cipher.getText().toString(), key.getText().toString());

        plain.setText(decrypted);
    }
}
