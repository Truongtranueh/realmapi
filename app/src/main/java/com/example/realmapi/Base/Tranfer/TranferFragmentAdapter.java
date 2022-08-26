package com.example.realmapi.Base.Tranfer;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TranferFragmentAdapter extends FragmentStateAdapter{


    public TranferFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new Fragment_BankAccount();
            case 2:
                return new Fragment_Other();
        }
        return new Fragment_DigitalWallet();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
