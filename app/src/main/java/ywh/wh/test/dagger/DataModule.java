package ywh.wh.test.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018-08-16.
 */

@Module
public class DataModule {
    @Provides
    Person getPerson(){
        return new Person();
    }
}
