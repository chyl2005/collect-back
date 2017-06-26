// 列表数据请求url
var dataUrl = rootPath + "deviceRecord/list";

$(document).ready(function () {



    // 加载数据
    loadData(dataUrl);
    $(".searchData").on('click', function () {
        loadData(dataUrl);
    });

});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

// 获取请求服务器数据
function getServerParams() {
    var postData = {};

    postData.deviceId = $("#deviceId").val();
    postData.startTime = $("#startTime").val();
    postData.endTime = $("#endTime").val();
    return postData;
}

// 数据加载
function loadData(url) {
    var postData = getServerParams();
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


    var title = {
        text: '设备指标'
    };
    var subtitle = {
        text: 'Source: runoob.com'
    };
    var xAxis = {categories: []};
    var yAxis = {
        title: {
            text: 'Temperature (\xB0C)'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };

    var tooltip = {
        valueSuffix: '\xB0C'
    }

    var legend = {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    };


    var airTemperature = [];
    var waterTemperature = [];
    var waterHigh = [];
    var waterDepth = [];
    var series = [];
    var items = data.data;
    items.forEach((item) => {
        xAxis.categories.push(item.datekey);
        airTemperature.push(item.airTemperature);
        waterTemperature.push(item.waterTemperature);
        waterHigh.push(item.waterHigh);
        waterDepth.push(item.waterDepth);
    });
    series.push({
        name: '气温',
        data: airTemperature
    });
    series.push({
        name: '水温',
        data: waterTemperature
    });
    series.push({
        name: '水面高程',
        data: waterHigh
    });
    series.push({
        name: '水下深度',
        data: waterDepth
    });

    var json = {};
    json.title = title;
    json.subtitle = subtitle;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.tooltip = tooltip;
    json.legend = legend;
    json.series = series;

    $('.page-content-area').highcharts(json);


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
