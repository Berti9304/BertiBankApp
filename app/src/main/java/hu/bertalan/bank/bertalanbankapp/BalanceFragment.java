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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Berti on 2017. 01. 14..
 */

public class BalanceFragment extends Fragment {

    private static final String USER_KEY = "logged";
    private User loggedInUser;
    @BindView(R.id.name) TextView displayName;
    @BindView(R.id.accountNumber) TextView displayAccountNumber;
    @BindView(R.id.amount) TextView displayBalance;



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
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayName.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        displayBalance.setText(String.valueOf(loggedInUser.getBalance()));
        displayAccountNumber.setText(loggedInUser.getAccountNumber());

    }
}
