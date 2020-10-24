package com.chinatsp.code.enumeration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhe
 * @date 2020/9/16 13:44
 **/
public enum ScreenShotTypeEnum {
    QNX_DISPLAY("空调屏", "air_condition"),
    ANDROID_DISPLAY("安卓屏", "android_service"),
    HMI_CLUSTER_DISPLAY("单HMI仪表屏", "cluster_hmi"),
    CLUSTER_DISPLAY("仪表屏","hypervisor");

    @Setter
    @Getter
    private String value;
    @Setter
    @Getter
    private String name;

    ScreenShotTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 根据Value查找枚举类型
     *
     * @param value 枚举类型的值
     * @return 枚举类型
     */
    public static ScreenShotTypeEnum fromValue(String value) {
        for (ScreenShotTypeEnum type : values()) {
            if (type.value.trim().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new RuntimeException("not support ScreenShotTypeEnum type[" + value + "]");
    }
}
