package com.bismillah.project;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.project.adapter.UserAdapter;
import com.bismillah.project.api.helper.ServiceGenerator;
import com.bismillah.project.api.models.Item;
import com.bismillah.project.api.models.ItemResponse;
import com.bismillah.project.api.services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ProgressDialog pd;
    private RecyclerView UserView;
    private int page;
    private Button load;
    private TextView dataLoad;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataLoad = findViewById(R.id.dataLoad);
        load = findViewById(R.id.load);
        UserView = findViewById(R.id.recycler_list);
        UserView.setVisibility(View.INVISIBLE);
        dataLoad.setVisibility(View.INVISIBLE);

        getData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               UserView.setVisibility(View.VISIBLE);
            }
        },2000);
        page = 1;
        initViews();


    }
    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        getData();
    }
    public void getData( ){
        ServiceGenerator Client = new ServiceGenerator();
        ApiInterface service = Client.getClient().create(ApiInterface.class);
        Call<ItemResponse> call = service.getItems(page);
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new UserAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    pd.hide();
                    }else{
                        Toast.makeText(MainActivity.this, "Sorry, this word didn't match our record.Please check again.", Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure load data", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void handleSearch(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void handleLoadMore(View view){
        page++;
        getData();
        load.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(page == 4){
                    dataLoad.setVisibility(View.VISIBLE);
                    load.setVisibility(View.GONE);
                    UserView.setVisibility(View.VISIBLE);
                }else{
                    dataLoad.setVisibility(View.INVISIBLE);
                    load.setVisibility(View.VISIBLE);
                }
            }
        },2000);
    }

}