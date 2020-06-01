package com.bismillah.project;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private EditText usernameInput;
    private ItemAdapter itemAdapter;
    private FastAdapter fastAdapter;
    private RecyclerView UserView;
    private int page;
    List git;
    private List github = new ArrayList<>();
    String userValue;
    String username;
    String url;
    String avatar;
    TextView name;
    TextView followers;
    TextView following;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.userInput);
        UserView = findViewById(R.id.recycler_list);
        git = new ArrayList();
        itemAdapter = new ItemAdapter();
        fastAdapter = FastAdapter.with(itemAdapter);
        getSupportActionBar().setTitle("GitHub");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                UserView.setVisibility(View.VISIBLE);
//            }
//        },2000);
//        page = 1;

    }
    public void getData( ){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<ResponeUser> call = service.getUser(usernameInput.getText().toString());
        call.enqueue(new Callback<ResponeUser>() {
            @Override
            public void onResponse(Call<ResponeUser> call, Response<ResponeUser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "sukses", Toast   .LENGTH_SHORT).show();
                    for(int i=0; i<response.body().getLogin().size(); i++) {
                        avatar = response.body().getLogin().get(i).getAvatarUrl();
                        username = response.body().getLogin().get(i).getLogin();
                        url = response.body().getLogin().get(i).getUrl();
                        System.out.println(username);
                        github.add(new UserAdapter(avatar, username, url));
                        itemAdapter.add(github);

                    }
                    UserView.setAdapter(fastAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    UserView.setLayoutManager(layoutManager);
                }else{
                    Toast.makeText(MainActivity.this, "Sorry, this word didn't match our record.Please check again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponeUser> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failure load data", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void handleRequest(View view) {
        usernameInput = findViewById(R.id.userInput);
        getData();
    }
}