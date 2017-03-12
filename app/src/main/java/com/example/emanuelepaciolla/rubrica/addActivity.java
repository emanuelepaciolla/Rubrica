package com.example.emanuelepaciolla.rubrica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Emanuele Paciolla on 11/03/2017.
 */

public class addActivity extends Activity {

    public static final String KEY_NAME = "key_name";
    public static final String KEY_SURNAME = "key_surname";
    public static final String KEY_ADDRESS = "key_address";
    public static final String KEY_PHONE = "key_phone";
    public static final String KEY_MAIL = "key_mail";

    EditText name;
    EditText surname;
    EditText address;
    EditText phone;
    EditText mail;

    FloatingActionButton fabconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Intent intent = getIntent();

        name = (EditText) findViewById(R.id.nome_edit_text);
        surname = (EditText) findViewById(R.id.cognome_edit_text);
        address = (EditText) findViewById(R.id.address_edit_text);
        phone = (EditText) findViewById(R.id.telefono_edit_text);
        mail = (EditText) findViewById(R.id.mail_edit_text);

        if (intent.getIntExtra(mainActivity.POSITION, -1)>-1){
            name.setText(intent.getStringExtra(Database.KEY_NOME));
            surname.setText(intent.getStringExtra(Database.KEY_COGNOME));
            address.setText(intent.getStringExtra(Database.KEY_INDIRIZZO));
            phone.setText(intent.getStringExtra(Database.KEY_TELEFONO));
            mail.setText(intent.getStringExtra(Database.KEY_EMAIL));
        }

        fabconfirm = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fabconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(name.getText().toString().equals("") || surname.getText().toString().equals("") || phone.getText().toString().equals(""))) {
                        CreaContatto();
                    } else
                        Toast.makeText(addActivity.this, "You cannot insert a contact without Name/Suraname/PhoneNumber", Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void CreaContatto(){
        Intent risposta = new Intent();
        risposta.putExtra(KEY_NAME, name.getText().toString());
        risposta.putExtra(KEY_SURNAME, surname.getText().toString());
        risposta.putExtra(KEY_PHONE, phone.getText().toString());
        if (!(address.getText().toString().equals(""))){
            System.out.println("address.getText().toString");
            risposta.putExtra(KEY_ADDRESS,address.getText().toString());
        } else if (address.getText().toString().equals("")) {risposta.putExtra(KEY_ADDRESS,"");}
        if (!(mail.getText().toString().equals(""))){
            risposta.putExtra(KEY_MAIL, mail.getText().toString());
        }  else if (mail.getText().toString().equals("")){risposta.putExtra(KEY_MAIL,"");}
        setResult(Activity.RESULT_OK, risposta);
        finish();
    }
}
