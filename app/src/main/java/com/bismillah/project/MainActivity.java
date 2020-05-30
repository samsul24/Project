package com.bismillah.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private EditText usernameInput;
    String userValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.userInput);

        getSupportActionBar().setTitle("GitHub");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void handleRequest(View view) {
        userValue = usernameInput.getText().toString();
        getData();
    }
    public void getData( ){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Response> call = service.getKTP(nikValue,nameValue,"0d69c24e4c2c2a758be0fdfa1a687e3a055dbbfa0675c123450bc7c423946ae1");
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    tps.setText("TPS \t\t: " + response.body().getData().getTps());
                    nama_ktp.setText("Nama \t: " + response.body().getData().getNama());
                    tempat_lahir.setText("\tTempat_Lahir  \t\t\t\t\t: " + response.body().getData().getTempatLahir());
                    jenis_kelamin.setText("\tJenis Kelamin  \t\t\t\t: " + response.body().getData().getJenisKelamin());
                    nama_Propinsi.setText("\tNama Propinsi  \t\t\t\t: " + response.body().getData().getNamaPropinsi());
                    nama_Kabko.setText("\tNama Kab/Kota  \t\t\t: " + response.body().getData().getNamaKabko());
                    nama_Kec.setText("\tNama Kecamatan  \t: " + response.body().getData().getNamaKec());
                    nama_Kel.setText("\tNama Kelurahan  \t\t: " + response.body().getData().getNamaKel());
                }else{
                    Toast.makeText(MainActivity.this, "Sorry, this word didn't match our record.Please check again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure load data", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
