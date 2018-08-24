package ywh.wh.test.dagger;

import android.content.Context;

import dagger.Component;

/**
 * Created by Administrator on 2018-08-16.
 */

@Component(modules = DataModule.class)
public interface PersonComponent {
    void inject(Context context);
}
