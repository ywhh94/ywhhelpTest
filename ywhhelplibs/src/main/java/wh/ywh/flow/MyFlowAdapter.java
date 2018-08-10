package wh.ywh.flow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.leoman.ywhhelplibs.R;

import java.util.List;

/**
 * Created by Administrator on 2018-08-06.
 */

public class MyFlowAdapter extends YFlowAdapter {

    private Context mContext;
    public MyFlowAdapter(Context context,List data) {
        super(data);
        mContext = context;
    }

    @Override
    public View getView(YFlowLayout parent, int position, Object o) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.ac_photo_classify,null);

        return view;
    }
}
