package tech.huning.wall.e.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.util.constant.UtilResultCode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class JwtUtil {

    /**
     * 生成token
     * @param issuer 签名
     * @param secret 秘钥
     * @param expireMillisecond 过期时间
     * @param claims 信息
     * @return token
     * @throws SystemException 系统异常
     */
    public static String generateToken(String issuer, String secret, int expireMillisecond, Map<String, String> claims) throws SystemException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(issuer)
                    .withExpiresAt(DateUtil.addMillisecond(new Date(), expireMillisecond));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            throw new SystemException(e, UtilResultCode.GENERATE_TOKEN_FAILURE);
        }
    }

    /**
     * 验证token
     * @param issuer 签名
     * @param secret 秘钥
     * @param token  token
     * @return  信息
     * @throws SystemException 系统异常
     */
    public static Map<String, String> verifyToken(String issuer, String secret, String token) throws SystemException {

        if(StringUtil.isEmpty(token)){
            throw new SystemException(UtilResultCode.VERIFY_TOKEN_CAN_NOT_BE_EMPTY);
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>(map.size());
            map.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        } catch(TokenExpiredException e) {
            throw new SystemException(e, UtilResultCode.VERIFY_TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new SystemException(e, UtilResultCode.VERIFY_TOKEN_FAILURE);
        }
    }

}
