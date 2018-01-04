/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.domain;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.AbstractKeys;

import up.god.jooq.domain.tables.SecUser;


/**
 * A class modelling indexes of tables of the <code>DB_HTL</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index SEC_USER_PRIMARY = Indexes0.SEC_USER_PRIMARY;
    public static final Index SEC_USER_UK_SEC_USER_S_ALIPAY_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_ALIPAY_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_CODE_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_CODE_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_EMAIL_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_EMAIL_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_MOBILE_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_MOBILE_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_QQ_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_QQ_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_TAOBAO_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_TAOBAO_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_USERNAME_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_USERNAME_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_WECHAT_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_WECHAT_Z_SIGMA;
    public static final Index SEC_USER_UK_SEC_USER_S_WEIBO_Z_SIGMA = Indexes0.SEC_USER_UK_SEC_USER_S_WEIBO_Z_SIGMA;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 extends AbstractKeys {
        public static Index SEC_USER_PRIMARY = createIndex("PRIMARY", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.PK_ID }, true);
        public static Index SEC_USER_UK_SEC_USER_S_ALIPAY_Z_SIGMA = createIndex("UK_SEC_USER_S_ALIPAY_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_ALIPAY, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_CODE_Z_SIGMA = createIndex("UK_SEC_USER_S_CODE_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_CODE, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_EMAIL_Z_SIGMA = createIndex("UK_SEC_USER_S_EMAIL_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_EMAIL, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_MOBILE_Z_SIGMA = createIndex("UK_SEC_USER_S_MOBILE_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_MOBILE, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_QQ_Z_SIGMA = createIndex("UK_SEC_USER_S_QQ_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_QQ, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_TAOBAO_Z_SIGMA = createIndex("UK_SEC_USER_S_TAOBAO_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_TAOBAO, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_USERNAME_Z_SIGMA = createIndex("UK_SEC_USER_S_USERNAME_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_USERNAME, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_WECHAT_Z_SIGMA = createIndex("UK_SEC_USER_S_WECHAT_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_WECHAT, SecUser.SEC_USER.Z_SIGMA }, true);
        public static Index SEC_USER_UK_SEC_USER_S_WEIBO_Z_SIGMA = createIndex("UK_SEC_USER_S_WEIBO_Z_SIGMA", SecUser.SEC_USER, new OrderField[] { SecUser.SEC_USER.S_WEIBO, SecUser.SEC_USER.Z_SIGMA }, true);
    }
}
