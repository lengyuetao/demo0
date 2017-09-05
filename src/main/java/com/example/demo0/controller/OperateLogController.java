package com.example.demo0.controller;

import com.example.demo0.enity.OperateLog;
import com.example.demo0.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by DELL on 2017/8/25.
 */
@RestController
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @RequestMapping(value="/log/insert",method = RequestMethod.POST)
    public String insert(@RequestParam(value="operateName")String operateName,
                         @RequestParam(value="descripes")String descripes,
                         @RequestParam(value="content")String content){
        Math.random();

        OperateLog operateLog=new OperateLog();

        operateLog.setId((long) new Random().nextInt(1000));
        operateLog.setOperateName(operateName);
        operateLog.setDescripes(descripes);
        operateLog.setContent(content);
        operateLog.setAddTime(new Date());
        operateLogService.insert(operateLog);
        return "success";
    }
    @RequestMapping(value="/log/list",method = RequestMethod.POST)
    public List<OperateLog> findOperateLogList(){
        return operateLogService.fingOperateLogList();
    }

    @RequestMapping(value="/log/delete",method = RequestMethod.POST)
    public String delete(@RequestParam(value="")Integer id){
        operateLogService.delete(id);
        return "success";

    }

    @RequestMapping(value="/log/update",method =RequestMethod.POST)
    public String update(@RequestParam(value="id")Long id,
                         @RequestParam(value="operateName")String operateName,
                         @RequestParam(value="descripes")String descripes,
                         @RequestParam(value="content")String content){

        OperateLog operateLog=new OperateLog();
        operateLog.setId(id);
        operateLog.setOperateName(operateName);
        operateLog.setDescripes(descripes);
        operateLog.setContent(content);

        operateLogService.update(operateLog);

        return "success";

    }

    @RequestMapping(value="/log/get",method = RequestMethod.POST)
    public OperateLog get(@RequestParam(value="id")Integer id){
        return  operateLogService.findOperateLog(id);
    }
}
