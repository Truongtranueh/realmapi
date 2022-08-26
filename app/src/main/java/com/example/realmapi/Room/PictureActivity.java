package com.example.realmapi.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.realmapi.Base.Tranfer.Fragment_BankAccount;
import com.example.realmapi.Interface.UpdatePicture;
import com.example.realmapi.R;
import com.example.realmapi.databinding.ActivityBankAccountPictureBinding;

public class PictureActivity extends AppCompatActivity {
    private ActivityBankAccountPictureBinding binding;
    UpdatePicture mupdatepicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankAccountPictureBinding.inflate(getLayoutInflater());

        Uri myuri = Uri.parse(getIntent().getStringExtra("img"));
        binding.imgpic.setImageURI(myuri);
        binding.btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent();
                    i.putExtra("imgresult",String.valueOf(myuri));
                    setResult(Activity.RESULT_OK,i);
                    finish();
            }
        });

        setContentView(binding.getRoot());
    }
}