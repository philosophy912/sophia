# -------------------------------------------------------------------------------
# Name:        test_module3.py
# Purpose:     The file is automatically generated by tools.
# Author:      CD QA Team
# Created:     2020-10-06 23:33
# -------------------------------------------------------------------------------
import allure
import pytest
from time import sleep
from automotive import *
from src.code.context import Tester

tester = Tester()


########################################################################################################################
#                                                                                                                      #
#                                                    创建Suite                                                          #
#                                                                                                                      #
########################################################################################################################
@pytest.fixture(scope="class", autouse=True)
@allure.suite("创建module3测试套件")
def suite():
    with allure.step("打开设备"):
        tester.battery_test1()
    yield suite
    with allure.step("关闭设备"):
        pass


########################################################################################################################
#                                                                                                                      #
#                                                    创建Function                                                       #
#                                                                                                                      #
########################################################################################################################
@pytest.fixture(scope="function", autouse=True)
def function():
    with allure.step("发送CAN信号"):
        pass
    yield
    with allure.step("恢复CAN信号"):
        tester.battery_test1()


########################################################################################################################
#                                                                                                                      #
#                                                    创建测试用例                                                        #
#                                                                                                                      #
########################################################################################################################
@allure.feature("module")
@pytest.mark.usefixtures("suite")
class TestModule3(object):
