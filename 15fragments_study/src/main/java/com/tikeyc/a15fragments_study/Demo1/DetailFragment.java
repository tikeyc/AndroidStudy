package com.tikeyc.a15fragments_study.Demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by public1 on 2017/1/3.
 */

public class DetailFragment extends Fragment {
    public DetailFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        String detail = getArguments().getString("detail");
        textView.setText(detail);

        return textView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
