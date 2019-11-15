package org.gl.ceir.CeirPannelCode.Model;

public class QuestionPair{ 
	  private Integer questionId;
	  private String question;
	  private String answer;
	  private Integer id;
	            
	  public QuestionPair(){};
	  
	  public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	} 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "QuestionPair [questionId=" + questionId + ", question=" + question + ", answer=" + answer + ", id=" + id
				+ "]";
	}

}