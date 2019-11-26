package com.wang.jmonkey;

import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

public class JmonkeyApplicationTests {

    @Test
    public void contextLoads() {
        List<String> areaList = CollectionUtil.newArrayList();

        String p = CollectionUtil.isNotEmpty(areaList) && areaList.size() > 0 ? areaList.get(0) : StringUtils.EMPTY;

        System.out.println(p);

    }

}
