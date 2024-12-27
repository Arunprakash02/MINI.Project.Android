package com.abc.projexct_customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Adapter.john{

    private RecyclerView recyclerView;

   private Button redirectButton;

    private Adapter adapter;
//    String ans = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        recyclerView = findViewById(R.id.review);
        fetchData();

        redirectButton = findViewById(R.id.redirect);
        redirectButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, intent_one.class);
            startActivity(intent);
        });
    }
    private void fetchData() {
        APIclient apiClient = APIclient.getapiClient();
        Call<jsMainGET> call = apiClient.getapiInterface().getData(82, 134, 2);
        call.enqueue(new Callback<jsMainGET>() {

            @Override
            public void onResponse(Call<jsMainGET> call, Response<jsMainGET> response) {

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API Corerect", "onResponse: " + "Successs");

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    Adapter adapter = new Adapter(MainActivity.this, response.body().getData(),MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API Error", "Response failed or empty");
                }
            }
            @Override
            public void onFailure(Call<jsMainGET> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void inter(jsValueGET items,
                      ImageView delete,
                      ImageView edit)
    {
        String ans = items.getActiveStatus();
        Log.d("Hiii", "join: " + items.getCustId() + "~~~" + items.getCustName() + "~~~~" + ans);
        delmethod(items, delete, edit,ans);

    }


    public void delmethod(jsValueGET items,
                          ImageView delete,
                          ImageView edit, String ans) {

//        String status = items.getActiveStatus();

        String str = "";

        if (ans.equals("A")) {
            str = "D";
        } else {
            str = "A";
        }


        APIclient api = APIclient.getapiClient();
        Call<jsMainDELETE> mall = api.getapiInterface().deldata(items.getCustId(), str, 468);

        String finals = ans;

        mall.enqueue(new Callback<jsMainDELETE>() {
            @Override
            public void onResponse(Call<jsMainDELETE> call, Response<jsMainDELETE> response) {
                fetchData();


                if (finals.equals("D")) {
                    edit.setVisibility(View.VISIBLE);
                    delete.setImageResource(R.drawable.delete_one);
                } else {
                    delete.setImageResource(R.drawable.delete);
                    edit.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<jsMainDELETE> call, Throwable t) {
                Log.d("solution ", "onFailure: " + "Failed ");

            }
        });
    }
}