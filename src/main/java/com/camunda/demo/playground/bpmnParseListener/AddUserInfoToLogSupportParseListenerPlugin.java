package com.camunda.demo.playground.bpmnParseListener;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserInfoToLogSupportParseListenerPlugin extends AbstractProcessEnginePlugin {

	@Autowired
	private AddUserInfoToLogSupportParseListener addUserInfoToLogSupportParseListener;

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

		// get all existing preParseListeners
		List<BpmnParseListener> preParseListeners = processEngineConfiguration.getCustomPreBPMNParseListeners();

		if (preParseListeners == null) {
			// if no preParseListener exists, create new list
			preParseListeners = new ArrayList<BpmnParseListener>();
		}

		// add new BPMN Parse Listener
		preParseListeners.add(addUserInfoToLogSupportParseListener);
		processEngineConfiguration.setCustomPreBPMNParseListeners(preParseListeners);
	}
}