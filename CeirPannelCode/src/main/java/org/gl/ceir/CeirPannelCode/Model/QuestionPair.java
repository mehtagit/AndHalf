package org.gl.ceir.CeirPannelCode.Model;

public class QuestionPair{ 
	  private Integer question;
	  private String answer;
	  public QuestionPair(){};
	  
	  public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override public String toString() { return "QuestionPair [question=" +
	  question + ", answer=" + answer + "]"; }
	  
	  }