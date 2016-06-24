package com.example.tom.kryptotool3000;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

        // fullscreen mod - bez status baru
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //seznam šifer
        ciphers = CipherRegister.getCipherList();

        //nastavit tlacitko
        Button b = (Button)findViewById(R.id.buttonSwitch);
        selectedCipher = ciphers.get(cipherIndex);
        b.setText(selectedCipher.getName());

        // pri stisku "enter" v textovem poli pro klic
        final EditText edittext = (EditText) findViewById(R.id.keyText);
        if (edittext != null) {
            edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_DONE)
                    {
                        setKeyboardState(false);
                        return true;
                    }
                    return false;
                }
            });
        }
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
     * Otevreni/zavreni klavesnice
     * @param visible
     */
    protected void setKeyboardState(boolean visible)
    {
        View view = this.getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if (visible)
            inputMethodManager.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        else
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Zobrazi chybovy dialog s potvrzovacim tlacitkem "OK"
     * @param title
     * @param text
     */
    public void showErrorDialog(String title, String text)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            // otevreni/zavreni klavesnice
            case R.id.toggle_keyboard_menuopt:
            {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, 0);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Tlacitko pro sifrovani
     * @param v
     */
    protected void viewEncrypt(View v) {
        EditText plain = (EditText)findViewById(R.id.plainText);
        EditText cipher = (EditText)findViewById(R.id.cipherText);
        EditText key = (EditText)findViewById(R.id.keyText);

        setKeyboardState(false);

        if (!selectedCipher.validateKey(key.getText().toString()))
        {
            showErrorDialog("Chybný klíč", "Vámi zadaný klíč neodpovídá požadavkům šifry");
            return;
        }

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

        setKeyboardState(false);

        if (!selectedCipher.validateKey(key.getText().toString()))
        {
            showErrorDialog("Chybný klíč", "Vámi zadaný klíč neodpovídá požadavkům šifry");
            return;
        }

        String decrypted = selectedCipher.decrypt(cipher.getText().toString(), key.getText().toString());

        plain.setText(decrypted);
    }
}
