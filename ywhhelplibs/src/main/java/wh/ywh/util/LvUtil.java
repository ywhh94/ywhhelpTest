package wh.ywh.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 用于嵌套listView 时解决listview高度显示不全的问题
 * Created by Administrator on 2018-08-10.
 */

public class LvUtil {
    public static void setLVHeight(ListView listView) {
        if (listView != null) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()-1));
            listView.setLayoutParams(params);
        }
    }

    public static void setGVHeight(GridView gridView) {
        if (gridView != null) {
            ListAdapter listAdapter = gridView.getAdapter();
            int numColumns = gridView.getNumColumns();
            if (listAdapter == null) {
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, gridView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = gridView.getLayoutParams();
            params.height = totalHeight + (gridView.getVerticalSpacing() * (listAdapter.getCount()-1));
            gridView.setLayoutParams(params);
        }
    }
}
