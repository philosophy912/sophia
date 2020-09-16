package com.chinatsp.code.checker.impl.testcase;

import com.chinatsp.code.checker.api.BaseChecker;
import com.chinatsp.code.checker.api.IChecker;
import com.chinatsp.code.configure.Configure;
import com.chinatsp.code.entity.BaseEntity;
import com.chinatsp.code.entity.storage.Information;
import com.chinatsp.code.entity.testcase.TestCase;
import com.chinatsp.code.entity.testcase.TestCaseSetUp;
import com.chinatsp.dbc.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lizhe
 * @date 2020/9/16 10:51
 **/
@Component
public class TestCaseSetUpChecker extends BaseChecker implements IChecker {

    @Override
    public void check(Map<String, List<BaseEntity>> map, List<Message> messages, Configure configure) {
        List<BaseEntity> entities = getEntity(map, TestCaseSetUp.class);
        List<BaseEntity> testCaseEntities = getEntity(map, TestCase.class);
        for (int i = 0; i < entities.size(); i++) {
            int index = i + 1;
            TestCaseSetUp testCaseSetUp = (TestCaseSetUp) entities.get(i);
            String name = testCaseSetUp.getClass().getName();
            // 检查名字是否符合python命名规范
           checkUtils.checkModule(testCaseSetUp.getName(), index, name, testCaseEntities);
            // 检查element名字是否存在于Sheet(Element)中
        }
    }
}