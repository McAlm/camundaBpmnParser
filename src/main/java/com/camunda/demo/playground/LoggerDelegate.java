package com.camunda.demo.playground;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * This is an easy adapter implementation illustrating how a Java Delegate can
 * be used from within a BPMN 2.0 Service Task.
 */
@Component("logger")
@Slf4j
public class LoggerDelegate implements JavaDelegate {

	private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

	public void execute(DelegateExecution execution) throws Exception {

		log.info("\n\n  ... LoggerDelegate invoked by " //
				+ "activityName='" + execution.getCurrentActivityName() + "'"//
				+ ", processInstanceId=" + execution.getProcessInstanceId()//
				+ ", businessKey=" + execution.getProcessBusinessKey() + " \n\n");
	}
}
