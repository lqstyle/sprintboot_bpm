package com.kpmg.bpm.activity;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: ${todo}
 * @date 08/06/2018 10:09 下午
 */
@Service
public class VacationProcessService {

    @Autowired
    RuntimeService runtimeService;

    public void startVacationApplicationProcess() {
        runtimeService.startProcessInstanceByKey("VacationApplicationFlow");
        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("Count:" + count);
    }

    public void sendSuccessfulEmail(DelegateExecution execution) {
        System.out.println("发送email:");
    }

    public List<Map<String, Object>> getTaskListByMap(Map<String, Object> map) {

        return new ArrayList<>();
    }
}
