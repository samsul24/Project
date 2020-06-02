package com.bismillah.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bismillah.project.api.helper.ServiceGenerator;
import com.bismillah.project.api.models.ResponeUser;
import com.bismillah.project.api.services.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity {
    EditText username;
    String userValue;
    RelativeLayout boxResult;
    TextView user, urll,id1,type;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        user=findViewById(R.id.nama);
        username=findViewById(R.id.nameView);
        urll=findViewById(R.id.urllView);
        id1=findViewById(R.id.idView);
        type=findViewById(R.id.typeView);
        boxResult= findViewById(R.id.boxresult);
        boxResult.setVisibility(View.INVISIBLE);

    }
    public void getData(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<ResponeUser> call=service.getUser(userValue);
        call.enqueue(new Callback<ResponeUser>() {
            @Override
            public void onResponse(Call<ResponeUser> call, retrofit2.Response<ResponeUser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SearchActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    for (int i = 0 ; i< response.body().getItems().size() ; i++) {
                        user.setText("Username  \t\t: " + response.body().getItems().get(i).getLogin());
                        id1.setText("Id Github \t\t\t\t: " + response.body().getItems().get(i).getId());
                        urll.setText("Url Github  \t\t\t: " + response.body().getItems().get(i).getHtmlUrl());
                        type.setText("Status  \t\t\t\t\t: " + response.body().getItems().get(i).getType());
                    }
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
        userValue=username.getText().toString();
        boxResult.setVisibility(View.VISIBLE);
        getData();
    }
}
