package tech.huning.wall.e.util.uo;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token 模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class JwtTokenUO {

    private String username;     // 用户名
    private String secretKey;    // 密钥
    private Long expireSecond;   // 失效时间(秒)
    private String token;        // 令牌

    private final Map<String, Object> claims = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireSecond() {
        return expireSecond;
    }

    public void setExpireSecond(Long expireSecond) {
        this.expireSecond = expireSecond;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public void addClaim(String key, Object value){
        this.claims.put(key, value);
    }

    public Object getClaim(String key){
        return this.claims.get(key);
    }

    public <T> T getClaim(String key, Class<T> t){
        return t.cast(claims.get(key));
    }

}
