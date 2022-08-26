package com.example.realmapi.Room;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.realmapi.Interface.CLickChoosePicture;
import com.example.realmapi.Interface.ClickChooseMenuRoom;
import com.example.realmapi.Interface.ClickShowBottomsheetCelsius;
import com.example.realmapi.Interface.ClickShowDialogRoom;
import com.example.realmapi.Interface.ClickShowPopupRoom;
import com.example.realmapi.Interface.Clickgetpositiondialog;
import com.example.realmapi.Interface.ShowImage;
import com.example.realmapi.Model.DataModel;
import com.example.realmapi.Model.Room;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.R;
import com.example.realmapi.databinding.ActivityRoomBinding;
import com.example.realmapi.databinding.DialogRoomBinding;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RoomActivity extends AppCompatActivity implements ClickShowDialogRoom, ClickShowPopupRoom, Clickgetpositiondialog, CLickChoosePicture, ClickChooseMenuRoom, ClickShowBottomsheetCelsius {
    private List<Room> listroom;
    private List<String> listmenu;
    private Realm realm;
    private RoomAdapter adapter;
    private RoommenuAdapter adaptermenu;
    private ActivityRoomBinding binding;
    int positonex = 1;
    int positionvd = 1;
    private boolean ischecked = false;
    Dialog dialog;
    TableLayout tableLayout;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        String trResult = data.getStringExtra("imgresult");
                        update(trResult);
                    }
                    else {

                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomBinding.inflate(getLayoutInflater());

        realm = Realm.getDefaultInstance();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        binding.rcvroom.setLayoutManager(gridLayoutManager);
        if(realm.where(Room.class).findAll().isEmpty()){
            addData();
        }
        listroom = realm.where(Room.class).findAll();
        adapter = new RoomAdapter(listroom,this,this,this);
        binding.rcvroom.setAdapter(adapter);
        FragmentManager fragmentManager = getSupportFragmentManager();

        listmenu = new ArrayList<>();
        listmenu.add("Tất cả tầng");
        for(int i = 0; i< listroom.size(); i++){
            if(listmenu.contains(listroom.get(i).getFloor()) == false){
                listmenu.add(String.valueOf(listroom.get(i).getFloor()));
            }
        }

        adaptermenu  = new RoommenuAdapter(listmenu,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        binding.rcvroommenu.setLayoutManager(linearLayoutManager);
        binding.rcvroommenu.setAdapter(adaptermenu);


        setContentView(binding.getRoot());

    }

    @Override
    public void Clickchoosepicture(int id) {
        if(id == 5){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galleryActivityResultLauncher.launch(intent);
        }
        else if(id == 6){
            Intent intentlive = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            liveActivityResultLauncher.launch(intentlive);
        }
    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri imageUri = data.getData();
                        Intent i = new Intent(RoomActivity.this,PictureActivity.class);
                        i.putExtra("img",String.valueOf(imageUri));
                        mActivityResultLauncher.launch(i);
                    }
                    else {
                    }
                }
            }
    );
        private ActivityResultLauncher<Intent> liveActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Bundle bundle = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");

                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                        String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),bitmap,"Title",null);
                        Intent i = new Intent(RoomActivity.this,PictureActivity.class);
                        i.putExtra("img",path);
                        mActivityResultLauncher.launch(i);

                     }
                    else {
                    }
                }
            }
    );
    @Override
    public void Showdialogroom(int id) {
        RoomBottomSheet roomBottomSheet = new RoomBottomSheet(this);
        roomBottomSheet.show(getSupportFragmentManager(),"Show sucessfull");
        positonex = id;
    }

    @Override
    public void showPopup(int id) {
        RoomDialog roomDialog = new RoomDialog(this);
//        roomDialog.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        roomDialog.show(getSupportFragmentManager(),"OK");

        positionvd = id;
    }

    @Override
    public void getpositon(String s) {
        Realm realm2 = Realm.getDefaultInstance();
        Room room = realm2.where(Room.class).equalTo("id",positionvd).findFirst();
        realm2.beginTransaction();
        room.setName(s);
        realm2.insertOrUpdate(room);
        realm2.commitTransaction();
        adapter.UpdateRoomAdapter(listroom);
    }

    public void update(String s){
        Realm realm3 = Realm.getDefaultInstance();
        Room room3 = realm3.where(Room.class).equalTo("id",positonex).findFirst();
        realm3.beginTransaction();
        room3.setImg(s);
        realm3.insertOrUpdate(room3);
        realm3.commitTransaction();
        adapter.UpdateRoomAdapter(listroom);
    }
    private void addData(){
        addDataToDatabase("Living Room", "content://media/external/images/media/566229", 45, 26 , "Tầng 23");
        addDataToDatabase("Kitchen", "content://media/external/images/media/566229", 58,27, "Tầng 23");
        addDataToDatabase("Dinning Room", "content://media/external/images/media/566229", 56, 25,"Tầng 23");
        addDataToDatabase("Garage", "content://media/external/images/media/566229", 49, 27,"Tầng 24");
        addDataToDatabase("BackYard", "content://media/external/images/media/566229",55, 25,"Tầng 24");
        addDataToDatabase("Front Yard", "content://media/external/images/media/566229", 59, 22,"Tầng 24");
    }
    private void addDataToDatabase(String name, String img, int humidity, int celsius, String floor){
        Room room = new Room();
        Number id = realm.where(Room.class).max("id");
        Integer nextId;
        if(id == null){
            nextId = 1;
        }
        else {
            nextId = id.intValue() +1;
        }

        room.setId(nextId);
        room.setName(name);
        room.setImg(img);
        room.setCelsius(celsius);
        room.setHumidity(humidity);
        room.setFloor(floor);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(room);
            }
        });
    }

    @Override
    public void Clickchoosemenuroom(List<Room> listfilter) {
        adapter.UpdateRoomAdapter(listfilter);
    }

    @Override
    public void Clickshowbottomsheetcelsius(int id) {
        RoomBottomSheetCelsius roomBottomSheetCelsius = new RoomBottomSheetCelsius(id);
        roomBottomSheetCelsius.show(getSupportFragmentManager(),"showbottomsheetcelsius ");
    }
}