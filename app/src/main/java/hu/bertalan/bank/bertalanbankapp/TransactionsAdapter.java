package hu.bertalan.bank.bertalanbankapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Berti on 2017. 01. 15..
 */

public class TransactionsAdapter extends
        RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View transactionView = inflater.inflate(R.layout.item_transaction, parent, false);
        ViewHolder vh = new ViewHolder(transactionView);
        return vh;
    }

    @Override
    public void onBindViewHolder(TransactionsAdapter.ViewHolder holder, int position) {

        Transaction transaction = mTransactions.get(position);
        TextView sender = holder.sender;
        TextView reciever = holder.reciever;
        TextView amount = holder.amount;
        sender.setText(transaction.getSender().getFirstName() + " " + transaction.getSender().getLastName());
        reciever.setText(transaction.getReciever().getFirstName() + " " + transaction.getReciever().getLastName());
        amount.setText(String.valueOf(transaction.getAmount()));

    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView sender;
        public TextView reciever;
        public TextView amount;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            sender = (TextView)itemView.findViewById(R.id.sender);
            reciever= (TextView)itemView.findViewById(R.id.reciever);
            amount = (TextView)itemView.findViewById(R.id.transactionAmount);

        }
    }

    private List<Transaction> mTransactions;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TransactionsAdapter(Context context, List<Transaction> transactions) {
        mTransactions = transactions;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

}
