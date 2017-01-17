package hu.bertalan.bank.bertalanbankapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Berti on 2017. 01. 15..
 */

public class Transaction implements Parcelable{

    private User sender;
    private User reciever;
    private long amount;




    public Transaction(User sender, User reciever, long amount) {
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amount;
    }

    protected Transaction(Parcel in) {
        User[] data = new User[2];
        in.readTypedArray(data,User.CREATOR);
        this.reciever = data[0];
        this.sender = data[1];
        this.amount= in.readLong();;

    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(new User[] {
                this.reciever,
                this.sender
        }, flags);
        dest.writeLong(this.amount);
    }
}
