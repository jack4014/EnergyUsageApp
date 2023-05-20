package com.example.energyusageapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.energyusageapp.*;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentBase extends Fragment {
    public FragmentBase(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }
    public FragmentBase() {
        super();
    }
    public static Action<FragmentBase> OnChange;
    public Drawable icon;

    public static FragmentBase last;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (OnChange != null)
            OnChange.invoke(this);
        last = this;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (OnChange != null)
            OnChange.invoke(this);
        last = this;
    }
}
