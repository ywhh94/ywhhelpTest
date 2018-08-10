package ywh.wh.test.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-07.
 */
public class MyListAdapter extends BaseAdapter{
    private List<String> data;
    private Context mContext;
    private LayoutInflater inflater;
    public MyListAdapter(Context context,List<String> data){
        this.data = data;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_one,null);

        return view;
    }
}
