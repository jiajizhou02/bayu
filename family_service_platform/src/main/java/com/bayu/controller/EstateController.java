package com.bayu.controller;

import com.alibaba.fastjson.JSONObject;
import com.bayu.bean.*;
import com.bayu.returnJson.ReturnObject;
import com.bayu.service.EstateService;
import com.bayu.vo.CellMessage;
import com.bayu.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EstateController {

    @Autowired
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public  String selectCompany(){
        System.out.println("selectCompany");
        List<TblCompany> tblCompanyList = estateService.selectCompany();
        System.out.println(tblCompanyList);
        return JSONObject.toJSONString(new ReturnObject(tblCompanyList));
    }

    @RequestMapping("/estate/insertEstate1")
    public String insertEstate1(@RequestBody FcEstate[] fcEstate){
        System.out.println("fcestata  01 : " + fcEstate);
        //Integer result = estateService.insertEstate(fcEstate);
        /*System.out.println("-------------"+result);  // 1
        //return "";
        if(result==0){
            return JSONObject.toJSONString(new ReturnObject("0", "数据编码已经存在"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("1","插入数据成功GOOD！"));
        }*/
        //return JSONObject.toJSONString(new ReturnObject("插入房产成功"));
        return "11";
    }

    @ResponseBody
    @RequestMapping("/estate/insertEstate")
    //@RequestMapping(value = "/fcEstate",method = RequestMethod.POST)
    public String insertEstate(FcEstate fcEstate){
        System.out.println("fcestata : " + fcEstate);
        Integer result = estateService.insertEstate(fcEstate);
        System.out.println("-------------"+result);  // 1
        //return "";
        if(result==0){
            return JSONObject.toJSONString(new ReturnObject("0", "数据编码已经存在"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("1","插入数据成功GOOD！"));
        }
    }

    /*此处应该完成的是楼宇的查询功能，但大家会发现，现在数据表中没有任何楼宇的数据
    因此再辨析的时候需要进行插入且返回插入的数据
     */
    @ResponseBody
    @RequestMapping("/estate/selectBuilding")
        public String  selectBuilding(Integer buildingNumber,String estateCode){
        List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        System.out.println("1+++++" + fcBuildings);
        return JSONObject.toJSONString(new ReturnObject(fcBuildings));
    }
    /*public String name(FcBuildingMapper fcBuildingMapper){
        System.out.println("name1" + );
    }*/

    @RequestMapping ("/eatate/updateBuilding")
    public String updateBuilding(FcBuilding fcbuilding){
         System.out.println("eatate/updateBuilding" + fcbuilding);
         Integer integer = estateService.updateBuilding(fcbuilding);
         System.out.println("integer :  " + integer);
         if (integer == 1) {
             return JSONObject.toJSONString(new ReturnObject("更新LY成功"));
         }
         else{
             return JSONObject.toJSONString(new ReturnObject("更新lY失败"));
          }
     }

     @RequestMapping("/estate/selectUnit")
     public String selectUnit(@RequestBody UnitMessage[] unitMessages){
        System.out.println("selectUnit");
        List<FcUnit> allUnits=new ArrayList<>();
         for (UnitMessage unitMessage : unitMessages) {
             //（话外篇）如果使用了List.add(list1);程序只会输出一条记录。原因就是上面说的。List.add() 加List 实例，它会把这个看一个实例，而不是把那个看成一个容器。
             //
             allUnits.addAll(estateService.selectUnit(unitMessage));
             //List.add() 的含义就是：你往这个List 中添加对象，它就把自己当初一个对象，你往这个List中添加容器，它就把自己当成一个容器。
             //List.addAll()方法，就是规定了，自己的这个List 就是容器，往里面增加的List 实例，增加到里面后，都会被看成对象
             System.out.println("unitMessage : " + unitMessage);
         }
        return JSONObject.toJSONString(new ReturnObject(allUnits));
     }

     @RequestMapping("/estate/updateUnit")
    public String updateUnit(FcUnit fcUnit){
         Integer integer = estateService.updateUnit(fcUnit);    
         System.out.println("intege：" + integer );
         if (integer == 1){
             return JSONObject.toJSONString(new ReturnObject("插入更新成功！"));
         }else {
             return JSONObject.toJSONString(new ReturnObject("插入更新失败！"));
         }
     }

     @RequestMapping("/estate/insertCell")
     public String  insertCell(@RequestBody CellMessage[] cellMessage){
         System.out.println("insert cell");
         List<FcCell> list = estateService.insertCell(cellMessage);
         for (FcCell fcCell : list) {
             System.out.println("fcCelllist :" + fcCell);
         }
         System.out.println("fcCelllist1 :" + list);
         System.out.println("fcCelllist2 :" + list.toString());
         return JSONObject.toJSONString(new ReturnObject(list));
     }

     @GetMapping("/demo/sayHello")
        public String sayHello(){
            return "hello world !";
        }

}

