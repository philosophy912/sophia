# -------------------------------------------------------------------------------
# Name:        test_vehicledoordisplay.py
# Purpose:     The file is automatically generated by tools.
# Author:      CD QA Team
# Created:     2020-10-14 17:04
# -------------------------------------------------------------------------------
import pytest
from time import sleep
from src.codes.context import *


########################################################################################################################
#                                                                                                                      #
#                                                    创建Suite                                                          #
#                                           s                                                                           #
########################################################################################################################
@pytest.fixture(scope="class", autouse=True)
@allure.suite("创建vehicleDoorDisplay测试套件")
def suite():
    with allure.step("打开CAN盒子"):
        open_device()
        sleep(10)
    yield suite
    with allure.step("关闭CAN盒子"):
        close_device()


########################################################################################################################
#                                                                                                                      #
#                                                    创建Function                                                       #
#                                                                                                                      #
########################################################################################################################
@pytest.fixture(scope="function", autouse=True)
def function():
    with allure.step("无"):
        pass
    yield
    with allure.step("无"):
        pass


########################################################################################################################
#                                                                                                                      #
#                                                    创建测试用例                                                        #
#                                                                                                                      #
########################################################################################################################
@allure.feature("module")
@pytest.mark.usefixtures("suite")
class TestVehicleDoorDisplay(object):

    @pytest.mark.usefixtures("function")
    @allure.title("左前门开")
    def test_fl_doorajarsts_open(self):
        """
        Description:
            左前门开
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.打开左前门：iBCM_FL_DoorAjarSts=0x1
        Expect Result:
            左前门打开
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_fl_doorajarsts_open()
            fl_doorajarsts_open()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_fl_doorajarsts_open())

    @pytest.mark.usefixtures("function")
    @allure.title("左前门关")
    def test_fl_doorajarsts_close(self):
        """
        Description:
            左前门关
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.关闭左前门：iBCM_FL_DoorAjarSts=0x0
        Expect Result:
            左前门关闭
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_fl_doorajarsts_close()
            fl_doorajarsts_close()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_fl_doorajarsts_close())

    @pytest.mark.usefixtures("function")
    @allure.title("右前门开")
    def test_fr_doorajarsts_open(self):
        """
        Description:
            右前门开
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.打开右前门：iBCM_FR_DoorAjarSts=0x1
        Expect Result:
            右前门打开
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_fr_doorajarsts_open()
            fr_doorajarsts_open()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_fr_doorajarsts_open())

    @pytest.mark.usefixtures("function")
    @allure.title("右前门关")
    def test_fr_doorajarsts_close(self):
        """
        Description:
            右前门关
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.关闭右前门：iBCM_FR_DoorAjarSts=0x0
        Expect Result:
            右前门关闭
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_fr_doorajarsts_close()
            fr_doorajarsts_close()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_fr_doorajarsts_close())

    @pytest.mark.usefixtures("function")
    @allure.title("右后门开")
    def test_rr_doorajarsts_open(self):
        """
        Description:
            右后门开
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.打开右后门：iBCM_RR_DoorAjarSts=0x1
        Expect Result:
            右后门打开
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_rr_doorajarsts_open()
            rr_doorajarsts_open()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_rr_doorajarsts_open())

    @pytest.mark.usefixtures("function")
    @allure.title("右后门关")
    def test_rr_doorajarsts_close(self):
        """
        Description:
            右后门关
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.关闭右后门：iBCM_RR_DoorAjarSts=0x0
        Expect Result:
            右后门关闭
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_rr_doorajarsts_close()
            rr_doorajarsts_close()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_rr_doorajarsts_close())

    @pytest.mark.usefixtures("function")
    @allure.title("左后门开")
    def test_rl_doorajarsts_open(self):
        """
        Description:
            左后门开
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.打开左后门：iBCM_RL_DoorAjarSts=0x1
        Expect Result:
            左后门打开
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_rl_doorajarsts_open()
            rl_doorajarsts_open()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_rl_doorajarsts_open())

    @pytest.mark.usefixtures("function")
    @allure.title("左后门关")
    def test_rl_doorajarsts_close(self):
        """
        Description:
            左后门关
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.关闭左后门：iBCM_RL_DoorAjarSts=0x0
        Expect Result:
            左后门关闭
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_rl_doorajarsts_close()
            rl_doorajarsts_close()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_rl_doorajarsts_close())

    @pytest.mark.usefixtures("function")
    @allure.title("尾门开")
    def test_trunksts_open(self):
        """
        Description:
            尾门开
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.打开尾门：
            PBD_RearDoorStatus=0x2
            PBD_RearDoorStsVld=0x0
        Expect Result:
            尾门打开
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_pbdsts_open()
            trunksts_open()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_trunksts_open())

    @pytest.mark.usefixtures("function")
    @allure.title("尾门关")
    def test_trunksts_close(self):
        """
        Description:
            尾门关
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.关闭尾门：
            PBD_RearDoorStatus=0x0
            PBD_RearDoorStsVld=0x0
        Expect Result:
            尾门关闭
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_pbdsts_close()
            trunksts_close()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_trunksts_close())

    @pytest.mark.usefixtures("function")
    @allure.title("引擎盖开")
    def test_hoodsts_open(self):
        """
        Description:
            引擎盖开
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.打开引擎盖：iBCM_HoodSts=0x1
        Expect Result:
            引擎盖打开
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_hoodsts_open()
            hoodsts_open()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_hoodsts_open())

    @pytest.mark.usefixtures("function")
    @allure.title("引擎盖关")
    def test_hoodsts_close(self):
        """
        Description:
            引擎盖关
        PreCondition:
            1.电源状态：OFF
            2.所有指示灯均熄灭
        Steps:
            1.关闭引擎盖：iBCM_HoodSts=0x0
        Expect Result:
            引擎盖关闭
        """
        # 前置条件
        with allure.step("前置条件"):
            power_off()
        # 执行步骤
        with allure.step('操作步骤'):
            ibcm_hoodsts_close()
            hoodsts_close()
        # 期望结果
        with allure.step('期望结果'):
            compare(compare_hoodsts_close())
