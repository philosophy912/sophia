package com.chinatsp.code.writer.impl.testcase;

import com.chinatsp.code.entity.BaseEntity;
import com.chinatsp.code.entity.testcase.TestCase;
import com.chinatsp.code.entity.testcase.TestCaseSetUp;
import com.chinatsp.code.enumeration.TestCaseFunctionTypeEnum;
import com.chinatsp.code.writer.api.TestCaseFreeMarker;
import com.philosophy.base.common.Pair;
import com.philosophy.base.common.Triple;
import com.philosophy.character.util.CharUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class TestCaseWriter {
    /**
     * 获取所有的module的名字
     *
     * @param testCases 测试用例表格Sheet
     * @return 所有的module名字
     */
    private Set<String> getModuleNames(List<BaseEntity> testCases) {
        Set<String> strings = new HashSet<>();
        for (BaseEntity entity : testCases) {
            TestCase testCase = (TestCase) entity;
            strings.add(testCase.getModuleName());
        }
        return strings;
    }

    /**
     * 把TestCase转换成Map
     *
     * @param modules   模块名
     * @param testCases 一行Excel的数据
     * @return 字典对象
     */
    private Map<String, List<TestCase>> convertTestCase(Set<String> modules, List<BaseEntity> testCases) {
        Map<String, List<TestCase>> map = new HashMap<>();
        for (String module : modules) {
            List<TestCase> testCaseList = new ArrayList<>();
            for (BaseEntity entity : testCases) {
                TestCase testCase = (TestCase) entity;
                if (testCase.getName().equalsIgnoreCase(module)) {
                    testCaseList.add(testCase);
                }
            }
            map.put(module, testCaseList);
        }
        return map;
    }

    /**
     * 把TestCaseSetUp转换成Map
     *
     * @param modules        模块名
     * @param testCaseSetUps 一行Excel的数据
     * @return 字典对象
     */
    private Map<String, TestCaseSetUp> convertSetup(Set<String> modules, List<BaseEntity> testCaseSetUps) {
        Map<String, TestCaseSetUp> map = new HashMap<>();
        for (String module : modules) {
            for (BaseEntity entity : testCaseSetUps) {
                TestCaseSetUp testCaseSetUp = (TestCaseSetUp) entity;
                if (testCaseSetUp.getName().equalsIgnoreCase(module)) {
                    map.put(module, testCaseSetUp);
                }
            }
        }
        return map;
    }

    /**
     * 处理每行数据
     *
     * @param type         类型
     * @param functionName 函数名
     * @param collection   要添加到的集合
     */
    private void addCollection(TestCaseFunctionTypeEnum type, String functionName, List<String> collection) {
        if (functionName == null) {
            collection.add(type.getValue());
        } else {
            if (type == TestCaseFunctionTypeEnum.SLEEP) {
                collection.add(type.getValue() + "(" + functionName + ")");
            } else {
                collection.add(functionName + "()");
            }

        }
    }

    /**
     * 根据YEILD分离成两个部分 如：
     * BatteryAction=battery_test1
     * yeild
     * pass
     *
     * @param pairs 一个Cell的值
     * @return 前半部分和后半部分
     */
    private Pair<List<String>, List<String>> splitLines(List<Pair<TestCaseFunctionTypeEnum, String>> pairs) {
        List<String> part1 = new LinkedList<>();
        List<String> part2 = new LinkedList<>();
        boolean flag = true;
        for (Pair<TestCaseFunctionTypeEnum, String> pair : pairs) {
            TestCaseFunctionTypeEnum type = pair.getFirst();
            String functionName = pair.getSecond();
            if (type == TestCaseFunctionTypeEnum.YEILD) {
                flag = false;
                continue;
            }
            if (flag) {
                // 加入到part1中
                addCollection(type, functionName, part1);
            } else {
                // 加入到part2中
                addCollection(type, functionName, part2);
            }
        }
        return new Pair<>(part1, part2);
    }

    /**
     * 把一个Cell单元的内容解析成函数
     *
     * @param cells 一个单元的内容
     * @return 函数名列表
     */
    private List<String> parseCell(List<Pair<TestCaseFunctionTypeEnum, String>> cells) {
        List<String> strings = new LinkedList<>();
        for (Pair<TestCaseFunctionTypeEnum, String> pair : cells) {
            TestCaseFunctionTypeEnum type = pair.getFirst();
            String functionName = pair.getSecond();
            if (functionName == null) {
                strings.add(type.getValue());
            } else {
                strings.add(functionName);
            }
        }
        return strings;
    }

    /**
     * 把测试用例集转换成每个模块拥有的测试用例字典
     *
     * @param map 表格字典集合
     * @return 字典
     */
    public Map<String, TestCaseFreeMarker> convert(Map<String, List<BaseEntity>> map) {
        Map<String, TestCaseFreeMarker> resultMap = new HashMap<>();
        List<BaseEntity> testCases = map.get(CharUtils.lowerCase(TestCase.class.getSimpleName()));
        List<BaseEntity> testCaseSetUps = map.get(CharUtils.lowerCase(TestCaseSetUp.class.getSimpleName()));
        Set<String> modules = getModuleNames(testCases);
        Map<String, TestCaseSetUp> moduleSetUpMap = convertSetup(modules, testCaseSetUps);
        Map<String, List<TestCase>> moduleTestCaseMap = convertTestCase(modules, testCases);
        for (String module : modules) {
            TestCaseFreeMarker freeMarker = new TestCaseFreeMarker();
            freeMarker.setModuleName(module);
            TestCaseSetUp testCaseSetUp = moduleSetUpMap.get(module);
            // 处理TestCaseSetUp中的setup
            List<Pair<TestCaseFunctionTypeEnum, String>> suites = testCaseSetUp.getSuites();
            List<Pair<TestCaseFunctionTypeEnum, String>> functions = testCaseSetUp.getFunctions();
            Pair<List<String>, List<String>> suitePair = splitLines(suites);
            Pair<List<String>, List<String>> functionPair = splitLines(functions);
            freeMarker.setSuite(new Pair<>(suitePair, new Pair<>(testCaseSetUp.getSuitesBefore(), testCaseSetUp.getSuitesAfter())));
            freeMarker.setFunction(new Pair<>(functionPair, new Pair<>(testCaseSetUp.getFunctionsBefore(), testCaseSetUp.getFunctionsAfter())));
            // 处理TestCase的部分
            List<Pair<Triple<List<String>, List<String>, List<String>>, Pair<String, List<String>>>> pairs = new ArrayList<>();
            List<TestCase> testCaseList = moduleTestCaseMap.get(module);
            for (TestCase testCase : testCaseList) {
                List<String> preCondition = parseCell(testCase.getPreCondition());
                List<String> steps = parseCell(testCase.getSteps());
                List<String> expect = parseCell(testCase.getExpect());
                List<String> comments = testCase.getComments();
                pairs.add(new Pair<>(new Triple<>(preCondition, steps, expect), new Pair<>(testCase.getName(), comments)));
            }
            freeMarker.setTestcases(pairs);
            resultMap.put(module, freeMarker);
        }
        return resultMap;

    }
}