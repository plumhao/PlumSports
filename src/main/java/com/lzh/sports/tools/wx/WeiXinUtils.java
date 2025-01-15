package com.lzh.sports.tools.wx;
import org.json.JSONObject;
import com.lzh.sports.tools.HttpUtils;

public class WeiXinUtils {

    public static final String APP_ID = "wxe774b4e5221f9608";
    public static final String APP_SECRET = "c0727db7dbd0717e1e1010cc702a6457";
    /**
     * 找微信去获取认证token
     */
    public static String GetAccessToken() {
        String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;
        String result = HttpUtils.Get(apiUrl);
        return "";
    }

    /**
     * 根据code获取openid
     */
    public static String GetOpenIdByCode(String code) {
        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+APP_ID+ "&secret="+APP_SECRET+"&js_code="+code+"&grant_type=authorization_code";
        String result = HttpUtils.Get(apiUrl);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.getString("openid");
    }

}
