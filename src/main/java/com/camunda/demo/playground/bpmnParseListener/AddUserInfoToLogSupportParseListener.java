package com.camunda.demo.playground.bpmnParseListener;

import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserInfoToLogSupportParseListener extends AbstractBpmnParseListener {

	@Autowired
	private GetUserTaskAssigneeAndProcessInstanceIdListener assigneeListener;

	@Override
	public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope, ActivityImpl activity) {
		if (activity.isAsyncBefore()) {
			activity.addListener(ExecutionListener.EVENTNAME_START, assigneeListener);
		}
	}
}