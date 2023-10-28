package tech.huning.wall.e.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class JWTokenUtil {

    private static final String CLAIM_KEY_ACCOUNT = "account";

    private JWTokenUtil(){}

    /**
     * 生成token
     * @param account      账号
     * @param secretKey    秘钥
     * @param expireMinute 失效分钟
     * @return token
     * @throws SystemException 系统异常
     */
    public static String getToken(String account, String secretKey, int expireMinute) throws SystemException {
        try {
            //headers
            Map<String, Object> headers = new HashMap<String, Object>();
            headers.put("alg", "HS256");
            headers.put("typ", "JWT");

            //签发时间
            Date iatDate = new Date();

            //过期时间30分钟
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, expireMinute);
            Date expiresDate = nowTime.getTime();

            return JWT.create()
                    .withHeader(headers) //headers
                    .withClaim(CLAIM_KEY_ACCOUNT, account) //payload
                    .withIssuedAt(iatDate) //签发时间
                    .withExpiresAt(expiresDate) //过期时间
                    .sign(Algorithm.HMAC256(secretKey)); //签名加密
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 验证token
     * @param token     token
     * @param secretKey 秘钥
     * @throws SystemException 系统异常
     */
    public static void verifyToken(String token, String secretKey) throws SystemException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 获取账号信息
     * @param token     token
     * @param secretKey 秘钥
     * @return 账号
     * @throws SystemException 系统异常
     */
    public static String getAccount(String token,String secretKey) throws SystemException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim account = claims.get(CLAIM_KEY_ACCOUNT);
            if(account == null || StringUtils.isEmpty(account.asString())){
                throw new SystemException(ResultCode.FAILURE);
            }
            return account.asString();
        } catch (Exception e) {
            throw new SystemException(ResultCode.FAILURE);
        }
    }

}
