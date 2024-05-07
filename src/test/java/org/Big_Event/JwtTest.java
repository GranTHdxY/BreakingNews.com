package org.Big_Event;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String, Objects> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        //生成jwt代码
        String token = JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间（登陆完之后过了很久 再次登录）
                                                                                   // 当前时间的毫秒值过12个小时
                .sign(Algorithm.HMAC256("granthdxy"));//制定算法、配置秘钥（granthdx）

        System.out.println(token);
    }
    @Test
    public void TestParse(){
        //定义字符串，模拟token
        String token = "abc";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("granthdxy")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);//验证token 生成一个解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
        //篡改了头部或者载荷部分的数据 验证失败
        //修改了秘钥验证失败
    }

}
