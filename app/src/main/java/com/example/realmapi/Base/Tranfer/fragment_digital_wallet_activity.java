package com.example.realmapi.Base.Tranfer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.realmapi.Model.History;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.R;
import com.example.realmapi.databinding.ActivityFragmentDigitalWalletBinding;
import com.example.realmapi.databinding.FragmentTranferDialogBinding;

import java.util.Calendar;
import java.util.List;

public class fragment_digital_wallet_activity extends AppCompatActivity {

    private ActivityFragmentDigitalWalletBinding binding;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentDigitalWalletBinding.inflate(getLayoutInflater());
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        int k = i.getIntExtra("img",0);
        binding.tvtitle.setText("Tranfer money to "+name);
        binding.imglogo.setImageResource(k);
        dialog = new Dialog(this);

        binding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null){
                    for(int j = 0; j<Singleton.getListuser().size(); j++){
                        if((s.toString()).equals(Singleton.getListuser().get(j).getPhone()) == true){
                            binding.tvnameuser.setText(Singleton.getListuser().get(j).getName());
                        }
                    }
                }

            }
        });
        binding.edtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                readMoney(s.toString());
            }
        });
        FragmentTranferDialogBinding bindingtv = FragmentTranferDialogBinding.inflate(LayoutInflater.from(fragment_digital_wallet_activity.this));
        binding.btntranfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getListhistory().add(new History("Send","Send money to "+binding.tvnameuser.getText(), Calendar.getInstance().getTime(),Integer.parseInt(binding.edtMoney.getText().toString())));
                dialog.setContentView(bindingtv.getRoot());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        bindingtv.btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        setContentView(binding.getRoot());
    }

    private String readGroup(String group){
        String[] readDigit = {" Kh??ng", " M???t", " Hai", " Ba", " B???n", " N??m", " S??u", " B???y", " T??m", " Ch??n" };
        String temp = "";
        if (group == "000") return "";
        temp = readDigit[Integer.parseInt(group.substring(0,1))] + " Tr??m";

        if (group.substring(1,2).equals("0"))
            if (group.substring(2,3).equals("0")) return temp;
            else
            {
                temp += " L???" + readDigit[Integer.parseInt(group.substring(2,3))];
                return temp;
            }
        else
            temp += readDigit[Integer.parseInt(group.substring(1,2))] + " M????i";

        if (group.substring(2,3) == "5") temp += " L??m";
        else if (group.substring(2,3) != "0") temp += readDigit[Integer.parseInt(group.substring(2,3))];
        return temp;
    }

    public String readMoney(String num){
        if((num==null)||(num.equals(""))) return "";
        String temp = "";
        while (num.length() < 18)
        {
            num = "0" + num;
        }
        String g1 = num.substring(0, 3);
        String g2 = num.substring(3, 6);
        String g3 = num.substring(6, 9);
        String g4 = num.substring(9, 12);
        String g5 = num.substring(12,15);
        String g6 = num.substring(15,18);

        if (!g1.equals("000")){
            temp = readGroup(g1);
            temp += " Tri???u";
        }
        if (!g2.equals("000")){
            temp += readGroup(g2);
            temp += " Ngh??n";
        }
        if (!g3.equals("000")){
            temp += readGroup(g3);
            temp += " T???";
        } else if(!"".equals(temp)){
            temp += " T???";
        }
        if (!g4.equals("000")){
            temp += readGroup(g4);
            temp += " Tri???u";
        }
        if (!g5.equals("000")){
            temp += readGroup(g5);
            temp += " Ngh??n";
        }
        temp = temp + readGroup(g6);
        temp = temp.replaceAll("M???t M????i", "M?????i");
        temp = temp.trim();
        temp = temp.replaceAll("Kh??ng Tr??m", "");
        temp = temp.trim();
        temp = temp.replaceAll("M?????i Kh??ng", "M?????i");
        temp = temp.trim();
        temp = temp.replaceAll("M????i Kh??ng", "M????i");
        temp = temp.trim();
        if (temp.indexOf("L???")==0) temp = temp.substring(2);
        temp = temp.trim();
        temp = temp.replaceAll("M????i M???t", "M????i M???t");
        temp = temp.trim();
        binding.tvreadmoney.setText(temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase() + " ?????ng.");
        return "("+temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase() + " ?????ng ch???n./.)";
    }
}