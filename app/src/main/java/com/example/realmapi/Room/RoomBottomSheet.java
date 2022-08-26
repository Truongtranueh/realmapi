package com.example.realmapi.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.realmapi.Interface.CLickChoosePicture;
import com.example.realmapi.Interface.ShowImage;
import com.example.realmapi.databinding.BottomsheetRoomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.ByteArrayOutputStream;

public class RoomBottomSheet extends BottomSheetDialogFragment{

    CLickChoosePicture mclickchoosepicture;
    public RoomBottomSheet( CLickChoosePicture clickchoosepicture) {
        mclickchoosepicture = clickchoosepicture;
    }

    private BottomsheetRoomBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomsheetRoomBinding.inflate(inflater,container,false);
        binding.cvSelectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickchoosepicture.Clickchoosepicture(5);
                dismiss();
            }
        });
        binding.cvLiveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickchoosepicture.Clickchoosepicture(6);
                dismiss();
            }
        });

        return binding.getRoot();
    }
}
