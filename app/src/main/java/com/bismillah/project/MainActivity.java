package com.bismillah.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.project.adapter.UserAdapter;
import com.bismillah.project.api.helper.ServiceGenerator;
import com.bismillah.project.api.models.Envelope;
import com.bismillah.project.api.models.ResponeAllUser;
import com.bismillah.project.api.models.ResponeUser;
import com.bismillah.project.api.services.ApiInterface;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ItemAdapter itemAdapter;
    private FastAdapter fastAdapter;
    private RecyclerView UserView;
    private View mUserLayout;
    List git;
    private List github = new ArrayList<>();
    String username;
    String avatar;
    private int page;
    private Button load;
    private TextView dataLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserView = findViewById(R.id.recycler_list);
        git = new ArrayList();
        itemAdapter = new ItemAdapter();
        fastAdapter = FastAdapter.with(itemAdapter);
        mUserLayout= findViewById(R.id.pop);
        load = findViewById(R.id.load);
        dataLoad = findViewById(R.id.dataLoad);

        dataLoad.setVisibility(View.INVISIBLE);

        getSupportActionBar().setTitle("GitHub");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                prog.setVisibility(View.GONE);
                UserView.setVisibility(View.VISIBLE);
            }
        },2000);
        page = 1;

    }

    public void getData( ){

        ApiInterface service = ServiceGenerator.createService(ApiInterface.class,"https://api.github.com");
       Call<List<ResponeAllUser>> call = service.getUsers(page);
       call.enqueue(new Callback<List<ResponeAllUser>>() {
           @Override
           public void onResponse(Call<List<ResponeAllUser>> call, Response<List<ResponeAllUser>> response) {
               if(response.isSuccessful()){
                   Snackbar snackbar = Snackbar
                           .make(mUserLayout, "Data Loaded ....", Snackbar.LENGTH_LONG);

                   snackbar.show();
                   itemAdapter = new ItemAdapter<>();
                   fastAdapter = FastAdapter.with(itemAdapter);
                   Toast.makeText(MainActivity.this, "hore berhasil", Toast.LENGTH_SHORT).show();
                   for(int i = 0 ; i < response.body().size(); i++){
                        avatar=response.body().get(i).getAvatarUrl();
                        username= response.body().get(i).getLogin();
//                        System.out.println(username);
                       github.add(new UserAdapter(avatar,username));

                   }
                   itemAdapter.add(github);
                   UserView.setAdapter(fastAdapter);
                   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                   UserView.setLayoutManager(layoutManager);

               }else {
                   Snackbar snackbar = Snackbar
                           .make(mUserLayout, "Data Load walah ..........", Snackbar.LENGTH_LONG);
                   snackbar.show();
               }
           }

           @Override
           public void onFailure(Call<List<ResponeAllUser>> call, Throwable t) {
               Snackbar snackbar = Snackbar
                       .make(mUserLayout, "Data Load Fail ..........", Snackbar.LENGTH_LONG);
               snackbar.show();
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