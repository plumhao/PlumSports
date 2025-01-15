package com.lzh.sports.controller;

import com.lzh.sports.entity.Enums;
import com.lzh.sports.tools.dto.*;
import com.lzh.sports.tools.dto.SelectResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController()
@RequestMapping("/Select")
public class SelectController {

    /**
     * 角色枚举接口
     */
    @RequestMapping(value = "/RoleType", method = RequestMethod.POST)
    public PagedResult<SelectResult> RoleType() throws InvocationTargetException, IllegalAccessException {

       var rs=Arrays.stream(Enums.RoleType.values()).map(n->new SelectResult(n.toString(),n.name(),Integer.toString(n.index()),"")).toList();
       return PagedResult.GetInstance(rs,rs.stream().count());
    }
  
     /**
     *使用状态枚举接口
     */
    @RequestMapping(value = "/EquipmentStatus", method = RequestMethod.POST)
    public PagedResult<SelectResult> EquipmentStatus() throws InvocationTargetException, IllegalAccessException {

       var rs=Arrays.stream(Enums.EquipmentStatus.values()).map(n->new SelectResult(n.toString(),n.name(),Integer.toString(n.index()),"")).toList();
       return PagedResult.GetInstance(rs,rs.stream().count());
    }
  
     /**
     *报名状态枚举接口
     */
    @RequestMapping(value = "/CouseAppointStatus", method = RequestMethod.POST)
    public PagedResult<SelectResult> CouseAppointStatus() throws InvocationTargetException, IllegalAccessException {

       var rs=Arrays.stream(Enums.CouseAppointStatus.values()).map(n->new SelectResult(n.toString(),n.name(),Integer.toString(n.index()),"")).toList();
       return PagedResult.GetInstance(rs,rs.stream().count());
    }

    /**
     *处理状态枚举接口
     */
    @RequestMapping(value = "/ProcessingStatus", method = RequestMethod.POST)
    public PagedResult<SelectResult> ProcessingStatus() throws InvocationTargetException, IllegalAccessException {

        var rs=Arrays.stream(Enums.ProcessingStatus.values()).map(n->new SelectResult(n.toString(),n.name(),Integer.toString(n.index()),"")).toList();
        return PagedResult.GetInstance(rs,rs.stream().count());
    }

    /**
     * 审核状态枚举接口
     */
    @RequestMapping(value = "/AuditStatus", method = RequestMethod.POST)
    public PagedResult<SelectResult> AuditStatus() throws InvocationTargetException, IllegalAccessException {

        var rs=Arrays.stream(Enums.AuditStatus.values()).map(n->new SelectResult(n.toString(),n.name(),Integer.toString(n.index()),"")).toList();
        return PagedResult.GetInstance(rs,rs.stream().count());
    }
  
}
