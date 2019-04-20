package com.wang.jmonkey.test.modules.sys;

import com.wang.jmonkey.JmonkeyApplication;
import com.wang.jmonkey.common.utils.poi.excel.ExportExcelUtil;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import com.wang.jmonkey.modules.sys.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JmonkeyApplication.class)
public class UserTest {

    @Autowired
    private ISysUserService service;

    @Test
    public void exportExcelTest() throws Exception {
        List<SysUser> userList = service.selectAll();
        new ExportExcelUtil("用户信息导出数据", SysUser.class)
                .setDataList(userList).write("D://user.xlsx").dispose();
    }
}
