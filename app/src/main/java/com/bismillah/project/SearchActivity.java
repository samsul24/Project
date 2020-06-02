package com.bismillah.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bismillah.project.api.helper.ServiceGenerator;
import com.bismillah.project.api.models.ResponeUser;
import com.bismillah.project.api.services.ApiInterface;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    EditText username;
    String userValue;
    RelativeLayout boxResult;
    TextView user, urll,id,type,followers,following;
    private InterstitialAd mInterstitialAd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        user=findViewById(R.id.nama);
        username=findViewById(R.id.nameView);
        urll=findViewById(R.id.urllView);
        id=findViewById(R.id.idView);
        type=findViewById(R.id.typeView);
        followers=findViewById(R.id.follView);
        following=findViewById(R.id.flowingView);
        boxResult= findViewById(R.id.boxresult);
        boxResult.setVisibility(View.INVISIBLE);


    }
    public void getData(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<ResponeUser> call=service.getUser(userValue);
        call.enqueue(new Callback<ResponeUser>() {
            @Override
            public void onResponse(Call<ResponeUser> call, Response<ResponeUser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SearchActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    id.setText("Id Github \t: " + response.body().getId());
                    type.setText("\tStatus  \t\t\t\t\t: " + response.body().getType());
                    followers.setText("\tFollowers  \t\t\t\t: " + response.body().getFollowers());
                    following.setText("\tFollowing  \t\t\t\t: " + response.body().getFollowing());
                    urll.setText("\tUrl Github  \t\t\t: " + response.body().getUrl());

                }else{
                    Toast.makeText(SearchActivity.this, "Sorry, this word didn't match our record.Please check again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponeUser> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Failure load data", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
                Toast.makeText(SearchActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void handleRequest(View view) {

        userValue = username.getText().toString();
        boxResult.setVisibility(View.VISIBLE);
        getData();

    }
}
