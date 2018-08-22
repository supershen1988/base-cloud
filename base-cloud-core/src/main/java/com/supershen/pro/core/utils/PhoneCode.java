package com.supershen.pro.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取手机验证码 <br />
 *
 * @author chenyidong
 * @创建时间 2018-07-2018/7/24/024 08:55
 * @copyright © 2018哈尔滨研石科技有限公司版权所有
 */
public class PhoneCode {
    
    
    
    /**
     * 获取手机验证码 (jar包有问题暂时先不用)
     * @param phone
     * @return
     */
    public static Map<String, Object> getVerificationCode(String phone) {
     /*  Map<String, Object> flag = new HashMap<>();
        flag.put("flag", "9");
        // 传入参数电话号码不可空
        if (phone != null && !"".equals(phone)) {
            HashMap<String, Object> result = null;
            CCPRestSDK restAPI = new CCPRestSDK();
            restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
            restAPI.setAccount("8a48b55150ac70fd0150acc34f1f01e4", "22ed8d4fa75e45978d35bc245326886d");// 初始化主帐号和主帐号TOKEN
            restAPI.setAppId("8aaf07085995ddef01599b9407660171");// 初始化应用ID
            
            int num = (int) ((Math.random() * 9 + 1) * 100000);
            // 这里生成验证码与验证码时限时间并存储表
            
            // sendTemplateSMS
            // 参数1:必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
            // 参数2:必选参数 模板Id
            // 参数3:可选参数 内容数据，用于替换模板中{序号}
            result = restAPI.sendTemplateSMS(phone, "153391", new String[] { num + "", "1" });
            System.out.println("SDKTestSendTemplateSMS result=" + result);
            if ("000000".equals(result.get("statusCode"))) {
                // 正常返回输出data包体信息（map）
                HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for (String key : keySet) {
                    Object object = data.get(key);
                }
                flag.put("flag", num + "");
                return flag;
            } else {
                // 异常返回输出错误码和错误信息
                flag.put("flag", "9");
                return flag;
            }
        } else {
            flag.put("flag", "9");
            return flag;
        }*/
     return new HashMap<String, Object> ();
    }
    
}
