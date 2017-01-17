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

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {


    User loggedInUser;
   @BindView(R.id.ps_saveButton) Button saveButton;
   @BindView(R.id.ps_firstName) EditText firstName ;
    @BindView(R.id.ps_lastName) EditText lastName;

    @BindView(R.id.ps_country) EditText country ;
    @BindView(R.id.ps_city) EditText city;
    @BindView(R.id.ps_postalCode) EditText postalCode;
    @BindView(R.id.ps_streetName) EditText streetName;
    @BindView(R.id.ps_streetNumber) EditText streetNumber;
    @BindView(R.id.ps_email) EditText email;


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
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName.setText(loggedInUser.getFirstName());
        lastName.setText(loggedInUser.getLastName());

        country.setText(loggedInUser.getCountry());
        city.setText(loggedInUser.getCity());
        postalCode.setText(loggedInUser.getPostalCode());
        streetName.setText(loggedInUser.getStreet());
        streetNumber.setText(loggedInUser.getStreetNumber());
        email.setText(loggedInUser.getEmail());

    }

    public User savingTheUser()
    {
        loggedInUser.setFirstName(String.valueOf(firstName.getText()));
        loggedInUser.setLastName(String.valueOf(lastName.getText()));

        loggedInUser.setCountry(String.valueOf(country.getText()));
        loggedInUser.setCity(String.valueOf(city.getText()));
        loggedInUser.setPostalCode(String.valueOf(postalCode.getText()));
        loggedInUser.setStreet(String.valueOf(streetName.getText()));
        loggedInUser.setStreetNumber(String.valueOf(streetNumber.getText()));
        loggedInUser.setEmail(String.valueOf(email.getText()));
        return loggedInUser;
    }




}
