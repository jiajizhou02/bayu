package com.bayu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bayu.bean.*;
import com.bayu.mapper.*;
import com.bayu.vo.CellMessage;
import com.bayu.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;
    @Autowired
    private FcEstateMapper fcEstateMapper;
    @Autowired
    private FcBuildingMapper fcBuildingMapper;
    @Autowired
    private FcUnitMapper fcUnitMapper;
    @Autowired
    private FcCellMapper fcCellMapper;

    public List<TblCompany> selectCompany(){
        List<TblCompany> tblCompanyList = tblCompanyMapper.selectCompany();
        return tblCompanyList;
    }

    //在插入数据之前，先判断ZZ编码是否已存在，如果不存在才允许插入
    public Integer insertEstate(FcEstate fcEstate) {

        //定义查询包装类
        QueryWrapper queryWrapper = new QueryWrapper();
        Object estate_code = queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fcEstate1 = fcEstateMapper.selectOne(queryWrapper);
        System.out.println("    ------------fcEstate : --------------- " + fcEstate);
        //插入之前先做一个判断
        //定义返回结果
        int insert=0;
        if (fcEstate1!=null){
            System.out.println("1.不为空");
            return insert;

        }else {
            System.out.println("2.huozhe");
            insert = fcEstateMapper.insert(fcEstate);
        }
        System.out.println("3.看看是什么:"+insert);
        return insert;
    }

    public List<FcEstate> insertEstate1(FcEstate[] fcEstate) {
        System.out.println("1 进来了吗  1 + " + fcEstate);
        List<FcEstate> list=new ArrayList<>();
        // queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fcEstate1 =new FcEstate();
        list.add(fcEstate1);
        return  list;
    }

    /**
     * 先插入数据，再查询数据
     * @return
     */
    public List<FcBuilding> selectBuilding(Integer buildingNumber,String estateCode){
        System.out.println("看看有没有进来这里" + buildingNumber + " + " + estateCode);
        List<FcBuilding> fcBuildings =new ArrayList<>();
        /*QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code",estateCode);*/
        for (int i = 0;i <buildingNumber; i++) {
            FcBuilding fcBuilding  =new FcBuilding();
            fcBuilding.setBuildingCode("B" +(i+1));
            fcBuilding.setBuildingName("第" + (i+1)+"hao");
            fcBuilding.setEstateCode(estateCode);
            System.out.println("fcBuilding  :  "  +  fcBuilding.getBuildingName());
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    /* 完成ly的更新
    * */
    public  Integer updateBuilding(FcBuilding fcBuilding){
        int result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    }



    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        System.out.println("service unitMessage " +  unitMessage);
        System.out.println("unitMessage.getUnitCount() :" + unitMessage.getUnitCount() + " unitMessage.getBuildingCode(): " + unitMessage.getBuildingCode());
        List<FcUnit> fcUnits = new ArrayList<>();
        for (int i = 0;i<unitMessage.getUnitCount();i++) {
            System.out.println("进来 for 循环了");
            FcUnit fcUnit = new FcUnit();
            System.out.println("fcunit1 :" + fcUnit);
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode("U" + (i + 1));
            fcUnit.setUnitName("第" + (i + 1) + "dy");
            System.out.println("fcunit2 :" + fcUnit);
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        System.out.println("fcUnits : " + fcUnits);
        return fcUnits;
    }


    public Integer updateUnit(FcUnit fcUnit){
        int i = fcUnitMapper.updateById(fcUnit);
        return i;
    }

    public List<FcCell> insertCell(CellMessage[] cellMessages) {
        List<FcCell> list =new ArrayList<>();
        for (CellMessage cellMessage:cellMessages) {
            //楼层
            for (int i = 1; i < cellMessage.getStopFloor(); i++) {
                //房间号
                for (int j = cellMessage.getStartCellId(); j<=cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    fcCell.setCellName(i + "0" + j);
                    fcCell.setCellCode("C" + i + "0" + j);
                    fcCell.setFloorNumber(i);
                    fcCellMapper.insert(fcCell);
                    list.add(fcCell);
                }
            }
        }
        return list;
    }
}
