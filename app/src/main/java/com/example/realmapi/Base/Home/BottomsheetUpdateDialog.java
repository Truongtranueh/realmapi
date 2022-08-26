package com.example.realmapi.Base.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.realmapi.Base.Home.CourseAdapter;
import com.example.realmapi.Interface.ClickUpdate;
import com.example.realmapi.Interface.OnLongClick;
import com.example.realmapi.Model.DataModel;
import com.example.realmapi.ViewModel.ViewModel;
import com.example.realmapi.databinding.BottomSheetUpdateLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import io.realm.Realm;

public class BottomsheetUpdateDialog extends BottomSheetDialogFragment implements OnLongClick{

    private CourseAdapter courseAdapter;
    private List<DataModel> dataModelList;
    private BottomSheetUpdateLayoutBinding binding;
    private ViewModel viewModel;
    private Realm realm;
    private ClickUpdate mclickupdate;


    public BottomsheetUpdateDialog() {
    }

    public BottomsheetUpdateDialog(ClickUpdate clickupdate) {
        this.mclickupdate = clickupdate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetUpdateLayoutBinding.inflate(inflater,container,false);

        realm = Realm.getDefaultInstance();
        dataModelList = realm.where(DataModel.class).findAll();


        binding.btnBottUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);

                int a = Integer.parseInt(binding.edtBottUserid.getText().toString());
                String b1 = binding.edtBottImg.getText().toString();
                String b = binding.edtBottTitle.getText().toString();
                String c = binding.edtBottBody.getText().toString();
                viewModel.updateDataToDatabase(a,b1,b,c);

                mclickupdate.onItemClickUpdate(dataModelList);

            }
        });
        binding.btnBotttClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void itemLongClick(int postion) {

    }
}
