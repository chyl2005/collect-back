/**
 * 收款日结算订单
 */
var pageCur = 1;
var pageSize = 10;
// 列表数据请求url
var dataUrl = rootPath + "deviceInfo/list";
var deleteUrl = rootPath + "deviceInfo/del";

var saveOrUpdateUrl = rootPath + "deviceInfo/saveOrUpdate";
$(document).ready(function () {



    // 加载数据
    loadData(pageCur, pageSize, dataUrl);
    $(".searchData").on('click', function () {
        pageCur = 1;
        loadData(pageCur, pageSize, dataUrl);
    });

});



// 获取请求服务器数据
function getServerParams() {
    var postData = {};
    var moduleId = parseInt($("#first_module").val());
    // 查询全部传null
    if (moduleId != 0) {
        postData.parentId = moduleId;
    }
    return postData;
}

// 数据加载
function loadData(currentPage, pageSize, url) {
    var postData = getServerParams();
    postData.startRow = (currentPage - 1) * pageSize;
    postData.pageSize = pageSize;
    $.ajax({
        type: "post",
        url: url,
        dataType: 'json',
        data: postData,
        async: false,
        success: dataCallbackShow
    })
}
// 数据填充
function dataCallbackShow(data) {
    // 表单清空
    $("#lists").empty();
    $("#page").empty();

    // 数据空判断
    if (data.data == null || data.data.datas == null || data.data.datas.length == 0) {
        $("#lists").html('<tr ><td valign="top" colspan="9"  class="center">无符合条件数据！</td></tr>');
        return;
    }

    // 总数
    var total = data.data.iTotalDisplayRecords;
    var items = data.data.datas;
    var currentPage = pageCur;
    var rowTemplate = $("#template").children();
    items.forEach((item) => {
        // 克隆tr模板
        var row = rowTemplate.clone();
        row.find("#deviceId").text(item.deviceId);
        row.find("#serialNum").text(item.serialNum);
        row.find("#deviceNum").text(item.deviceNum);
        row.find("#cityName").text(item.cityName);
        row.find("#provinceName").text(item.provinceName);
        row.find("#gmtCreated").text(getSmpFormatDateByLong(item.gmtCreated, true));
        row.find("#gmtModified").text(getSmpFormatDateByLong(item.gmtModified, true));
        row.find("#isDel").prop('checked', item.isDel == 1 ? true : false);
        row.appendTo("#lists");
    });

    // 绑定分页方法
    paginator("#page", currentPage, pageSize, total, dataUrl);

    //编辑
    $(".btn-info").on('click', function () {
        showDialog(this);
    });

    //删除
    $(".btn-danger").on('click', function () {
        var postData = {};
        postData.id = $(this).closest("tr").find("#id").text();
        $.ajax({
            url: deleteUrl,
            data: postData,
            type: 'post',
            dataType: 'json',
            async: true,
            success: function (data) {
                if (null != data) {
                    loadData(pageCur, pageSize, dataUrl);

                }
            }
        });
    });
    //角色授权
    $(".btn-warning").on('click', function () {
        showMenuDialog(this);
    });

}


// 查看详情
function showDialog(_this) {
    var dialog = $("#saveOrUpdateDialog");
    // 弹窗数据清空
    dialog.find(":input").val("");
    dialog.modal("show");
    // 获取选中行数据
    var rowNode = $(_this).closest("tr");
    var menuId = rowNode.find("#id").text();
    var moduleName = rowNode.find("#name").text();
    var moduleUrl = rowNode.find("#url").text();
    var isLink = rowNode.find("#isLink").prop('checked');
    var parentId = rowNode.find("#parentId").text();
    var icon = rowNode.find("#icon").text();
    // 下来列表选中
    dialog.find("input[name='parentModule']").val(parentId);
    // 数据填充
    dialog.find("input[type=hidden]").val(menuId);
    dialog.find("input[name='name']").val(moduleName);
    dialog.find("input[name='url']").val(moduleUrl);
    dialog.find("input[type=checkbox]").prop('checked', isLink);
    dialog.find("input[name='icon']").val(icon);
    if ($(_this).hasClass("btn-xs")) {
        dialog.find("h4").text("修改");
    } else {
        dialog.find("h4").text("添加");
    }
    var dialog_parentModule = dialog.find("#parentModule");
    // 加载一级模块列表数据
    var defaultHtml1 = '<option value="0" selected>根模块</option>';
    loadModule(dialog_parentModule, 0, defaultHtml1);
}

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

// 分页方法 element jquery selector
function paginator(element, currentPage, pageSize, totalCount, url) {
    var pageCount = Math.ceil(totalCount / pageSize);
    var options = {
        currentPage: currentPage,
        totalPages: pageCount,
        bootstrapMajorVersion: 3,
        alignment: "right",
        numberOfPages: 5,
        size: "normal",
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "尾页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {
            // 加载数据
            loadData(page, pageSize, url);
            pageCur = page;
        }
    }
    // 渲染分页模块
    $(element).bootstrapPaginator(options);
}
