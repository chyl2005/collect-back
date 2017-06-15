package com.mm.back.common;

/**
 * Author:chyl2005
 * Date:17/3/26
 * Time:10:13
 * Desc:描述该类的作用
 */
public class WebConf {

    /**
     * ===================WebResponse的status常量配置===================
     */
    // 正常
    public static final int STATUS_OK = 0;
    // 系统异常
    public static final int SYSTEM_ERROR = 1;
    // BA权限错误
    public static final int AUTH_ERROR = 2;
    // 参数错误
    public static final int PARAM_ERROR = 3;
    // 业务异常
    public static final int BIZ_ERROR = 4;
    // TOKEN权限验证错误
    public static final int TOKEN_ERROR = 5;
    // ecom商家登录错误
    public static final int ECOM_AUTH_ERROR = 6;

    public static final int TASK_ERROR = 8;
    //子账号插入出错
    public static final int SUBID_ERROR= 9;
    /**
     * ===================WebResponse的domain常量配置===================
     */
    //
    public static final String DOMAIN = "lvyou";
    /**
     * ===================WebResponse的message常量配置===================
     */
    public static final String SUCCESS_MESSAGE = "成功";
    public static final String AUTH_ERROR_MESSAGE = "BA权限验证失败";
    public static final String TOKEN_ERROR_MESSAGE = "TOKEN验证失败";
    public static final String PARAM_ERROR_MESSAGE = "参数错误";
    public static final String SYSTEM_ERROR_MESSAGE = "系统异常";
    public static final String BIZ_ERROR_MESSAGE = "业务异常";
    public static final String SUBID_ERROR_MESSAGE = "子账号插入出错";

    /**
     * ==================== 自己调用 url =============================
     */
    public static final String MEILV_URL_DEAL_GET = "/lv/deal/get";
    public static final String MEILV_URL_BATCH_DEAL_GET = "/lv/deal/batch/get";
    public static final String MEILV_URL_DEAL_ONLINE = "/lv/deal/online";
    public static final String MEILV_URL_DEAL_ONLINE_BY_MTDEALID = "/lv/deal/online/dealid";
    public static final String MEILV_URL_DEAL_ONLINE_BY_PARTNER_DEALID = "/lv/deal/online/partnerDealId";
    public static final String MEILV_URL_DEAL_OFFLINE = "/lv/deal/offline";
    public static final String MEILV_URL_GET_POI = "/lv/poi/get";
    public static final String MEILV_URL_BATCH_GET_POI = "/lv/poi/batch/get";
    public static final String MEILV_URL_POI_ONLINE_BY_PARTNERID = "/lv/poi/online/partnerid";
    public static final String MEILV_URL_POI_ONLINE_BY_PARTNERID_AND_POIID = "/lv/poi/online/partnerid/poiid";
    public static final String MEILV_URL_GET_PRICE_STOCK = "/lv/price/get";
    public static final String MEILV_URL_ONLINE_PRICE = "/lv/price/online";
    public static final String MEILV_URL_ORDER_QUERY_BY_PARTNERID = "/lv/order/query/partnerId";
    public static final String MEILV_URL_ORDER_QUERY_BY_BOOKID = "/lv/order/query/bookId";
    public static final String MEILV_URL_PARTNER = "/lv/partner/pull/all";
    public static final String MEILV_URL_PARTNER_UPDATE = "/lv/partner/update/info";
    public static final String MEILV_URL_PARTNER_INSERT = "/lv/partner/insert/info";
    public static final String MEILV_URL_LVMAMA_VOUCHER_UPDATE = "/lv/lvmama/voucher/update/info";
    public static final String MEILV_URL_LOG_INSERT = "/lv/log/insert/info";

    /**
     * =================测试工作台url==============================
     */
    public static final String ADMIN_PARTNER_POI_GET_PAGE = "admin/partner/poi/get/page";
    public static final String ADMIN_PARTNER_POI_GET_MULTI = "admin/partner/poi/get/multi";
    public static final String ADMIN_PARTNER_DEAL_GET_PAGE = "admin/partner/deal/get/page";
    public static final String ADMIN_PARTNER_DEAL_GET_MULTI = "admin/partner/deal/get/multi";
    public static final String ADMIN_PARTNER_PRICE_GET = "admin/partner/price/get";
    public static final String ADMIN_PARTNER_ORDER_TEST = "admin/partner/order/test";
    public static final String ADMIN_PARTNER_ORDER_VALIDATE = "admin/partner/order/validate";
    public static final String ADMIN_PARTNER_ORDER_CREATE = "admin/partner/order/create";
    public static final String ADMIN_PARTNER_ORDER_PAY = "admin/partner/order/pay";
    public static final String ADMIN_PARTNER_ORDER_REFUND = "admin/partner/order/refund";
    public static final String ADMIN_PARTNER_ORDER_QUER = "admin/partner/order/quer";
    public static final String ADMIN_PARTNER_ORDER_QUER_REFUND = "admin/partner/order/quer/refund";

    /**
     * ==================== 适配器调用 url =============================
     */
    public static final String MEILV_URL_DEAL_PUSH = "/partner/lv/deal/push";
    public static final String MEILV_URL_PUSH_PARTNER = "/partner/lv/partner/push";
    public static final String MEILV_URL_PUSH_POI = "/partner/lv/poi/push";
    public static final String MEILV_URL_PUSH_PRICE_STOCK = "/partner/lv/price/push";
    public static final String MEILV_URL_DEAL_CHANGE_NOTICE = "/partner/lv/deal/change/notice";
    public static final String MEILV_URL_ORDER_REFUND_NOTICE_ORDER = "/partner/lv/order/refund/notice";
    public static final String MEILV_URL_ORDER_CONSUME_NOTICE_ORDER = "/partner/lv/order/consume/notice";
    public static final String MEILV_URL_ORDER_NOTICE = "/partner/lv/order/notice";

    /**
     * ==================== 默认审核分页参数 =============================
     */

    public static final int DEFAULT_AUDIT_PAGE_SIZE = 25;

}
