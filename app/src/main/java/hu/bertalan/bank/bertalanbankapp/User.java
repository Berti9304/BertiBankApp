
package hu.bertalan.bank.bertalanbankapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Berti on 2017. 01. 12..
 */

public class User implements Parcelable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String country;
    private String city;
    private String postalCode;
    private String street;




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String streetNumber;
    private String accountNumber;
    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User(String accountNumber,String firstName, String lastName,String email,  String password,  String country, String city, String postalCode, String street, String streetNumber,  long balance) {

        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.country = country;
        this.city = city;

    }
    public User(Parcel in)
    {
        String[] data = new String[11];
        in.readStringArray(data);
                this.firstName = data[0];
                this.lastName= data[1];
                this.email= data[2];
                this.password= data[3];
                this.country= data[4];
                this.city= data[5];
                this.postalCode= data[6];
                this.street= data[7];
                this.streetNumber= data[8];
                this.accountNumber= data[9];
                this.balance= Long.parseLong(data[10]);

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
     dest.writeStringArray(new String[] {
                this.firstName,
                this.lastName,
                this.email,
                this.password,
                this.country,
                this.city,
                this.postalCode,
                this.street,
                this.streetNumber,
                this.accountNumber,
                String.valueOf(this.balance)
     });

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
