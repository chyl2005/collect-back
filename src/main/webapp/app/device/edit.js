


var configInfoUrl = rootPath + "deviceConfig/info";

var saveOrUpdateUrl = rootPath + "deviceConfig/saveOrUpdate";
$(document).ready(function () {



});




function saveOrUpdate() {
    var dialog = $("#saveOrUpdateDialog");
    // 弹窗数据清空
    var postData = {};
    postData.id = dialog.find("input[type=hidden]").val();
    postData.parentId = dialog.find("#parentModule").val();
    postData.name = dialog.find("input[name='name']").val();
    postData.url = dialog.find("input[name='url']").val();
    postData.icon = dialog.find("input[name='icon']").val();
    var isLink = dialog.find("input[type=checkbox]").prop('checked');
    postData.isLink = isLink ? 1 : 0;
    $.ajax({
        type: "post",
        url: saveOrUpdateUrl,
        dataType: 'json',
        data: postData,
        async: false,
        success: function (data) {
            if (data != null) {
                // 弹窗数据清空
                dialog.find(":input").val("");
                dialog.modal('hide');
                loadData(pageCur, pageSize, dataUrl);
            }
        }
    });

}
