package com.kpmg.bpm.controller;

import com.google.common.base.Preconditions;
import com.kpmg.bpm.activity.VacationProcessService;
import com.kpmg.bpm.common.ResponseEntity;
import com.kpmg.bpm.vo.UserVo;
import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 任务管理控制器
 * @date 06/06/2018 5:29 下午
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 功能描述: 查询任务
     *
     * @param: [params]
     * @return: demo.bpm.bpmvacationflow.common.ResponseEntity
     * @author: lucasliang
     * @date: 06/06/2018 8:46 下午
     */
    @RequestMapping(value = "/restTask", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity restProject(HttpSession session, @RequestBody Map<String, Object> params) {
        UserVo user = (UserVo) session.getAttribute("User");
        Preconditions.checkArgument(user != null, "用户不能为空");
        Preconditions.checkArgument(!user.getLoginName().equals(""), "用户名不能为空");
        String userName = user.getLoginName();
        TaskQuery query;
        String taskName = params.get("taskName") != null ? params.get("taskName").toString() : "";
        if (!StringUtils.isEmpty(userName)) {
            if (!StringUtils.isEmpty(taskName)) {
                query = processEngine.getTaskService().createTaskQuery().taskAssignee(userName).taskName(taskName);
            } else {
                query = processEngine.getTaskService().createTaskQuery().taskAssignee(userName);
            }

        } else {
            if (!StringUtils.isEmpty(taskName)) {
                query = processEngine.getTaskService().createTaskQuery();
            } else {
                query = processEngine.getTaskService().createTaskQuery().taskName(taskName);
            }

        }
        List<Task> list = query.list();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> map;
        for (Task task : list) {
            map = new HashMap<>();
            map.put("taskname", task.getName());
            map.put("assignee", task.getAssignee());
            map.put("id", task.getId());
            map.put("priority", task.getPriority());
            map.put("suspensionState", task.isSuspended());
            map.put("executionId", task.getExecutionId());
            map.put("owner", task.getOwner());
            res.add(map);
        }
        ResponseEntity result = new ResponseEntity();
        result.setRows(res);
        // 取总记录数
        result.setRecords(res.size());
        result.setTotal(1);
        return result;
    }

    /**
     * 功能描述:审批处理
     *
     * @param: [session, way, taskId]
     * @return: java.lang.String
     * @author: lucasliang
     * @date: 08/06/2018 2:15 下午
     */
    @RequestMapping(value = "/approvalVacationOrNot", method = RequestMethod.GET)
    @ResponseBody
    public String approvalVacation(HttpSession session, String way, String taskId) {
        String result = "";
        UserVo user = (UserVo) session.getAttribute("User");
        Preconditions.checkArgument(user != null, "用户不能为空");
        Preconditions.checkArgument(!user.getLoginName().equals(""), "用户名不能为空");
        String userName = user.getLoginName();
        if (!StringUtils.isEmpty(userName) && "admin".equals(userName)) {
            if (!StringUtils.isEmpty(taskId)) {
                if (!StringUtils.isEmpty(way) && "approval".equals(way)) {
                    processEngine.getTaskService().complete(taskId);
                    result = "approvalSuccess";
                } else if (!StringUtils.isEmpty(way) && "reject".equals(way)) {
                    processEngine.getTaskService().setAssignee(taskId, "lucas");
                    result = "rejectSuccess";
                } else {
                    result = "fail";
                }
            }
        } else if (!StringUtils.isEmpty(userName) && "lucas".equals(userName)) {
            if (!StringUtils.isEmpty(taskId)) {
                if (!StringUtils.isEmpty(way) && "approval".equals(way)) {
                    processEngine.getTaskService().setAssignee(taskId, "admin");
                    result = "approvalSuccess";
                } else if (!StringUtils.isEmpty(way) && "reject".equals(way)) {
                    //此处应该为owner,暂未获取
                    processEngine.getTaskService().setAssignee(taskId, "simon");
                    result = "rejectSuccess";
                } else {
                    result = "fail";
                }
            }
        }

        return result;
    }

}
