package com.ffs.api.controller;


import com.ffs.api.CommonResult;
import com.ffs.entity.PayInfo;
import com.ffs.service.impl.PayInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
@RequestMapping("/ffs/pay-info")
public class PayInfoController {

    @Autowired
    PayInfoServiceImpl ps;

    CommonResult CR = null;

    /**
     * 查
     */
    @RequestMapping("/all")
    public CommonResult getAll() {
        List<PayInfo> infos =ps.getAllInfo();
        if(infos.isEmpty()!=true){
            return new CommonResult().success(infos);
        }
        else{
            return new CommonResult().badRequest("没有数据");
        }
    }

    @RequestMapping("/byid")
    public CommonResult getById(HttpServletRequest req){

        long userid = getIdAttribute(req);
        List<PayInfo> infos =ps.getInfoByUserid(userid);
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

        List<PayInfo> infos = ps.getPage(page,snum);

        if(infos.isEmpty()!=true){
            return new CommonResult().success(infos);
        }
        else {
            return new CommonResult().badRequest("没有找到数据");
        }

    }

    @RequestMapping("/idLimit")
    public CommonResult idLimitInfo(int page, HttpServletRequest req){

        try {
            Long userid = getIdAttribute(req);
            List<PayInfo> infos = ps.getPageById(page, snum, userid);

            if (infos.isEmpty() != true) {
                return new CommonResult().success(infos);
            } else {
                return new CommonResult().badRequest("没有找到数据");
            }
        }
        catch (Exception e){
            return new CommonResult().badRequest("无法请求,请检查登陆状态");
        }
    }

    @RequestMapping("/s/pageNum")
    public long totalPage(){
        return ps.totalPage(2,snum);
    }

    @RequestMapping("/s/idPage")
    public long totalPageById(HttpServletRequest req){
        try {
            Long userid = Long.parseLong(req.getAttribute("id").toString());
            return ps.totalPageById(1,snum,userid);
        }
        catch (Exception e){
            return 0;
        }

    }

    /**
     * 删
     */

    @RequestMapping("/warning/erase/{userid}")
    public CommonResult delAllByUserid(@PathVariable("userid") Long userid){

        CommonResult CR = new CommonResult();

        Integer result =ps.delAllByUserid(userid);

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
        int result = ps.delById(id);

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
        int result = ps.delByIdS(ids);

        if(result!=0){
            return new CommonResult().success(result+"条数据删除");
        }
        else return new CommonResult().badRequest("失败");
    }

    /**
     * 改
     */
    @PostMapping("/add")
    public CommonResult add(BigDecimal quota, String type,  Date payDate, String payFor, String remakers,HttpServletRequest req){
        CR = new CommonResult();
        long userid = getIdAttribute(req);
        PayInfo py = new PayInfo();
        py.setQuota(quota);
        py.setType(type);
        py.setUserid(userid);
        py.setPayDate(payDate);
        py.setPayFor(payFor);
        py.setRemakers(remakers);

        if(ps.insert(py)!=0){
            CR.success("数据被更新");
        }
        else {
            CR.badRequest("未成功");
        }
        return CR;
    }


    @RequestMapping ("/update" )
    public CommonResult updateByid(@RequestParam("payFor") String payFor,
                                   @RequestParam("quota") BigDecimal quota,
                                   @RequestParam("type") String type,
                                   @RequestParam("id") long id,
                                   @RequestParam("payDate") Date payDate,
                                   @RequestParam("remakers") String remakers,
                                   HttpServletRequest req) {
        try {
            long userid = getIdAttribute(req);
            PayInfo py = new PayInfo();
            py.setId(id);
            py.setPayFor(payFor);
            py.setQuota(quota);
            py.setPayDate(payDate);
            py.setUserid(userid);
            py.setRemakers(remakers);
            System.out.println(py);

            if(ps.updateByid(py)!=0){
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
