package com.example.habib.urrehman.classtask;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.habib.urrehman.classtask.api.Users;

import java.util.ArrayList;

public class PersonAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Users> persons = new ArrayList<>();


    public PersonAdaptor() {
    }


    public void setChatEntities(ArrayList<Users> persons) {
        this.persons = persons;
    }

    public static class CallViewHolder extends RecyclerView.ViewHolder {
        public TextView name, idForPerson, email, gender,status;


        public CallViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            //

            name = view.findViewById(R.id.personName);
//            idForPerson = view.findViewById(R.id.idForPerson);
//            email = view.findViewById(R.id.email);
//            gender = view.findViewById(R.id.gender);
//            status = view.findViewById(R.id.status);

        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_view, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PersonDetails.class);
                intent.putExtra("personName",persons.get(viewType).getName());
                intent.putExtra("personId",persons.get(viewType).getId());
                intent.putExtra("personGender",persons.get(viewType).getGender());
                intent.putExtra("personStatus",persons.get(viewType).getStatus());
                intent.putExtra("personEmail",persons.get(viewType).getEmail());


//                intent.putExtra("date",callEntities.get(viewType).getCallDate());
//                intent.putExtra("callType",callEntities.get(viewType).getCallType());
//
                view.getContext().startActivity(intent);

//                Toast.makeText(view.getContext(), "" + viewType, Toast.LENGTH_SHORT).show();
            }
        });
        return new CallViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CallViewHolder callViewHolder = (CallViewHolder) holder;
        Users users = persons.get(position);
        callViewHolder.name.setText(users.getName());
//      callViewHolder.idForPerson.setText(users.getId());
//        callViewHolder.email.setText(users.getEmail());
//        callViewHolder.gender.setText(users.getGender());
//        callViewHolder.status.setText(users.getStatus());


    }
}