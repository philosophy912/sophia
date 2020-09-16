# -*- coding:utf-8 -*-
# --------------------------------------------------------
# Copyright (C), 2016-2020, China TSP, All rights reserved
# --------------------------------------------------------
# @Name:        configure.py.py
# @Purpose:     todo
# @Author:      lizhe
# @Created:     2020/9/15 - 15:53
# --------------------------------------------------------

# 最大支持的显示屏的个数
max_display = 5
# 最大支持的电压值
max_voltage = 20
# 最小支持的电压值
min_voltage = 0
# SOC串口号
soc_port = "COM23"
# SOC串口波特率
soc_baud_rate = 115200
# MCU串口号
mcu_port = "COM24"
# 显示屏宽
max_width = 1920
# 显示屏高
max_height = 720
# 临时图片存放路径
screen_shot_temp_path = r"D:\Workspace\screenshot"
# 模板图片存放路径
template_image_path = r"D:\Workspace\template"
# CAN的DBC文件绝对路径
dbc_file = r"D:\Workspace\github\code\file\HiFire_B31CP_Info_HU_CAN_V2.0.dbc"
# 最大支持的继电器通道
max_relay_channel = 8