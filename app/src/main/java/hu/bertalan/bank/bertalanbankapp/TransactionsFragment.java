package hu.bertalan.bank.bertalanbankapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Berti on 2017. 01. 15..
 */

public class TransactionsFragment extends Fragment {


    Data data;
    List<Transaction> transactionList;

    public static TransactionsFragment newInstance(Data data)
    {
        TransactionsFragment fragment = new TransactionsFragment();
        Bundle b = new Bundle();
        b.putParcelable("dataBase",data);
        fragment.setArguments(b);
        return fragment;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transactions,container,false);
        RecyclerView rc = (RecyclerView)v.findViewById(R.id.rvTransactions);
        data = (Data)getArguments().getParcelable("dataBase");
        transactionList = data.transactionList;
        TransactionsAdapter adapter = new TransactionsAdapter(this.getContext(),transactionList);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
}
