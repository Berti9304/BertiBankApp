package hu.bertalan.bank.bertalanbankapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableFuture;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * Created by Berti on 2017. 01. 12..
 */

public class Data implements Parcelable {

    public List<User> userList  = new ArrayList<User>();
    public List<Transaction> transactionList = new ArrayList<Transaction>();
    private static final String url = "jdbc:mysql://192.168.1.78:3306/berti_bank_database";
    private static final String user="basic_user";
    private static final String pass="basicpass";

    public Data() {

        this.syncFromDatabase();


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


    public void syncFromDatabase() {

        final ArrayList<User> tempList = new ArrayList<User>();
        Runnable r = new Runnable() {   // Get all users
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, pass);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from user_data");
                    ResultSetMetaData rsmd = rs.getMetaData();

                    while (rs.next()) {
                        User temp = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getLong(11));
                        tempList.add(temp);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        };

        Thread thread = new Thread(r);
        thread.start();

        this.userList = tempList;


    }




    public void syncUserToDatabase(final User temp)
    {


        Runnable r = new Runnable() {   // update
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url,user,pass);
                    Statement st = con.createStatement();
                    String query = "UPDATE user_data SET first_name='" + temp.getFirstName() + "',last_name='" + temp.getLastName() + "',email='" + temp.getEmail() +
                            "',country='" + temp.getCountry() + "',city='" + temp.getCity() + "',postal_code='" + temp.getPostalCode() + "',street_name='" + temp.getStreet() +
                            "',street_number='" + temp.getStreetNumber() + "',balance='" + temp.getBalance() + "' WHERE account_number='" + temp.getAccountNumber() + "'";
                    st.executeUpdate(query);





                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        };

        Thread thread = new Thread(r);
        thread.start();


    }
}
