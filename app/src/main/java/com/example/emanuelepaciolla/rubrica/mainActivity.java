package com.example.emanuelepaciolla.rubrica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Emanuele Paciolla on 11/03/2017.
 */

public class mainActivity extends Activity{


    //Declare a component of recycleview
    ContactAdapter adapter;
    RecyclerView contactReccycleView;
    RecyclerView.LayoutManager layoutManager;
    public int position;

    public static final int REQUEST_INTENT = 1;
    public static final int REQUEST_INTENT_2 =2;
    public static final String POSITION = "POSITION";
    Database D;

    //On create of recycle view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare main components of recycleview
        contactReccycleView = (RecyclerView) findViewById(R.id.mainRV);
        adapter = new ContactAdapter();
        layoutManager = new LinearLayoutManager(this);
        contactReccycleView.setLayoutManager(layoutManager);
        contactReccycleView.setAdapter(adapter);

        D = new Database(this);
        adapter.setData(D.getAllContact());

        Button btn_add = (Button) findViewById(R.id.button_add_recycle_view);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.this, addActivity.class);
                intent.putExtra("AGGIUNTA", REQUEST_INTENT );
                startActivityForResult(intent, REQUEST_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);
        if (resultCode == Activity.RESULT_OK && requestCode==REQUEST_INTENT){
            String name = data.getStringExtra(addActivity.KEY_NAME);
            String surname = data.getStringExtra(addActivity.KEY_SURNAME);
            String phonenumber = data.getStringExtra(addActivity.KEY_PHONE);
            Contact contact = new Contact(name, surname, phonenumber);
            if (!((data.getStringExtra(addActivity.KEY_MAIL)).equals(""))){
            String email = data.getStringExtra(addActivity.KEY_MAIL);
            contact.setEmail(email);}
            if (!(data.getStringExtra(addActivity.KEY_ADDRESS).equals(""))){
            String address = data.getStringExtra(addActivity.KEY_ADDRESS);
            contact.setAddress(address);}
            long ritorno = D.addContact(contact);
            contact.setID((int)ritorno);
            adapter.insertContact(contact);
        } else if (resultCode == Activity.RESULT_OK && requestCode==REQUEST_INTENT_2){
            Contact contact = adapter.list_contact.get(position);
            String name = data.getStringExtra(addActivity.KEY_NAME);
            contact.setName(name);
            String surname = data.getStringExtra(addActivity.KEY_SURNAME);
            contact.setSurname(surname);
            String phonenumber = data.getStringExtra(addActivity.KEY_PHONE);
            contact.setCellphone(phonenumber);
            if (!((data.getStringExtra(addActivity.KEY_MAIL)).equals(""))){
                String email = data.getStringExtra(addActivity.KEY_MAIL);
                contact.setEmail(email);}
            if (!(data.getStringExtra(addActivity.KEY_ADDRESS).equals(""))){
                String address = data.getStringExtra(addActivity.KEY_ADDRESS);
                contact.setAddress(address);}
            D.updateContact(contact);
            adapter.updateContact(position,contact);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete){
            D.deleteContact(adapter.list_contact.get(position));
            adapter.deleteContact(position);
        } else if (item.getItemId() == R.id.edit){
            Intent intent = new Intent(mainActivity.this, addActivity.class);
            intent.putExtra(POSITION, position);
            intent.putExtra(Database.KEY_NOME, adapter.list_contact.get(position).getName());
            intent.putExtra(Database.KEY_COGNOME, adapter.list_contact.get(position).getSurname());
            intent.putExtra(Database.KEY_TELEFONO, adapter.list_contact.get(position).getCellphone());
            intent.putExtra(Database.KEY_EMAIL, adapter.list_contact.get(position).getEmail());
            intent.putExtra(Database.KEY_INDIRIZZO, adapter.list_contact.get(position).getAddress());
            startActivityForResult(intent, REQUEST_INTENT_2);
        }
        return super.onContextItemSelected(item);
    }
}
