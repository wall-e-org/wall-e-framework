package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.constant.CommonConstant;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.util.constant.UtilResultCode;
import tech.huning.wall.e.util.uo.JwtTokenUO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Token工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class TokenUtil {

    /**
     * 生成token
     * @param uo 数据
     * @return token
     * @throws SystemException 系统异常
     */
    public static String generateJwtToken(JwtTokenUO uo) throws SystemException {

        try{
            uo.addClaim("sub", uo.getUsername());
            uo.addClaim("created", generateCurrentDate());

            String token = Jwts
                    .builder()
                    .setClaims(uo.getClaims())
                    .setExpiration(generateExpirationDate(uo.getExpireSecond()))
                    .signWith(SignatureAlgorithm.HS512, uo.getSecretKey().getBytes(CommonConstant.ENCODE_UTF8))
                    .compact();

            return token;
        }catch (Exception e){
            throw new SystemException(e, ResultCode.FAILURE);
        }

    }

    /**
     * 验证token
     * @param secretKey 秘钥
     * @param token token
     * @return 验证结果
     * @throws SystemException 系统异常
     */
    public static JwtTokenUO verifyJwtToken(String secretKey, String token) throws SystemException {
        try{
            JwtTokenUO uo = new JwtTokenUO();

            Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes(CommonConstant.ENCODE_UTF8)).parseClaimsJws(token).getBody();
            uo.setUsername(claims.getSubject());

            return uo;
        }catch (ExpiredJwtException e){
            throw new SystemException(e, UtilResultCode.JWT_TOKEN_EXPIRED);
        }catch (Exception e){
            throw new SystemException(e, ResultCode.FAILURE);
        }
    }

    /**
     * 获取过期时间
     * @param expireSecond 有效期
     * @return 失效日期
     */
    private static Date generateExpirationDate(Long expireSecond) {
        return new Date(System.currentTimeMillis() + expireSecond * 1000);
    }

    /**
     * 获取当前时间
     * @return 日期
     */
    private static Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

}
