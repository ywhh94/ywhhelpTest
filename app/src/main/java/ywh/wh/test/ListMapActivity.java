package ywh.wh.test;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import wh.ywh.base.BaseHelpActivity;
import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-08-17.
 */

public class ListMapActivity extends BaseHelpActivity {

    @Override
    protected int setLayout() {
        return R.layout.ac_test;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        Vector<View> vector = new Vector<>();

        ArrayList<String> arrayList = new ArrayList<String>();

        LinkedList<String> linkedList = new LinkedList();

        linkedList.add("1");

        Set<String> hashSet = new HashSet<>();
        Set<String> treeSet = new TreeSet<>();

        hashSet.add("1");hashSet.add("1");
        Iterator<String> i = hashSet.iterator();

        while (i.hasNext()){
            LogUtil.d("hashSet:"+i.next());
        }
        Map<String,String> map = new HashMap<>();
        map.put(null,null);

        Map<String,String> hashTable = new Hashtable<>();
    }


















}
