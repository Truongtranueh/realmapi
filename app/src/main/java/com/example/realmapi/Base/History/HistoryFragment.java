package com.example.realmapi.Base.History;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.realmapi.Model.History;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.R;
import com.example.realmapi.databinding.FragmentHistoryBinding;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.bson.codecs.jsr310.LocalDateCodec;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class HistoryFragment extends Fragment{


    private FragmentHistoryBinding binding;
    private List<History> historyList;
    private ArrayList<Entry> pieEntries;
    ArrayList<String> pieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;
    ArrayList<String> arrayListmonth;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater,container,false);

        pieEntries = new ArrayList<>();
        addValuetoPieEntry();
        pieEntryLabels = new ArrayList<>();
        addValuetoPieLable();
        pieDataSet = new PieDataSet(pieEntries,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        binding.piechart.setData(new PieData(pieEntryLabels,pieDataSet));
        binding.piechart.animateY(3000);
        binding.piechart.setDescription("Chart ------");


//        createHistory();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.rcvhistory.setLayoutManager(linearLayoutManager);

        HistoryAdapter historyAdapter = new HistoryAdapter(Singleton.getListhistory());
        binding.rcvhistory.setAdapter(historyAdapter);

        arrayListmonth = new ArrayList<>();
        addMonthToSpiner();
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,arrayListmonth);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.seemorespiner.setAdapter(adapter);
        binding.seemorespiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("aaa",String.valueOf(arrayListmonth.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return binding.getRoot();
    }
    private void addValuetoPieEntry(){
        pieEntries.add(new Entry(2f, 0));
        pieEntries.add(new Entry(4f, 1));
        pieEntries.add(new Entry(6f, 2));
        pieEntries.add(new Entry(8f, 3));
        pieEntries.add(new Entry(7f, 4));
        pieEntries.add(new Entry(3f, 5));
    }
    private void addValuetoPieLable(){
        pieEntryLabels.add("January");
        pieEntryLabels.add("February");
        pieEntryLabels.add("March");
        pieEntryLabels.add("April");
        pieEntryLabels.add("May");
        pieEntryLabels.add("June");
    }
    private void addMonthToSpiner(){
        arrayListmonth.add("See more");
        arrayListmonth.add("5/2022");
        arrayListmonth.add("6/2022");
        arrayListmonth.add("7/2022");
        arrayListmonth.add("8/2022");
    }


}