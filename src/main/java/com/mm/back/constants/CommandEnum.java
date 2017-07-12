package com.mm.back.constants;

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
    SET_PARAM(6, "SetUp:@##wellNum:@wellNum##currentTime:2017/07/09/20/37/00##surfaceHigh:5778.55##sensorDepth:030.00##linearCoefficient:1.0000##phonenumber1:15324260695##phonenumber2:00010000000##IPAddress:103.044.145.247##portNumber:55511##WakeInterval:0001##UploadTime:21/10##", "");

    public static final List<String> commonds = new ArrayList<>();

    static {

        commonds.add(QUERY_NEW_DATA1.commond);
        commonds.add(SET_PARAM.commond);

    }

    /**
     * 命令分隔符
     */
    public static final String SPILT = "：";
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

    /**
     * 获取客户端配置参数
     *
     * @return
     */
    public static String getClientSettingParam(DeviceSettingDto setting) {
        StringBuilder sb = new StringBuilder();
        sb.append("SetUp:").append(setting.getDeviceNum()).append("##");
        sb.append("wellNum:").append(setting.getWellNum()).append("##");
        String currentTime = DateUtils.getDateformat(new Date(), DateUtils.YMD_HMS_FORMAT_DIAS);
        sb.append("currentTime:").append(currentTime).append("##");
        sb.append("surfaceHigh:").append(setting.getSurfaceHigh().setScale(2)).append("##");
        sb.append("sensorDepth:").append(setting.getSensorDepth().setScale(2)).append("##");
        sb.append("linearCoefficient:").append(setting.getLinearCoefficient().setScale(4)).append("##");
        sb.append("phonenumber1:").append(setting.getPhonenumber1()).append("##");
        sb.append("phonenumber2:").append(setting.getPhonenumber2()).append("##");
        sb.append("IPAddress:103.044.145.247").append(StringUtils.isNotBlank(setting.getIPAddress())?setting.getIPAddress():"47.94.194.137").append("##");
        sb.append("portNumber:").append(10086).append("##");
        sb.append("WakeInterval:").append(setting.getWakeInterval()).append("##");
        sb.append("UploadTime:").append(setting.getUploadTime()).append("##");
        return sb.toString();
    }
}
