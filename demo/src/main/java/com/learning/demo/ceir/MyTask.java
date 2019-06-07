package com.learning.demo.ceir;

public class MyTask implements Runnable {

	private RuleSolverService ruleSolverService;

	private Request request;

	private OutputWritter outputWritter;

	public MyTask(Request request) {
		this.request = request;
	}

	@Override
	public void run() {
		Rule failedRule = ruleSolverService.checkFailedRule(request);
		if(failedRule == null) {
			
		}
		outputWritter.write(request);
	}

}
