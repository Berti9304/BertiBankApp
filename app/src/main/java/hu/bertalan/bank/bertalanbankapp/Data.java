package hu.bertalan.bank.bertalanbankapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berti on 2017. 01. 12..
 */

public class Data implements Parcelable {

    public List<User> userList  = new ArrayList<User>();
    public List<Transaction> transactionList = new ArrayList<Transaction>();

    public Data() {

        User first = new User("Mark","berti@gmail.com","Bertalan","abc123","4322","Ruheron","31","123123123",500000,"Nyirtarcsony","Mucsarocsoge");
        User second = new User("Pista","pista@gmail.com","Kiss","bbc321","1100","Rhode","11","666666666",2321102,"California","USA");
        User third = new User("Gergő","gergely@gmail.com","Nagy","bbc321","3432","Saint Nick","43","324324324",322222,"Los Angeles","USA");
        userList.add(first);
        userList.add(second);
        userList.add(third);
        Transaction tfirst = new Transaction(first,second, 50000);
        Transaction tsecond = new Transaction(second,first, 15000);
        Transaction tthird = new Transaction(first,second,40000);
        Transaction tfour  = new Transaction(third,second, 30000);
        Transaction tfive = new Transaction(third,first, 55555);
        Transaction tsix = new Transaction(second,third, 32113);
        Transaction tseven = new Transaction(first,third, 44000);
        transactionList.add(tfirst);
        transactionList.add(tsecond);
        transactionList.add(tthird);
        transactionList.add(tfour);
        transactionList.add(tfive);
        transactionList.add(tsix);
        transactionList.add(tseven);

    }

    public void setTransactionList(List<Transaction> setList)
    {
        this.transactionList = setList;
    }

    public List<Transaction> reduceTransactionList(User user){

        List<Transaction> moddedList = new ArrayList<Transaction>();
        for(int i=0;i<transactionList.size();i++)
        {
            if(user.getAccountNumber().equals(transactionList.get(i).getReciever().getAccountNumber()) || user.getAccountNumber().equals(transactionList.get(i).getSender().getAccountNumber()))
            {
                moddedList.add(transactionList.get(i));

            }

        }

        return moddedList;
    }


    protected Data(Parcel in) {
        in.readTypedList(userList, User.CREATOR);
        in.readTypedList(transactionList, Transaction.CREATOR);
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(userList);
        dest.writeTypedList(transactionList);

    }
}
