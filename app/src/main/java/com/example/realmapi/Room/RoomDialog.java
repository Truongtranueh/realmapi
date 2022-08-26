package com.example.realmapi.Room;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.realmapi.Interface.Clickgetpositiondialog;
import com.example.realmapi.R;
import com.example.realmapi.databinding.DialogRoomBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RoomDialog extends DialogFragment {
    Clickgetpositiondialog mclickgetpostiondialog;
    private DialogRoomBinding binding;

    public RoomDialog(Clickgetpositiondialog mclickgetpostiondialog){
        this.mclickgetpostiondialog = mclickgetpostiondialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;
        getDialog().getWindow().setLayout(600,400);
    }

    @Override
    public int getTheme() {
        return R.style.CustomAlertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogRoomBinding.inflate(inflater,container,false);

        binding.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickgetpostiondialog.getpositon(binding.edtNameroom.getText().toString());
                dismiss();
            }
        });

        binding.btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return binding.getRoot();
    }
}
