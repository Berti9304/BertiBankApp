package hu.bertalan.bank.bertalanbankapp;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    public Data dataBase = new Data();
    @BindView(R.id.emailLogin)  EditText email;
    @BindView(R.id.passwordLogin) EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataBase.syncFromDatabase();
        ButterKnife.bind(this);



    }



    protected void submitOnClick(View v){


        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        User loggedUser = null;
        for(int i=0;i<dataBase.userList.size();i++)
        {
            if(emailString.equals(dataBase.userList.get(i).getEmail()) && passwordString.equals(dataBase.userList.get(i).getPassword()))
            {
                loggedUser = dataBase.userList.get(i);
            }
        }
        if(loggedUser == null)
        {
            TextView loginErrorText = (TextView)findViewById(R.id.loginErrorText);
            loginErrorText.setText(R.string.login_error);
        }
        else
        {


            Intent i = new Intent();
            Bundle b = new Bundle();
            b.putParcelable("logged", loggedUser);
            i.putExtras(b);
            Bundle list = new Bundle();
            list.putParcelable("databaseList", dataBase);
            i.putExtras(list);
            i.setClass(this, MainActivity.class);
            startActivity(i);
        }


    }
}
