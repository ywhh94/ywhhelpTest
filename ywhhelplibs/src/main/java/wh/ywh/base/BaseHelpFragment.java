package wh.ywh.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-07-05.
 */

public abstract class BaseHelpFragment extends Fragment {

    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayout() == 0) {
            new NullPointerException("请设置资源id");
        }
        view = inflater.inflate(setLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int setLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(view);
    }
}
