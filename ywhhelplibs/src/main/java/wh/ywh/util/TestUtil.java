package wh.ywh.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-07-23.
 */

public class TestUtil {

    public static List<String> getData() {
        return getData(15);
    }

    public static List<String> getData(int num) {
        List<String> list = new ArrayList<>();
        int forNum = 0;
        if (num > 0) {
            forNum = num;
        } else {
            forNum = 15;
        }
        for (int i = 0; i < forNum; i++) {
            list.add("" + i);
        }
        return list;
    }

}
