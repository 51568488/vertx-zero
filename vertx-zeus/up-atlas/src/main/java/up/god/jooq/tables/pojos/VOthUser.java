/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IVOthUser;


/**
 * VIEW
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VOthUser implements IVOthUser {

    private static final long serialVersionUID = -295858549;

    private String pkId;
    private String sUsername;
    private String sEmail;
    private String sMobile;
    private String sPassword;
    private String sQq;
    private String sAlipay;
    private String sTaobao;
    private String sWeibo;
    private String sWechat;
    private String sSecret;
    private String tRedirectUri;
    private String sScope;
    private String sClientSecret;
    private String sGrantType;
    private String roleId;

    public VOthUser() {}

    public VOthUser(VOthUser value) {
        this.pkId = value.pkId;
        this.sUsername = value.sUsername;
        this.sEmail = value.sEmail;
        this.sMobile = value.sMobile;
        this.sPassword = value.sPassword;
        this.sQq = value.sQq;
        this.sAlipay = value.sAlipay;
        this.sTaobao = value.sTaobao;
        this.sWeibo = value.sWeibo;
        this.sWechat = value.sWechat;
        this.sSecret = value.sSecret;
        this.tRedirectUri = value.tRedirectUri;
        this.sScope = value.sScope;
        this.sClientSecret = value.sClientSecret;
        this.sGrantType = value.sGrantType;
        this.roleId = value.roleId;
    }

    public VOthUser(
        String pkId,
        String sUsername,
        String sEmail,
        String sMobile,
        String sPassword,
        String sQq,
        String sAlipay,
        String sTaobao,
        String sWeibo,
        String sWechat,
        String sSecret,
        String tRedirectUri,
        String sScope,
        String sClientSecret,
        String sGrantType,
        String roleId
    ) {
        this.pkId = pkId;
        this.sUsername = sUsername;
        this.sEmail = sEmail;
        this.sMobile = sMobile;
        this.sPassword = sPassword;
        this.sQq = sQq;
        this.sAlipay = sAlipay;
        this.sTaobao = sTaobao;
        this.sWeibo = sWeibo;
        this.sWechat = sWechat;
        this.sSecret = sSecret;
        this.tRedirectUri = tRedirectUri;
        this.sScope = sScope;
        this.sClientSecret = sClientSecret;
        this.sGrantType = sGrantType;
        this.roleId = roleId;
    }

    @Override
    public String getPkId() {
        return this.pkId;
    }

    @Override
    public VOthUser setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public String getSUsername() {
        return this.sUsername;
    }

    @Override
    public VOthUser setSUsername(String sUsername) {
        this.sUsername = sUsername;
        return this;
    }

    @Override
    public String getSEmail() {
        return this.sEmail;
    }

    @Override
    public VOthUser setSEmail(String sEmail) {
        this.sEmail = sEmail;
        return this;
    }

    @Override
    public String getSMobile() {
        return this.sMobile;
    }

    @Override
    public VOthUser setSMobile(String sMobile) {
        this.sMobile = sMobile;
        return this;
    }

    @Override
    public String getSPassword() {
        return this.sPassword;
    }

    @Override
    public VOthUser setSPassword(String sPassword) {
        this.sPassword = sPassword;
        return this;
    }

    @Override
    public String getSQq() {
        return this.sQq;
    }

    @Override
    public VOthUser setSQq(String sQq) {
        this.sQq = sQq;
        return this;
    }

    @Override
    public String getSAlipay() {
        return this.sAlipay;
    }

    @Override
    public VOthUser setSAlipay(String sAlipay) {
        this.sAlipay = sAlipay;
        return this;
    }

    @Override
    public String getSTaobao() {
        return this.sTaobao;
    }

    @Override
    public VOthUser setSTaobao(String sTaobao) {
        this.sTaobao = sTaobao;
        return this;
    }

    @Override
    public String getSWeibo() {
        return this.sWeibo;
    }

    @Override
    public VOthUser setSWeibo(String sWeibo) {
        this.sWeibo = sWeibo;
        return this;
    }

    @Override
    public String getSWechat() {
        return this.sWechat;
    }

    @Override
    public VOthUser setSWechat(String sWechat) {
        this.sWechat = sWechat;
        return this;
    }

    @Override
    public String getSSecret() {
        return this.sSecret;
    }

    @Override
    public VOthUser setSSecret(String sSecret) {
        this.sSecret = sSecret;
        return this;
    }

    @Override
    public String getTRedirectUri() {
        return this.tRedirectUri;
    }

    @Override
    public VOthUser setTRedirectUri(String tRedirectUri) {
        this.tRedirectUri = tRedirectUri;
        return this;
    }

    @Override
    public String getSScope() {
        return this.sScope;
    }

    @Override
    public VOthUser setSScope(String sScope) {
        this.sScope = sScope;
        return this;
    }

    @Override
    public String getSClientSecret() {
        return this.sClientSecret;
    }

    @Override
    public VOthUser setSClientSecret(String sClientSecret) {
        this.sClientSecret = sClientSecret;
        return this;
    }

    @Override
    public String getSGrantType() {
        return this.sGrantType;
    }

    @Override
    public VOthUser setSGrantType(String sGrantType) {
        this.sGrantType = sGrantType;
        return this;
    }

    @Override
    public String getRoleId() {
        return this.roleId;
    }

    @Override
    public VOthUser setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VOthUser (");

        sb.append(pkId);
        sb.append(", ").append(sUsername);
        sb.append(", ").append(sEmail);
        sb.append(", ").append(sMobile);
        sb.append(", ").append(sPassword);
        sb.append(", ").append(sQq);
        sb.append(", ").append(sAlipay);
        sb.append(", ").append(sTaobao);
        sb.append(", ").append(sWeibo);
        sb.append(", ").append(sWechat);
        sb.append(", ").append(sSecret);
        sb.append(", ").append(tRedirectUri);
        sb.append(", ").append(sScope);
        sb.append(", ").append(sClientSecret);
        sb.append(", ").append(sGrantType);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IVOthUser from) {
        setPkId(from.getPkId());
        setSUsername(from.getSUsername());
        setSEmail(from.getSEmail());
        setSMobile(from.getSMobile());
        setSPassword(from.getSPassword());
        setSQq(from.getSQq());
        setSAlipay(from.getSAlipay());
        setSTaobao(from.getSTaobao());
        setSWeibo(from.getSWeibo());
        setSWechat(from.getSWechat());
        setSSecret(from.getSSecret());
        setTRedirectUri(from.getTRedirectUri());
        setSScope(from.getSScope());
        setSClientSecret(from.getSClientSecret());
        setSGrantType(from.getSGrantType());
        setRoleId(from.getRoleId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IVOthUser> E into(E into) {
        into.from(this);
        return into;
    }

    public VOthUser(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
