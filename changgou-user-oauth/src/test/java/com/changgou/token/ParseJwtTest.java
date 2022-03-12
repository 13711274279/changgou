package com.changgou.token;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/*****
 * @Author: www.itheima
 * @Date: 2019/7/7 13:48
 * @Description: com.changgou.token
 *  使用公钥解密令牌数据
 ****/
public class ParseJwtTest {

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJpdGhlaW1hIiwiaWQiOiIxIn0.UfRyQfvvOqfE2TmhfiGZl-AYgQyDNdXSSp0_C9khR6NpSqS-K4DVhnjTjdKs8PlgrA1MJ3Elwf9DTeR2jwH26TPOrhuRs_1AsRW9cVaXrqbk4Xw97lLvsZKZRyShNk9OWYBEJUKBq8JtYAnMA5XS6MYBcvR6yAXeesEyQZE2vZWznzcfKEF67W19nMRchEeMLfZKXfzCnc6DeKx3g5E_66s3d1ekFPkL1t12I4BlsXpZkB31BI_0E7S9HIBLC5QAumfr9Xj80nDXSFJyRFkxLOvDOt6o1HAQHLJHcOHYUFIVrxECkb0nM1ZmBcXNb1aVPnrBIktHCeM0L1OqRuQ5rg";

        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA10CtXJLMtkFmPUNkRNQK7smqMGTm+O29WR8JaWrOksGYmImiaCVg1fo7yIbyKiukl+uBl9t4F9POkGPOeilWuNfz8QhAdQ1CoAVUISIsvn2oJL8+x/3My4kHHtZKJsRuIJilfnyiVRdkTEs2wSDipOFxczEIyM1tWR1Odbepax0j6Jxvg38mzqoMViFfHhtuh0h5U659wIgRGXlJuGuBpVbIbp7CO+/DuQR0M/dX4GnwS4TXXUqCJ651zz28ERuEtVRjO3FIqc1U4kvtMEsPMdpTCsawj2cfv9Zj3EfVOK3thAOMsjJVukOWwejOIw5qfdbHMgun6XB5aORcbplHawIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
