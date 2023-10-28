package tech.huning.wall.e.specs.constant;

import tech.huning.wall.e.specs.model.pojo.Tenant;

/**
 * 通用常量
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CommonConstant {

    /**
     * 删除标记
     */
    public static final byte IS_DELETED_YES = 1;
    public static final byte IS_DELETED_NO = 0;

    /**
     * 封面展示标记
     */
    public static final byte IS_COVERED_YES = 1;
    public static final byte IS_COVERED_NO = 0;

    /**
     * 状态
     */
    public static final Integer STATUS_DRAFT = 0;
    public static final Integer STATUS_PUBLISHED = 1;
    public static final Integer STATUS_OFFLINE = 10;
    public static final Integer STATUS_ONLINE = 11;

    /**
     * 分页默认值,默认页码,默认每页条数
     */
    public static final Integer PAGE_NO_DEFAULT_VALUE = 1;
    public static final Integer PAGE_SIZE_DEFAULT_VALUE = 50;

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 字符编码UTF-8
     */
    public static final String ENCODE_UTF8 = "UTF-8";

    /**
     * 统一字段
     */
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_IS_DELETED = "isDeleted";
    public static final String FIELD_NAME_PRIORITY = "priority";
    public static final String FIELD_NAME_CREATED_BY = "createdBy";
    public static final String FIELD_NAME_CREATED_AT = "createdAt";
    public static final String FIELD_NAME_UPDATED_BY = "updatedBy";
    public static final String FIELD_NAME_UPDATED_AT = "updatedAt";

    /**
     * 默认创建人,更新人
     */
    public static final String CREATED_BY_DEFAULT_VALUE = "system";
    public static final String UPDATED_BY_DEFAULT_VALUE = "system";

    /**
     * 当前验证码
     */
    public static final String SESSION_CURRENT_CAPTCHA = "session-current-captcha";

    /**
     * 当前用户数据
     */
    public static final String SESSION_CURRENT_USER = "session-current-user";

    /**
     * 租户
     */
    public static final Tenant TENANT_ADMIN = new Tenant(0L, "admin");
    public static final Tenant TENANT_WEBSITE = new Tenant(1L, "website");

    /**
     * 用户来源
     * system: 系统内用户
     * qq, wechat, weibo: 第三方系统用户
     */
    public static final String USER_ORIGIN_SYSTEM = "system";
    public static final String USER_ORIGIN_QQ = "qq";
    public static final String USER_ORIGIN_WECHAT = "wechat";
    public static final String USER_ORIGIN_WEIBO = "weibo";

    /**
     * 排序方式
     */
    public static final Integer ORDER_BY_ASC = 0;
    public static final Integer ORDER_BY_DESC = 1;

}
