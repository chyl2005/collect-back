package com.mm.back.constants;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.mm.back.dto.DeviceSettingDto;
import com.mm.common.utils.DateUtils;

/**
 * Author:chyl2005
 * Date:17/4/22
 * Time:10:35
 * Desc:描述该类的作用
 */
public enum CommandEnum {

    QUERY_NEW_DATA1(100, "查询数据：", ""),
    QUERY_DATA(102, "JSON查询数据：@num", ""),
    QUERY_PARAM(104, "JSON查询全部参数", ""),
    QUERY_STORE_INFO(103, "JSON查询储存信息", ""),
    HISTORY(5, "导出历史数据", ""),
    STOP(6, "立即进入停止模式", ""),
    SETPARAM(7, "设置参数", "");

    public static final List<String> commonds = new ArrayList<>();

    static {
        commonds.add(QUERY_NEW_DATA1.commond);
    }

    /**
     * 指令编号
     */
    private Integer code;
    private String commond;

    private String success;

    CommandEnum(Integer code, String commond, String success) {
        this.code = code;
        this.commond = commond;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public String getCommond() {
        return commond;
    }

    public String getSuccess() {
        return success;
    }

    public static String getCommond(int code) {
        for (CommandEnum statusEnum : values()) {
            if (code == statusEnum.code) {
                return statusEnum.commond;
            }
        }
        return null;
    }

    /**
     * 获取客户端配置参数
     *
     * @return
     */
    public static String getClientSettingParam(DeviceSettingDto setting) {
        StringBuilder sb = new StringBuilder();
        sb.append("SetUp:").append(setting.getDeviceNum()).append("##");
        sb.append("wellNum:").append(setting.getWellNum() != null ? setting.getWellNum() : 0000).append("##");
        String currentTime = DateUtils.getDateformat(new Date(), DateUtils.YMD_HMS_FORMAT_DIAS);
        sb.append("currentTime:").append(currentTime).append("##");
        BigDecimal surfaceHigh = setting.getSurfaceHigh() != null ? setting.getSurfaceHigh() : BigDecimal.ZERO;
        String surface = new DecimalFormat("0000.00").format(surfaceHigh.doubleValue());
        sb.append("surfaceHigh:").append(surface).append("##");
        BigDecimal depth = setting.getSensorDepth() != null ? setting.getSensorDepth() : BigDecimal.ZERO;
        String sensorDepth = new DecimalFormat("000.00").format(depth.doubleValue());
        sb.append("sensorDepth:").append(sensorDepth).append("##");
        BigDecimal linearCoefficient = setting.getLinearCoefficient() != null ? setting.getLinearCoefficient() : BigDecimal.ZERO;
        sb.append("linearCoefficient:").append(linearCoefficient.setScale(4)).append("##");
        String phonenumber1 = StringUtils.isNotBlank(setting.getPhonenumber1()) ? setting.getPhonenumber1() : ClientConst.DEFAULT_PHONE_NUM;
        String phonenumber2 = StringUtils.isNotBlank(setting.getPhonenumber2()) ? setting.getPhonenumber2() : ClientConst.DEFAULT_PHONE_NUM;
        sb.append("phonenumber1:").append(phonenumber1).append("##");
        sb.append("phonenumber2:").append(phonenumber2).append("##");
        sb.append("IPAddress:").append(ClientConst.IP).append("##");
        sb.append("portNumber:").append(ClientConst.PORT).append("##");
        String WakeInterval = StringUtils.isNotBlank(setting.getWakeInterval()) ? setting.getWakeInterval() : ClientConst.DEFAULT_WAKE_INTERVEL;
        String UploadTime = StringUtils.isNotBlank(setting.getUploadTime()) ? setting.getUploadTime() : ClientConst.DEFAULT_UPLOAD_TIME;
        sb.append("WakeInterval:").append(WakeInterval).append("##");
        sb.append("UploadTime:").append(UploadTime).append("##");
        return sb.toString();
    }

    private static String formatIP(String ip) {
        String format = ip.replaceAll("(\\d{1,3})", "00$1");
        format = format.replaceAll("0*(\\d{3})", "$1");

        return format;
    }
}
