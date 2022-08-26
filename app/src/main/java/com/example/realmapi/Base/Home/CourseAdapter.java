package com.example.realmapi.Base.Home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.realmapi.Interface.CLickDelete;
import com.example.realmapi.Interface.ClickUpdate;
import com.example.realmapi.Interface.OnLongClick;
import com.example.realmapi.Model.DataModel;
import com.example.realmapi.R;
import com.example.realmapi.ViewModel.ViewModel;
import com.example.realmapi.databinding.CourseLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

public class CourseAdapter extends RecyclerView.Adapter <CourseAdapter.ViewHolder> implements CLickDelete{
    Realm realm;
    CLickDelete mclickdelete;
    OnLongClick monlongclick;
    List<DataModel> dataModalList;
    ViewModel viewModel;

    public void UpdateCourseAdapter(List<DataModel> dataModalList ) {
        this.dataModalList = dataModalList;
        notifyDataSetChanged();
    }

    public CourseAdapter(List<DataModel> dataModalList, CLickDelete clickdelete, ClickUpdate clickupdate,OnLongClick onlongclick ) {
        this.dataModalList = dataModalList;
        this.mclickdelete = clickdelete;
        this.monlongclick = onlongclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        viewModel = new ViewModel();
        DataModel dataModal = dataModalList.get(position);
        holder.binding.title.setText(dataModal.getTitle());
        holder.binding.body.setText(dataModal.getBody());
        Picasso.get().load(dataModal.getImage()).into(holder.binding.img);
        holder.binding.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm = Realm.getDefaultInstance();
                DataModel modal = realm.where(DataModel.class).equalTo("id",dataModal.getId()).findFirst();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                    dialog.setTitle("Delete");
                    dialog.setMessage("Are you sure delete --- " + dataModal.getTitle());
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    modal.deleteFromRealm();
                                    mclickdelete.onItemClick(dataModalList);
                                }
                            });
                        }
                    });
                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.create().show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                monlongclick.itemLongClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.dataModalList != null) {
            return this.dataModalList.size();
        }
        return 0;
    }
    public void filterlist(List<DataModel> filteredlist){
        dataModalList = filteredlist;
        notifyDataSetChanged();}

    @Override
    public void onItemClick(List<DataModel> dataModelList) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CourseLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CourseLayoutBinding.bind(itemView);
        }
    }
}
