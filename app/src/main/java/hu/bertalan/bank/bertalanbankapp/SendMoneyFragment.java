package hu.bertalan.bank.bertalanbankapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Berti on 2017. 01. 15..
 */

public class SendMoneyFragment extends Fragment {


    EditText reciever;
    EditText amount;


    User loggedInUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sendmoney, container,false);
        reciever = (EditText)v.findViewById(R.id.recieverNumber);
        amount = (EditText)v.findViewById(R.id.amountToSend);
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
