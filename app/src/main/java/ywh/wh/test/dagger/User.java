package ywh.wh.test.dagger;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018-08-16.
 */

public class User {
    String name;
    String age;

    @Inject
    public User(String name) {
        this.name = name;
    }

    @Inject
    public String getName() {
        return name;
    }

    @Inject
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
