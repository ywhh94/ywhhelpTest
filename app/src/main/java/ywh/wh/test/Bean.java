package ywh.wh.test;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-05-10.
 */

public class Bean  implements Serializable{
    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }


    public Bean(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "BeanDataInfo{" +
                "i=" + i +
                '}';
    }
}
