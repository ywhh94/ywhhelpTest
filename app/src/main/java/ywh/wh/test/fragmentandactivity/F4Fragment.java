package ywh.wh.test.fragmentandactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wh.ywh.util.LogUtil;
import ywh.wh.test.R;

public class F4Fragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.e("F4--onAttach");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e("F4--onCreateView");
        View view = inflater.inflate(R.layout.f4,null);
        TextView tv = (TextView)view.findViewById(R.id.tv_f4);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TwoActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("F4--onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.e("F4--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.e("F4--onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.e("F4--onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.e("F4--onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("F4--onDestroy");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        LogUtil.e("F4--onAttachFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.e("F4--onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.e("F4--onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("F4--onResume");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("F4--onViewCreated");
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.e("F4--setUserVisibleHint");
    }

}
