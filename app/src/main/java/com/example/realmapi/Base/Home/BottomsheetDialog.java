package com.example.realmapi.Base.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.realmapi.Base.Home.CourseAdapter;
import com.example.realmapi.Interface.ClickAdd;
import com.example.realmapi.Model.DataModel;
import com.example.realmapi.ViewModel.ViewModel;
import com.example.realmapi.databinding.BottomSheetAddLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import io.realm.Realm;

public class BottomsheetDialog extends BottomSheetDialogFragment {
    private CourseAdapter courseAdapter;
    private List<DataModel> dataModelList;
    private BottomSheetAddLayoutBinding binding;
    private ViewModel viewModel;
    private Realm realm;
    private ClickAdd mclickadd;

    public BottomsheetDialog() {
    }

    public BottomsheetDialog(ClickAdd mclickadd) {
        this.mclickadd = mclickadd;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetAddLayoutBinding.inflate(inflater,container,false);
        realm = Realm.getDefaultInstance();

        dataModelList = realm.where(DataModel.class).findAll();
        binding.btnBottAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
//                int a1 = Integer.parseInt(binding.edtBottId.getText().toString());
                int a = Integer.parseInt(binding.edtBottUserid.getText().toString());
                String b1 = binding.edtBottImg.getText().toString();
               String b = binding.edtBottTitle.getText().toString();
               String c = binding.edtBottBody.getText().toString();
               viewModel.addnewDataToDatabase(a,b1,b,c);
               mclickadd.onItemClickAdd(dataModelList);
            }
        });

        binding.btnBottClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return binding.getRoot();
    }
}
