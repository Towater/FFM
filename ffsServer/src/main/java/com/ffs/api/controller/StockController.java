package com.ffs.api.controller;



import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ffs.entity.Stock;



import com.alibaba.fastjson.JSONObject;
import com.ffs.api.CommonResult;
import com.ffs.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import java.net.URL;
import java.net.URLEncoder;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@RestController
@RequestMapping("/ffs/stock")
public class StockController {

    @Autowired
    StockServiceImpl stockService;


    CommonResult CR = null;


    public static final String APPKEY ="11530d5e19da61e42fc1e8ca9e4e68d0";//股票查询
    public static final String APPKEY1 ="bc495d8695f2611c63e568bd3ad905e6";//汇率查询
    public static final String APPKEY2 ="0e9ff34e659c8db8d60f0691031e0ef4";//黄金查询
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    /**
     * 沪深股市
     */
    @RequestMapping("/hs")
    public CommonResult hsStockData(String code){
        CommonResult re = new CommonResult();
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/stock/hs";//请求接口地址
        Map params = new HashMap();
        params.put("gid",code);//股票代码，上海股市以sh开头，深圳股市以sz开头
        params.put("key",APPKEY);

        try {
            result = net(url,params,"GET");
            JSONObject object = JSONObject.parseObject(result);
            if(object.getInteger("error_code")==0 ){
//                System.out.println(object.get("result"));
                re.success(object.get("result"));
            }else {
//                System.out.println(object.get("error_code")+":"+object.get("reason"));
                re.badRequest(object.get("reason"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  re;
    }

    /**
     * 香港股市
     */
    @RequestMapping("/hk")
    public CommonResult hkStockData(String code){
        CommonResult re = new CommonResult();
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/stock/hk";//请求接口地址
        Map params = new HashMap();
        params.put("num",code);//股票代码，如：00001 为“长江实业”股票代码
        params.put("key",APPKEY);//APP Key
        try {
            result = net(url,params,"GET");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getInteger("error_code")==0){
//                System.out.println(object.get("result"));
                re.success(object.get("result"));
            }else {
//                System.out.println(object.get("error_code")+":"+object.get("reason"));
                re.badRequest(object.get("reason"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  re;
    }

    /**
     * 美国股市
     */
    @RequestMapping("/usa")
    public CommonResult usaStockData(String code){
        CommonResult re = new CommonResult();
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/stock/usa";//请求接口地址
        Map params = new HashMap();
        params.put("num",code);//股票代码，如：aapl 为“苹果公司”的股票代码
        params.put("key",APPKEY);//APP Key
        try {
            result = net(url,params,"GET");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getInteger("error_code")==0){
//                System.out.println(object.get("result"));
                re.success(object.get("result"));
            }else {
//                System.out.println(object.get("error_code")+":"+object.get("reason"));
                re.badRequest(object.get("reason"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  re;
    }

    /**
     * 常用汇率
     */
    @RequestMapping("/exchange")
   public  CommonResult exchange(){
       CommonResult re = new CommonResult();
       String result =null;
       String url ="http://web.juhe.cn:8080/finance/exchange/rmbquot";//请求接口地址
        Map params=new HashMap();
        params.put("key",APPKEY1);
        try {
            result=net(url,params,"GET");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getInteger("error_code")==0){
//                System.out.println(object.get("result"));
                re.success(object.get("result"));
            }else {
//                System.out.println(object.get("error_code")+":"+object.get("reason"));
                re.badRequest(object.get("reason"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return  re;
   }

    /**
     * 黄金价格
     */
   @RequestMapping("/gold")
    public  CommonResult gold(){
        CommonResult re = new CommonResult();
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/gold/shgold";//请求接口地址，上海黄金交易所
        Map params=new HashMap();
        params.put("key",APPKEY2);
        params.put("v",0);//json版本格式，1 or 0，默认为0
        try{
            result=net(url,params,"GET");
            JSONObject object=JSONObject.parseObject(result);
            if (object.getInteger("error_code")==0){
                re.success(object.get("result"));
            }else {
                re.badRequest(object.get("reason"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  re;
    }


    @RequestMapping("/add")
    public CommonResult add(HttpServletRequest request,String stockCode){
        CR = new CommonResult();
        Long userid = Long.parseLong(request.getAttribute("id").toString());
        String stockName;
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/stock/hs";//请求接口地址
        Map params = new HashMap();
        params.put("gid",stockCode);//股票代码，上海股市以sh开头，深圳股市以sz开头
        params.put("key",APPKEY);

        Stock stock = new Stock();
        stock.setUserId(userid);
        stock.setStockCode(stockCode);

        try {
            result = net(url,params,"GET");
            JSONObject object = JSONObject.parseObject(result);
//            JSONObject obj=object.get("result");
            JSONArray result1 = object.getJSONArray("result");
            JSONObject data = result1.getJSONObject(0).getJSONObject("data");
            stockName = data.get("name").toString();
            stock.setStockName(stockName);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(stock);
        if(stockService.insert(stock)!=0){
            CR.success("添加成功");
        }
        else {
            CR.badRequest("添加失败");
        }
        return CR;
    }

    @RequestMapping("/del")
    public CommonResult del(HttpServletRequest request,String stockCode){
        Long userid = Long.parseLong(request.getAttribute("id").toString());
        CR = new CommonResult();
        Integer result=stockService.delByCode(userid,stockCode);
        if(result!=0){
            return CR.success("删除成功");
        }
        else {
            return CR.badRequest("删除失败");
        }
    }

    @RequestMapping("/byID")
    public  CommonResult findByID(HttpServletRequest request){
       CR = new CommonResult();
       Long userid = Long.parseLong(request.getAttribute("id").toString());
        List<Stock> stocks=stockService.findByID(userid);
        if(stocks.isEmpty()!=true){
            return new CommonResult().success(stocks);
        }
        else {
            return new CommonResult().badRequest("没有找到数据");
        }

    }


    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs=null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        }catch(IOException e) {

            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }
}




