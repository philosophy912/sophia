package com.chinatsp.code.checker.impl.storage;

import com.chinatsp.code.checker.api.BaseChecker;
import com.chinatsp.code.checker.api.IChecker;
import com.chinatsp.code.configure.Configure;
import com.chinatsp.code.entity.BaseEntity;
import com.chinatsp.code.entity.collection.Element;
import com.chinatsp.code.entity.storage.Information;
import com.chinatsp.dbc.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class InformationChecker extends BaseChecker implements IChecker {


    @Override
    public void check(Map<String, List<BaseEntity>> map, List<Message> messages, Configure configure) {
        List<BaseEntity> entities = getEntity(map, Information.class);
        List<BaseEntity> elements = getEntity(map, Element.class);
        for (int i = 0; i < entities.size(); i++) {
            int index = i + 1;
            Information information = (Information) entities.get(i);
            String name = information.getClass().getName();
            // 检查名字是否符合python命名规范
            checkUtils.checkPythonFunction(information.getName(), index, name);
            // 检查element名字是否存在于Sheet(Element)中
            checkUtils.checkElementExist(information.getElement(), index, name, elements);
        }
        // 检查函数名是否有重名
        checkUtils.findDuplicate(entities, Information.class.getSimpleName());
    }
}
