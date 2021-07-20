package com.zpl.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpeilin
 * @date 2021/5/27
 */
public class PlatformUtil {

    /**
     * 统一 platform 字符串，如果将来要修改，只改这里即可
     */
    public static final String PLATFORM = "platform";
    private final Map<String, String> platformMap = new HashMap<>();

    public PlatformUtil() {
    }

    public PlatformUtil(PlatformUtil platformUtil) {
        this.platformMap.putAll(platformUtil.getPlatformsMap());
    }

    public PlatformUtil(PlatformEnum... platformEnum) {
        include(platformEnum);
    }

    /**
     * 增加要判断的平台来源
     *
     * @param platformEnums 平台来源，可以单个可以多个
     * @return this
     */
    public PlatformUtil include(PlatformEnum... platformEnums) {
        Arrays.stream(platformEnums).forEach(platformEnum -> this.platformMap.put(platformEnum.value, platformEnum.describe));
        return this;
    }

    /**
     * 一次性增加所有的平台来源，方便直接校验平台来源的合法性
     *
     * @return this
     */
    public PlatformUtil includeAll() {
        this.platformMap.clear();
        include(PlatformEnum.values());
        return this;
    }

    /**
     * 排除平台来源
     *
     * @param platformEnum 平台来源
     * @return this
     */
    public PlatformUtil exclude(PlatformEnum platformEnum) {
        platformMap.remove(platformEnum.value);
        return this;
    }

    /**
     * 校验 platform 是否符合要求
     *
     * @param platformString 平台来源 字符串
     * @return boolean
     */
    public boolean validate(String platformString) {
        return platformMap.containsKey(platformString);
    }

    /**
     * 输出当前的构造的 platform map
     */
    public String printPlatforms() {
        return platformMap.toString();
    }

    /**
     * 返回一个当前的 platform map的拷贝
     *
     * @return 当前的 platform map的拷贝
     */
    public HashMap<String, String> getPlatformsMap() {
        return new HashMap<>(platformMap);
    }

    /**
     * 返回枚举值的 value
     * @param platformEnum
     * @return
     */
    public static String getPlatformEnumValue(PlatformEnum platformEnum){
        return platformEnum.value;
    }

    /**
     * 返回枚举值的描述
     * @param platformEnum
     * @return
     */
    public static String getPlatformEnumDesc(PlatformEnum platformEnum){
        return platformEnum.describe;
    }

    /**
     * 提供常用的平台来源实例
     */
    public static class Instance{
        /**
         * 寿险在线 1
         */
        public static final PlatformUtil SHOU_XIAN_ZAI_XIAN = new PlatformUtil(PlatformEnum.SHOU_XIAN_ZAI_XIAN);
        /**
         * 小程序 2
         */
        public static final PlatformUtil E_FU_WU = new PlatformUtil(PlatformEnum.E_FU_WU);
        /**
         * E服务 3
         */
        public static final PlatformUtil XIAO_CHENG_XU = new PlatformUtil(PlatformEnum.XIAO_CHENG_XU);
        /**
         * 集团 APP 7
         */
        public static final PlatformUtil JI_TUAN_APP = new PlatformUtil(PlatformEnum.JI_TUAN_APP);
        /**
         * 管家 8
         */
        public static final PlatformUtil GUAN_JIA_APP = new PlatformUtil(PlatformEnum.SHOU_XIAN_GUAN_JIA_APP);

        /**
         * 支付宝 b
         */
        public static final PlatformUtil ZHI_FU_BAO = new PlatformUtil(PlatformEnum.ZHI_FU_BAO);

        /**
         * E服务支付宝 i
         */
        public static final PlatformUtil E_FU_WU_ZHI_FU_BAO = new PlatformUtil(PlatformEnum.E_FU_WU_ZHI_FU_BAO);
        /**
         * 重庆自助机 30
         */
        public static final PlatformUtil CHONG_QING_ZI_ZHU_JI = new PlatformUtil(PlatformEnum.CHONG_QING_ZI_ZHU_JI);
    }

    /**
     * 平台来源枚举
     */
    public enum PlatformEnum {
        /**
         * 人保寿险在线 1
         */
        SHOU_XIAN_ZAI_XIAN("1", "人保寿险在线"),
        /**
         * 中国人民人寿保险小程序 2
         */
        XIAO_CHENG_XU("2", "中国人民人寿保险小程序"),
        /**
         * 人民人寿e服务 3
         */
        E_FU_WU("3", "人民人寿e服务"),
        /**
         * 电商官网 4
         */
        DIAN_SHANG_GUAN_WANG("4", "电商官网"),
        /**
         * 掌中宝 5
         */
        ZHANG_ZHONG_BAO("5", "掌中宝"),
        /**
         * 保护通 6
         */
        BAO_HU_TONG("6", "保护通"),
        /**
         * 集团app 7
         */
        JI_TUAN_APP("7", "集团app"),
        /**
         * 人保寿险管家/寿险App 8
         */
        SHOU_XIAN_GUAN_JIA_APP("8", "人保寿险管家/寿险App"),
        /**
         * 移动平台 9
         */
        YI_DONG_PING_TAI("9", "移动平台"),
        /**
         * 种菜小程序 a
         */
        ZHONG_CAI_XIAO_CHENG_XU("a", "种菜小程序"),
        /**
         * 支付宝 b
         */
        ZHI_FU_BAO("b", "支付宝"),
        /**
         * 电话中心多媒体 c
         */
        DIAN_HUA_DUO_MEI_TI("c", "电话中心多媒体"),
        /**
         * 人民保险大师 d
         */
        BAO_XIAN_DA_SHI("d", "人民保险大师"),
        /**
         * 其他（通用） e
         */
        QI_TA_TONG_YONG("e", "其他（通用）"),
        /**
         * wap-移动电商门户 f
         */
        WAP_YI_DONG_DIAN_SHANG("f", "wap-移动电商门户"),
        /**
         * pc-综合电商门户 g
         */
        PC_ZONG_HE_DIAN_SHANG("g", "pc-综合电商门户"),
        /**
         * 东软 h
         */
        DONG_RUAN("h", "东软"),
        /**
         * E服务支付宝 i
         */
        E_FU_WU_ZHI_FU_BAO("i", "E服务支付宝"),
        /**
         * 分红通知书短信下载 j
         */
        FEN_HONG_TONG_ZHI_SHU("j", "分红通知书短信下载"),
        /**
         * 银保满期引流 k
         */
        YIN_BAO_MAN_QI_YIN_LIU("k","银保满期引流"),
        /**
         * 电商接口平台来源传值错误 z
         */
        DIAN_SHANG_CUO_WU("z", "电商接口平台来源传值错误"),
        /**
         * 重庆自助机
         */
        CHONG_QING_ZI_ZHU_JI("30","重庆自助机"),
        /**
         * 全部 99
         */
        QUAN_BU("99", "全部");
        /**
         * 平台来源 platform 值
         */
        private String value;
        /**
         * 平台来源 platform 描述
         */
        private String describe;

        PlatformEnum(String value, String describe) {
            this.value = value;
            this.describe = describe;
        }
    }
}
