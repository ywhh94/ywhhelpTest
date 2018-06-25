package com.leoman.helper.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class FragmentController {

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    private int resource;
    private String[] fragmentTags;
    private Activity activity = null;

    public FragmentController(FragmentManager fragmentManager, int resource, String[] fragmentTags, Activity activity) {
        this.resource = resource;
        this.fragmentManager = fragmentManager;
        this.fragmentTags = fragmentTags;
        this.activity = activity;
    }

    public void add(Class<? extends Fragment> clazz, String tag, Bundle bundle) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (String tagName : fragmentTags) {
            Fragment fragment = fragmentManager.findFragmentByTag(tagName);
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        // transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        if (fragment != null) {
            transaction.show(fragment);
        } else {
            try {
                fragment = clazz.newInstance();
                transaction.add(resource, fragment, tag);
                if (bundle != null) {
                    fragment.setArguments(bundle);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // transaction.commit();
        if (activity != null)
            transaction.commitAllowingStateLoss();
    }

}
