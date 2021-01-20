package com.camunda.demo.playground.bpmnParseListener;

import java.util.List;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GetUserTaskAssigneeAndProcessInstanceIdListener implements ExecutionListener {

	@Lazy
	@Autowired
	private HistoryService hs;

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		String processInstanceId = execution.getProcessInstanceId();

		String lastAssignee = "";

		List<HistoricTaskInstance> tasks = hs.createHistoricTaskInstanceQuery()//
				.processInstanceId(processInstanceId)//
				// .taskDefinitionKey("TaskA")//
				.orderByHistoricTaskInstanceEndTime()//
				.desc()//
				.list();

		if (tasks != null && !tasks.isEmpty()) {
			HistoricTaskInstance task = tasks.get(0);
			lastAssignee = task.getAssignee();
		}

		System.out.println("processInstanceId: " + processInstanceId);
		System.out.println("Task Assignee: " + lastAssignee);
		
		
		MDC.put("assignee", lastAssignee);
		MDC.put("processInstanceId", processInstanceId);
	}

}
