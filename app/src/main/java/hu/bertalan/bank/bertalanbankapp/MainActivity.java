package hu.bertalan.bank.bertalanbankapp;



import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    User loggedInUser;
    BalanceFragment balanceFragment;
    Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            loggedInUser = b.getParcelable("logged");
            data = b.getParcelable("databaseList");
        }
        balanceFragment = BalanceFragment.newInstance(loggedInUser);
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, balanceFragment);
        NavigationView nav = (NavigationView)findViewById(R.id.nav);
        fragmentTransaction.commit();
        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layoutreal);
        nav.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.menu_profile)
                        {


                            drawer.closeDrawer(GravityCompat.START);
                            switchToProfileFragment();

                        }
                        else if(id == R.id.menu_sendMoney)
                        {
                            drawer.closeDrawer(GravityCompat.START);
                            switchToSendMoneyFragment();

                        }
                        else if(id == R.id.transactionHistory)
                        {
                            drawer.closeDrawer(GravityCompat.START);
                            switchToTransactions();

                        }


                        return true;


                    }
                }
        );





    }

    public void switchToProfileFragment(){
        ProfileFragment fragment = ProfileFragment.newInstance(loggedInUser);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragment,"profileSettingsFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }
    public void switchToTransactions(){

        Data moddedData = new Data();
        moddedData.setTransactionList(data.reduceTransactionList(loggedInUser));

        TransactionsFragment fragment = TransactionsFragment.newInstance(moddedData);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragment,"transactionFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void switchToBalanceFragment()
    {
        BalanceFragment fragment = BalanceFragment.newInstance(loggedInUser);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragment,"balanceFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void switchToSendMoneyFragment()
    {
        SendMoneyFragment fragment = new SendMoneyFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragment,"sendMoneyFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onSaveClick(View v)
    {
        FragmentManager fm = getSupportFragmentManager();
        ProfileFragment pf = (ProfileFragment)fm.findFragmentByTag("profileSettingsFragment");
        loggedInUser = pf.savingTheUser();
        switchToBalanceFragment();



    }

   public void onSendMoneyClick(View v)
   {
       FragmentManager fm  = getSupportFragmentManager();
       SendMoneyFragment smf = (SendMoneyFragment)fm.findFragmentByTag("sendMoneyFragment");
       String reciever = smf.getReciever();
       String amount = smf.getAmount();
       boolean transactionSucces = false;
       for(int i=0; i<data.userList.size();i++ )
       {
           if(reciever.equals(data.userList.get(i).getAccountNumber()))
           {
             loggedInUser.setBalance(loggedInUser.getBalance()-Long.parseLong(amount));
             data.userList.get(i).setBalance(data.userList.get(i).getBalance() + Long.parseLong(amount));
               transactionSucces = true;
               data.transactionList.add(new Transaction(loggedInUser,data.userList.get(i),Long.parseLong(amount)));
           }
       }
       if(transactionSucces == false)
       {
           Toast transactionToast = Toast.makeText(getApplicationContext(),"Transaction failed", Toast.LENGTH_SHORT);
           transactionToast.show();
       }
       else
       {
           Toast transactionToast = Toast.makeText(getApplicationContext(),"Transaction completed", Toast.LENGTH_SHORT);
           smf.clearEditTexts();
           transactionToast.show();
       }
   }




}

