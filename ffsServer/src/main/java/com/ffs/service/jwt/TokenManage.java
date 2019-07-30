package com.ffs.service.jwt;


import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.time.Instant;

@Service
@Component
public class TokenManage {

    @Value("${jwt.secret}")
    private  String SECRET;

    @Value("${jwt.expires}")
    private  String EXPIRES;

    /**
     * sha256_HMAC加密
     * @param message 消息
     * @param secret 秘钥
     * @return
     */
    public static String sha256_HMAC(String message,String secret){
        String hash = "";
        try{
            //
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch(Exception e){
            System.out.println("Error HmacSHA256 ==============="+e.getMessage());
        }
        return hash;
    }

    /**
     * 将加密后的字节数组转换成字符串
     * @param b
     * @return
     */
    public static String byteArrayToHexString(byte[] b){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for(int i = 0;b!=null&i<b.length;i++){
            stmp = Integer.toHexString(b[i] & 0XFF);
            if(stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * 中文字符串转十六进制
     */
    public static String decodeShiLiu(String str) {
        if ( str == null ) {
            return "转换失败";
        }
        String gbk;
        try {
            byte[] sss = str.getBytes("GBK");  //中文字符串转byte数组
            gbk = unpack(sss); // byte数组转十六进制
        } catch ( Exception E ) {
            gbk = "转换失败";
        }
        return gbk;
    }

    /**
     * byte数组转十六进制，模拟php中unpack
     */
    public static String unpack(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 十六进制转中文字符串
     */
    public static String decodeString(String str) {
        if ( str == null ) {
            return "转换失败";
        }
        byte[] s = pack(str); //十六进制转byte数组
        String gbk;
        try {
            gbk = new String(s, "gbk"); //byte数组转中文字符串
        } catch ( UnsupportedEncodingException ignored ) {
            gbk = "转换失败";
        }
        return gbk;
    }

    /**
     * 十六进制转byte数组，模拟php中pack
     */
    public static byte[] pack(String str) {
        int nibbleshift = 4;
        int position = 0;
        int len = str.length() / 2 + str.length() % 2;
        byte[] output = new byte[len];
        for (char v : str.toCharArray()) {
            byte n = (byte) v;
            if (n >= '0' && n <= '9') {
                n -= '0';
            } else if (n >= 'A' && n <= 'F') {
                n -= ('A' - 10);
            } else if (n >= 'a' && n <= 'f') {
                n -= ('a' - 10);
            } else {
                continue;
            }
            output[position] |= (n << nibbleshift);
            if (nibbleshift == 0) {
                position++;
            }
            nibbleshift = (nibbleshift + 4) & 7;
        }
        return output;
    }

    /**
     * 生成Token
     * @param userId
     * @param type
     * @return
     */
    public String creactToken(Long userId,String type){

        //head
        JSONObject head = new JSONObject();
        head.put("alg","HS256");
        head.put("typ","JWT");

        String header = Base64.encodeBase64String(head.toJSONString().getBytes());

        //payload
        JSONObject pl = new JSONObject();
        pl.put("userId",userId);
        pl.put("type",type);
        Long time = Instant.now().toEpochMilli()+(86400000*Long.parseLong(EXPIRES));
        pl.put("expires",time);

        String payload = Base64.encodeBase64String(pl.toJSONString().getBytes());

        //Signature
        String signature = sha256_HMAC((header+"."+payload),SECRET);

        return header+"."+payload+"."+signature;
    }

    /**
     * 将token分段
     * @param token
     * @return
     */
    public String[] subsection(String token){
        String[] jwt = token.split("\\.");
        return  jwt;
    }

    /**
     * 验证Token是否正确
     *
     * @return true or false
     */
    public boolean examineToken(String header,String payload,String signature){

        String unKnown= sha256_HMAC((header+"."+payload),SECRET);
        if(unKnown.equals(signature)){
            return true;
        }
        return false;
    }

    /**
     * 解析Token中带的信息
     *
     * @return JSONObject
     */
    public JSONObject fetchToken(String payload) {
        String js = new String(Base64.decodeBase64(payload));
        return JSONObject.parseObject(js);
    }
}