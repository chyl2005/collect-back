var settingUrl = rootPath + "deviceInfo/setting/setting";

var uploadSettingUrl = rootPath + "deviceInfo/setting/uploadSetting";

var saveOrUpdateUrl = rootPath + "deviceInfo/setting/saveOrUpdate";
$(document).ready(function () {


    getSetting();
    getUploadSetting();

});


function getSetting() {
    var deviceId = $(".setting").find("input[name='deviceId']").val();
    $.ajax({
        type: "get",
        url: settingUrl,
        dataType: 'json',
        data: {deviceId: deviceId},
        async: false,
        success: function (data) {
            if (data.status === 0) {
                var item = data.data;
                $(".setting").find("input[name='deviceId']").val(item.deviceId);
                $(".setting").find("input[name='serverIp']").val(item.serverIp);
                $(".setting").find("input[name='serverPort']").val(item.serverPort);
                $(".setting").find("input[name='serialNum']").val(item.serialNum);
                $(".setting").find("input[name='deviceNum']").val(item.deviceNum);
                $(".setting").find("input[name='phoneNum1']").val(item.phoneNum1);
                $(".setting").find("input[name='phoneNum2']").val(item.phoneNum2);
                $(".setting").find("input[name='sensorDepth']").val(item.sensorDepth);
                $(".setting").find("input[name='surfaceHigh']").val(item.surfaceHigh);
                $(".setting").find("input[name='wakeupTime1']").val(item.wakeupTime1);
                $(".setting").find("input[name='wakeupTime2']").val(item.wakeupTime2);
                $(".setting").find("input[name='gmtModified']").val(getSmpFormatDateByLong(item.gmtModified, true));
            } else {
                alert(data.message);
            }
        }
    });
}


function getUploadSetting() {
    var deviceId = $(".setting").find("input[name='deviceId']").val();
    $.ajax({
        type: "get",
        url: uploadSettingUrl,
        dataType: 'json',
        data: {deviceId: deviceId},
        async: false,
        success: function (data) {
            if (data.status === 0) {
                var item = data.data;
                $(".uploadSetting").find("input[name='deviceId']").val(item.deviceId);
                $(".uploadSetting").find("input[name='serverIp']").val(item.serverIp);
                $(".uploadSetting").find("input[name='serverPort']").val(item.serverPort);
                $(".uploadSetting").find("input[name='serialNum']").val(item.serialNum);
                $(".uploadSetting").find("input[name='deviceNum']").val(item.deviceNum);
                $(".uploadSetting").find("input[name='phoneNum1']").val(item.phoneNum1);
                $(".uploadSetting").find("input[name='phoneNum2']").val(item.phoneNum2);
                $(".uploadSetting").find("input[name='sensorDepth']").val(item.sensorDepth);
                $(".uploadSetting").find("input[name='surfaceHigh']").val(item.surfaceHigh);
                $(".uploadSetting").find("input[name='wakeupTime1']").val(item.wakeupTime1);
                $(".uploadSetting").find("input[name='wakeupTime2']").val(item.wakeupTime2);
                $(".uploadSetting").find("input[name='gmtModified']").val(getSmpFormatDateByLong(item.gmtModified, true));

            } else {
                alert(data.message);
            }
        }
    });

}


function saveOrUpdate() {
    var dialog = $("#saveOrUpdateDialog");
    // 弹窗数据清空
    var postData = {};
    postData.deviceId = $(".setting").find("input[name='deviceId']").val();
    postData.serverIp = $(".setting").find("input[name='serverIp']").val();
    postData.serverPort = $(".setting").find("input[name='serverPort']").val();
    postData.serialNum = $(".setting").find("input[name='serialNum']").val();
    postData.phoneNum1 = $(".setting").find("input[name='phoneNum1']").val();
    postData.phoneNum2 = $(".setting").find("input[name='phoneNum2']").val();
    postData.sensorDepth = $(".setting").find("input[name='sensorDepth']").val();
    postData.surfaceHigh = $(".setting").find("input[name='surfaceHigh']").val();
    postData.wakeupTime1 = $(".setting").find("input[name='wakeupTime1']").val();
    postData.wakeupTime2 = $(".setting").find("input[name='wakeupTime2']").val();

    $.ajax({
        type: "post",
        url: saveOrUpdateUrl,
        dataType: 'json',
        data: postData,
        async: false,
        success: function (data) {
            if (data.status === 0) {
                getSetting();
            } else {
                alert(data.message);
            }
        }
    });

}
