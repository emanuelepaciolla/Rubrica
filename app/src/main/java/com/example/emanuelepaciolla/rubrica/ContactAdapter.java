package com.example.emanuelepaciolla.rubrica;

import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Emanuele Paciolla on 11/03/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contact> list_contact = new ArrayList<>();


    //Method of RecycleView.Adapter
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card, parent, false);
        return new ContactViewHolder(v);
    }

    public void setData(List<Contact> lista){
        list_contact = lista;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = list_contact.get(position);
        holder.name.setText(contact.getName());
        holder.surname.setText(contact.getSurname());
        holder.phonenumber.setText(contact.getCellphone());
        if (!(contact.getAddress().equals(""))) { holder.address.setVisibility(View.VISIBLE); holder.address.setText(contact.getAddress());} else { holder.address.setVisibility(View.GONE);}
        if (!(contact.getEmail().equals(""))) { holder.email.setVisibility(View.VISIBLE); holder.email.setText(contact.getEmail());} else { holder.email.setVisibility(View.GONE);}

    }

    @Override
    public int getItemCount() {
        return list_contact.size();
    }

    //Inner class to store a Contact and find a attribute on viewHolder
    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

    TextView name, surname, phonenumber, email, address;

        ContactViewHolder(View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((mainActivity)v.getContext()).position=getAdapterPosition();
                    return false;
                }
            });
            name = (TextView) itemView.findViewById(R.id.text_view_name);
            surname = (TextView) itemView.findViewById(R.id.text_view_surname);
            phonenumber = (TextView) itemView.findViewById(R.id.text_view_telephone);
            phonenumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_VIEW);
                    Uri uri = Uri.parse("tel:" + list_contact.get(getAdapterPosition()).getCellphone().trim());
                    intent.setData(uri);
                    (v.getContext()).startActivity(intent);

                }
            });
            address = (TextView) itemView.findViewById(R.id.text_view_address);

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_VIEW);
                    Uri uri = Uri.parse("geo:0,0?q=" + list_contact.get(getAdapterPosition()).getAddress().trim());
                    intent.setData(uri);
                    (v.getContext()).startActivity(intent);
                }
            });
            email = (TextView) itemView.findViewById(R.id.text_view_mail);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_SENDTO);
                    Uri uri = Uri.parse("mailto: " + list_contact.get(getAdapterPosition()).getEmail().trim());
                    intent.setData(uri);
                    (v.getContext()).startActivity(intent);

                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = ((mainActivity)v.getContext()).getMenuInflater();
            inflater.inflate(R.menu.menu_context, menu);
        }
    }
    void insertContact(Contact contact){
        list_contact.add(contact);
        notifyDataSetChanged();
    }
    void deleteContact(int posizione){
        list_contact.remove(posizione);
        notifyDataSetChanged();
    }
    void updateContact(int posizione, Contact contact){
        list_contact.set(posizione, contact);
        notifyDataSetChanged();
    }
}
