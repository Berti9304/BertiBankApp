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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Berti on 2017. 01. 15..
 */

public class TransactionsFragment extends Fragment {


    Data data;
    List<Transaction> transactionList;
    @BindView(R.id.rvTransactions)RecyclerView rc;

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
        ButterKnife.bind(this,v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = (Data)getArguments().getParcelable("dataBase");
        transactionList = data.transactionList;
        TransactionsAdapter adapter = new TransactionsAdapter(this.getContext(),transactionList);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
