package com.tikeyc.a15fragments_study.Demo1;

import android.content.ContentProviderOperation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tikeyc.a15fragments_study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by public1 on 2017/1/3.
 */

public class TitleListFragment extends ListFragment {

    public TitleListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.e("TAG","TitleListFragment_onActivityCreated");

        super.onActivityCreated(savedInstanceState);

        //设置lieview item选择模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //给listView设置adapter显示列表
        List<String > titles = new ArrayList<String>();
        titles.add("title1");
        titles.add("title2");
        titles.add("title3");
        titles.add("title4");

//        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,titles);
        MyAdapter myAdapter = new MyAdapter();
        setListAdapter(myAdapter);

        //默认选择第0个
        getListView().setItemChecked(0,true);
        showDetailFragment(0);
    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = new TextView(getContext());
            }

            TextView textView = (TextView) view;
            textView.setText("title"+i);

            return view;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        showDetailFragment(position);
    }

    private DetailFragment detailFragment;
    public void showDetailFragment(int positon) {
        if (detailFragment == null) {
            detailFragment = new DetailFragment();
        }
        //携带数据
        Bundle args = new Bundle();
        args.putString("detail","detail_title"+positon);
        detailFragment.setArguments(args);
        //
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.detail_fragment_container,detailFragment);
        fragmentTransaction.commit();
    }


}
