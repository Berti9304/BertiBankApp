package hu.bertalan.bank.bertalanbankapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Berti on 2017. 01. 14..
 */

public class BalanceFragment extends Fragment {

    private static final String USER_KEY = "logged";
    private User loggedInUser;

    public static BalanceFragment newInstance(User user)
    {
        BalanceFragment frag = new BalanceFragment();
        Bundle b = new Bundle();
        b.putParcelable(USER_KEY,user);
        frag.setArguments(b);
        return frag;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.balance_layout, container, false);
        loggedInUser = (User)getArguments().getParcelable(USER_KEY);
        TextView displayName = (TextView)v.findViewById(R.id.name);
        displayName.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        TextView displayAccountNumber = (TextView)v.findViewById(R.id.accountNumber);
        TextView displayBalance = (TextView)v.findViewById(R.id.amount);
        displayBalance.setText(String.valueOf(loggedInUser.getBalance()));
        displayAccountNumber.setText(loggedInUser.getAccountNumber());


        return v;
    }


}
