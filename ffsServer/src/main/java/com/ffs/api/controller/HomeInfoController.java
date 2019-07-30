package com.ffs.api.controller;

import com.ffs.api.CommonResult;
import com.ffs.commen.Query;
import com.ffs.entity.HomeInfo;
import com.ffs.entity.IncomeInfo;
import com.ffs.entity.PageBean;
import com.ffs.entity.PayInfo;
import com.ffs.service.impl.PrintIncomeInfoServiceImpl;
import com.ffs.service.impl.PrintPayInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping
public class HomeInfoController {
    @Autowired
    PrintPayInfoServiceImpl printInfoService;
    @Autowired
    PrintIncomeInfoServiceImpl printIncomInfoService;
    PageBean<HomeInfo> pageBean=new PageBean<>();
    int ps=pageBean.PAGE_SIZE;
    @PostMapping(value="/total")
    public CommonResult printInfo(HttpServletRequest request){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
      // Long uid=Long.parseLong(id);
        List<IncomeInfo> listIncome=new ArrayList<>();
        List<PayInfo> listPay=new ArrayList<>();
        List<String> listIncomeTime=new ArrayList<String>();
        List<String> listPayTime=new ArrayList<String>();
        if(printIncomInfoService.isNor(uid)){
            listIncome=printIncomInfoService.findAll(uid);
            //2.得到IncomInfo表中的不同时间
            String time1=new SimpleDateFormat("yyyy-MM-dd").format(listIncome.get(0).getIncomeDate());

            listIncomeTime.add(time1);
            for(int i=0;i<listIncome.size();i++){
                time1=new SimpleDateFormat("yyyy-MM-dd").format(listIncome.get(i).getIncomeDate());
                if(i<listIncome.size()-1) {
                    if (!new SimpleDateFormat("yyyy-MM-dd").format(listIncome.get(i + 1).getIncomeDate()).equals(time1)) {
                        listIncomeTime.add(new SimpleDateFormat("yyyy-MM-dd").format(listIncome.get(i + 1).getIncomeDate()));
                    }
                }
            }
        }
        if(printInfoService.isNor(uid)){
            listPay=printInfoService.findAll(uid);
            //1.得到PayInfo表中的不同时间
            String time0= new SimpleDateFormat("yyyy-MM-dd").format(listPay.get(0).getPayDate());

            listPayTime.add(time0);
            for(int i=0;i<listPay.size();i++){
                String  time=new SimpleDateFormat("yyyy-MM-dd").format(listPay.get(i).getPayDate());
                if(i<listPay.size()-1) {
                    if (!new SimpleDateFormat("yyyy-MM-dd").format(listPay.get(i + 1).getPayDate()).equals(time)) {
                        listPayTime.add(new SimpleDateFormat("yyyy-MM-dd").format(listPay.get(i + 1).getPayDate()));
                    }
                }
            }
        }
        //3.得到一组新的List<时间>,并且按时间降序
        if(listPayTime.size()>0&&listIncomeTime.size()>0){
            for(int i=0;i<listPayTime.size();i++){
                Iterator<String> it=listIncomeTime.iterator();
                while(it.hasNext()){
                    String x=it.next();
                    if(x.equals(listPayTime.get(i))){
                        it.remove();
                    }
                }
            }
            for(int j=0;j<listIncomeTime.size();j++){
                listPayTime.add(listIncomeTime.get(j));
            }

        }
        else if(listPayTime.size()==0&&listIncomeTime.size()>0){
            for(int j=0;j<listIncomeTime.size();j++){
                listPayTime.add(listIncomeTime.get(j));
            }
        }
        for(int i=1;i<listPayTime.size();i++){
            if(listPayTime.get(i).compareTo(listPayTime.get(i-1))>0){
                String temp=listPayTime.get(i);
                int f=i-1;
                while(f>-1&&listPayTime.get(f).compareTo(temp)<0){
                    listPayTime.set(f+1,listPayTime.get(f));
                    f--;
                }
                listPayTime.set(f+1,temp);
            }
        }
        pageBean.setPc(getPc(request));
        pageBean.setUrl(getUrl(request));
        pageBean.setTr(listPayTime.size());
 //4.根据时间得到PayInfo实体类和IncomInfo实体

        List<HomeInfo> listHome=new ArrayList<HomeInfo>();
        List<String> listTime=new ArrayList<>();
        if(getPc(request)<pageBean.getTp()){
            listTime=listPayTime.subList((getPc(request)-1)*ps,getPc(request)*ps);
        }
        else{
            listTime=listPayTime.subList((getPc(request)-1)*ps,listPayTime.size());
        }
        int homeId=(getPc(request)-1)*ps;
        for(String time:listTime){
            homeId++;
           HomeInfo homeInfo=new HomeInfo();
           List<PayInfo> payInfos= printInfoService.findByTime(time,uid);
           List<IncomeInfo> incomeInfos= printIncomInfoService.findByTime(time,uid);
           BigDecimal paySum=new BigDecimal(0);//累计消费
           BigDecimal incomSum=new BigDecimal(0);//累计支出
           for(PayInfo payInfo:payInfos){
              paySum = paySum.add(payInfo.getQuota());
           }
           for(IncomeInfo incomeInfo:incomeInfos){
               incomSum=incomSum.add(incomeInfo.getQuota());
           }
           //映射到一个HomeInfo中
            homeInfo.setId(homeId);
            homeInfo.setPayMoney(paySum);
            homeInfo.setIncomMoney(incomSum);
            homeInfo.setDate(time);
            listHome.add(homeInfo);
            pageBean.setBeanList(listHome);
        }
        return Query.queryClass( pageBean,"找不到资源");
    }
    /**
     * 得到当前页pc
     * @param req
     * @return
     */
    private int getPc(HttpServletRequest req){
        int pc=1;
        String p=req.getParameter("pc");
        if(p!=null && !p.trim().isEmpty()){
            try{
                pc=Integer.parseInt(p);
            }catch(RuntimeException e){}
        }
        return pc;
    }

    /**
     * 得到请求路径
     * @param req
     * @return
     */
    private String getUrl(HttpServletRequest req){
        String url = req.getRequestURI() + "?" + req.getQueryString();
        int index=url.lastIndexOf("&pc=");
        if(index != -1){
            url=url.substring(0, index);
        }
        return url;
    }
}

