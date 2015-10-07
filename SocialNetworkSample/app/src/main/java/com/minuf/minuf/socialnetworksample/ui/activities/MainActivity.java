package com.minuf.minuf.socialnetworksample.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.tools.encryption.Encryption;
import com.minuf.minuf.socialnetworksample.tools.encryption.Encryption2;
import com.minuf.minuf.socialnetworksample.tools.encryption.MCrypt;

/**
 * Created by jorge on 17/09/15.
 */
public class MainActivity extends AppCompatActivity {

    private String email, pass;
    private EditText et_email, et_pass;
    private Button btn_login, btn_signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createViews();

        try {
            encryptDecrypt();
        } catch (Exception e) {
            Log.e("ENCRYPTION ERROR: ", e.getMessage());
        }


    }
    public void encryptDecrypt() throws Exception {
        String message="This is just an example";
        String key = "clave";
        byte [] byteKey = new byte[16];
        for (int i=0; i<key.getBytes().length; i++){
            byteKey[i] = key.getBytes("UTF-8")[i];
        }
        Encryption encrypter = new Encryption(byteKey);

        byte[] encrypted = encrypter.encrypt(message.getBytes("UTF-8"));
        byte[] decrypted = encrypter.decrypt(encrypted);
        Log.e("ENCRYPTADO", encrypted.toString());
        Log.e("DESENCRYPTADO", new String(decrypted, "UTF-8"));
        //System.out.println(new String (decrypted, "UTF-8"));

        /////*****//////


        String text = "This is a plain text";
        String key2 = "clave";

        String crypted2 = Encryption2.cipher(key2, text);

        Log.e("CRYPTO-TEST", "plain: " + text);
        Log.e("CRYPTO-TEST", "crypted: " + crypted2);
        Log.e("CRYPTO-TEST", "crypted string : " + crypted2.toString());

        String decrypted2 = Encryption2.decipher("clave", crypted2);
        Log.e("CRYPTO-TEST", "decrypted: " + decrypted2);

        /*******************/

        String texto = "Texto password";
        MCrypt mcrypt = new MCrypt();
        /* Encrypt */
        String en = MCrypt.bytesToHex( mcrypt.encrypt(texto) );
        Log.e("MCrypt : Encrypted", en);
        /* Decrypt */
        String de = new String( mcrypt.decrypt(en));
        Log.e("MCrypt : Decrypted", de);




    }


    private void createViews(){
        et_email = (EditText)findViewById(R.id.et_login_mail);
        et_pass = (EditText)findViewById(R.id.et_login_pass);
        btn_login = (Button)findViewById(R.id.btn_login_login);
        btn_signin = (Button)findViewById(R.id.btn_login_signin);
    }

    //LISTENERS

    public void btnLoginListener(View v){
        email = et_email.getText().toString();
        pass = et_pass.getText().toString();

        if (!email.isEmpty() && !pass.isEmpty()){
            //log in
        }else {
            Snackbar.make(v, "Falta algún dato.", Snackbar.LENGTH_SHORT).show();
        }

    }

    public void btnSigninListener(View v) {
        // start activity signin
    }
}
