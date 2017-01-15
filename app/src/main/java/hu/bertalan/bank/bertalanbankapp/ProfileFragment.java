package hu.bertalan.bank.bertalanbankapp;

import android.support.v4.app.Fragment;


import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {


    User loggedInUser;
    Button saveButton;
    EditText firstName ;
    EditText lastName;
    EditText accountNumber;
    EditText country ;
    EditText city;
    EditText postalCode;
    EditText streetName;
    EditText streetNumber;
    EditText email;


    public static ProfileFragment newInstance(User user)
    {
        ProfileFragment fragment = new ProfileFragment();
        Bundle b = new Bundle();
        b.putParcelable("logged",user);
        fragment.setArguments(b);
        return fragment;

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loggedInUser = (User)getArguments().getParcelable("logged");
        View v = inflater.inflate(R.layout.profile_fragment,container,false);
         firstName = (EditText)v.findViewById(R.id.ps_firstName);
         lastName = (EditText)v.findViewById(R.id.ps_lastName);
         accountNumber = (EditText)v.findViewById(R.id.ps_accountNumber);
         country = (EditText)v.findViewById(R.id.ps_country);
         city = (EditText)v.findViewById(R.id.ps_city);
         postalCode = (EditText)v.findViewById(R.id.ps_postalCode);
         streetName = (EditText)v.findViewById(R.id.ps_streetName);
         email = (EditText)v.findViewById(R.id.ps_email);
        streetNumber = (EditText)v.findViewById(R.id.ps_streetNumber);
        saveButton=(Button)v.findViewById(R.id.ps_saveButton);
        firstName.setText(loggedInUser.getFirstName());
        lastName.setText(loggedInUser.getLastName());
        accountNumber.setText(loggedInUser.getAccountNumber());
        country.setText(loggedInUser.getCountry());
        city.setText(loggedInUser.getCity());
        postalCode.setText(loggedInUser.getPostalCode());
        streetName.setText(loggedInUser.getStreet());
        streetNumber.setText(loggedInUser.getStreetNumber());
        email.setText(loggedInUser.getEmail());
        return v;
    }

    public User savingTheUser()
    {
        loggedInUser.setFirstName(String.valueOf(firstName.getText()));
        loggedInUser.setLastName(String.valueOf(lastName.getText()));
        loggedInUser.setAccountNumber(String.valueOf(accountNumber.getText()));
        loggedInUser.setCountry(String.valueOf(country.getText()));
        loggedInUser.setCity(String.valueOf(city.getText()));
        loggedInUser.setPostalCode(String.valueOf(postalCode.getText()));
        loggedInUser.setStreet(String.valueOf(streetName.getText()));
        loggedInUser.setStreetNumber(String.valueOf(streetNumber.getText()));
        loggedInUser.setEmail(String.valueOf(email.getText()));
        return loggedInUser;
    }




}
