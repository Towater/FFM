package com.ffs.api.controller;



import com.ffs.api.CommonResult;
import com.ffs.entity.IncomeInfo;
import com.ffs.service.impl.IncomeInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@RestController
@RequestMapping("/ffs/income-info")
public class IncomeInfoController {

    @Autowired
    IncomeInfoServiceImpl incomeInfoService;

    CommonResult CR = null;

    /**
     * 数据查询
     */

    @RequestMapping("/all")
    public CommonResult getAll(HttpServletRequest req) throws Exception{


        List<IncomeInfo> infos =incomeInfoService.getAllIncomeInfo();
        if(infos.isEmpty()!=true){
            return new CommonResult().success(infos);
        }
        else{
            return new CommonResult().badRequest("没有数据");
        }
    }

    @RequestMapping("/byid")
    public CommonResult getById(HttpServletRequest request){
        Long userid = Long.parseLong(request.getAttribute("id").toString());

        List<IncomeInfo> infos =incomeInfoService.getIncomeByUserid(userid);
        if(infos.isEmpty()!=true){
            return new CommonResult().success(infos);
        }
        else {
            return new CommonResult().badRequest("没有找到数据");
        }
    }

    /**
     * 一页默认8条信息
     */

    int snum =8;//每页显示的数量
    @RequestMapping("/allLimit/{page}")
    public CommonResult limitInfo(@PathVariable("page") int page){

        List<IncomeInfo> infos = incomeInfoService.getPage(page,snum);

        if(infos.isEmpty()!=true){
            return new CommonResult().success(infos);
        }
        else {
            return new CommonResult().badRequest("没有找到数据");
        }

    }

    @RequestMapping("/idLimit")
    public CommonResult idLimitInfo( int page, HttpServletRequest req){
        long userid = Long.parseLong(req.getAttribute("id").toString());
        System.out.println(userid);
        try {

            List<IncomeInfo> infos = incomeInfoService.getPageById(page, snum, userid);
            System.out.println(userid);
            if (infos.isEmpty() != true) {
                return new CommonResult().success(infos);
            } else {

                return new CommonResult().badRequest("没有找到数据");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new CommonResult().badRequest("无法请求,请检查登陆状态");
        }
    }

    @RequestMapping("/s/pageNum")
    public long totalPage(){
        return incomeInfoService.totalPage(2,snum);
    }


    @RequestMapping("/s/idPage")
    public long totalPageById(HttpServletRequest req){
        try {
            long userid = getIdAttribute(req);
            return incomeInfoService.totalPageById(1,snum,userid);
        }
        catch (Exception e){
            return 0;
        }

    }

    /**
     * 数据删除
     */

    //根据userid删除数据(多条数据)
    @RequestMapping("/warning/erase/{userid}")
    public CommonResult delAllByUserid(@PathVariable("userid") Long userid){

        CR = new CommonResult();

        Integer result =incomeInfoService.delAllByUserid(userid);

        if(result!=0){
            return CR.success("没有数据啊啊啊");
        }
        else {
            return CR.badRequest(userid);
        }



    }

    //根据主键删除(单条数据)
    @RequestMapping("/del")
    public CommonResult delById(int id){
        int result = incomeInfoService.delById(id);

        if(result!=0){
            return new CommonResult().success(result+"条已删除");
        }
        else{
            return new CommonResult().badRequest("失败");
        }
    }

    //根据多条主键删除(多条数据)
    @RequestMapping("/del/ids")
    public CommonResult delByIds(List<Integer> ids){
        int result = incomeInfoService.delByIdS(ids);

        if(result!=0){
            return new CommonResult().success(result+"条数据删除");
        }
        else return new CommonResult().badRequest("失败");
    }

    /**
     * 数据新增
     */
    @PostMapping("/add")
    public CommonResult add( BigDecimal quota, String type, Date incomeDate, String incomeRoot, String remakers,HttpServletRequest req){

        CR = new CommonResult();
        long userid = getIdAttribute(req);
        IncomeInfo incomeInfo = new IncomeInfo();
        //incomeInfo.setId(id);
        incomeInfo.setQuota(quota);
        incomeInfo.setType(type);
        incomeInfo.setUserid(userid);
        incomeInfo.setIncomeDate(incomeDate);
        incomeInfo.setIncomeRoot(incomeRoot);
        incomeInfo.setRemakers(remakers);

        if(incomeInfoService.insert(incomeInfo)!=0){
            CR.success("数据被更新");
        }
        else {
            CR.badRequest("未成功");
        }
        return CR;
    }

    /**
     * 数据更新
     */
    @RequestMapping("/update" )
    public CommonResult updateByid( String incomeRoot, BigDecimal quota,
                                    String type,
                                    long id,
                                    String incomeDate,
                                    String remakers,
                                    HttpServletRequest req
    ) {
        try {
//            @RequestParam("incomeRoot") String incomeRoot,
//            @RequestParam("quota") BigDecimal quota,
//            @RequestParam("type") String type,
//            @RequestParam("id") long id,
//            @RequestParam("incomeDate") String incomeDate,
//            @RequestParam("remakers") String remakers,
//            @RequestParam("userid") long userid
//            JSONObject jb =  JSONObject.parseObject(data);
//            IncomeInfo incomeInfo = new IncomeInfo();
//            incomeInfo.setId(jb.getLong("id"));
//            incomeInfo.setQuota(jb.getBigDecimal("quota"));
//            incomeInfo.setType(jb.getString("type"));
//            incomeInfo.setUserid(jb.getLong("userid"));
//            incomeInfo.setIncomeDate(jb.getDate("incomeDate"));
//            incomeInfo.setIncomeRoot(jb.getString("incomeRoot"));
//            incomeInfo.setRemakers(jb.getString("remakers"));
            long userid = getIdAttribute(req);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");

            IncomeInfo incomeInfo = new IncomeInfo();
            incomeInfo.setId(id);
            incomeInfo.setIncomeRoot(incomeRoot);
            incomeInfo.setQuota(quota);
            incomeInfo.setIncomeDate(sdf.parse(incomeDate));
            incomeInfo.setUserid(userid);
            incomeInfo.setRemakers(remakers);
            incomeInfo.setType(type);


            if(incomeInfoService.updateByid(incomeInfo)!=0){
                return new CommonResult().success("数据更新成功");
            }
            else{
                return new CommonResult().badRequest("数据更新失败");
            }

        }
        catch (Exception e){
            return new CommonResult().badRequest("参数转换出错");
        }


    }


    private static Long getIdAttribute(HttpServletRequest req){
        long id = Long.parseLong(req.getAttribute("id").toString());
        return id;
    }

}
