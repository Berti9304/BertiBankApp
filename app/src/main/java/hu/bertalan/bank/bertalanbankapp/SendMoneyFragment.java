package hu.bertalan.bank.bertalanbankapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Berti on 2017. 01. 15..
 */

public class SendMoneyFragment extends Fragment {


    @BindView(R.id.recieverNumber) EditText reciever;
    @BindView(R.id.amountToSend) EditText amount;


    User loggedInUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sendmoney, container,false);
        ButterKnife.bind(this,v);
        return v;
    }

    public String getReciever(){

        return String.valueOf(reciever.getText());
    }
    public String getAmount(){
        return String.valueOf(amount.getText());
    }

    public void clearEditTexts()
    {
        reciever.setText("");
        amount.setText("");
    }
}
