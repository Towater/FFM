package com.ffs.commen;

import com.ffs.api.CommonResult;
import java.util.List;
public class Query {
    public static CommonResult re=new CommonResult();
    //通用查询方法,参数为一个List<Class>
    public static  <T> CommonResult queryList(List<T> arrayList,String filedMessage){
        if(arrayList.size() == 0){
            return re.badRequest(filedMessage);
        }
        else{
            return re.success(arrayList);
        }
    }
    //通用查询方法，参数为一个Class
    public static <T> CommonResult queryClass(T clazz,String filedMessage){
        if(re.success(clazz)==null ){
            return re.badRequest(filedMessage);
        }
        else{
            return re.success(clazz);
        }

    }
}
