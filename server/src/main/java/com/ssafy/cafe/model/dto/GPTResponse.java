package com.ssafy.cafe.model.dto;

import java.util.List;

public class GPTResponse {
	private List<GPTChoice> choices;

	public GPTResponse() {}

	public GPTResponse(List<GPTChoice> choices) {
		super();
		this.choices = choices;
	}

	public List<GPTChoice> getChoices() {
		return choices;
	}

	public void setChoices(List<GPTChoice> choices) {
		this.choices = choices;
	}

	public static class GPTChoice{
		private GPTMessage message;
		
		public GPTChoice() {}

		public GPTChoice(GPTMessage message) {
			super();
			this.message = message;
		}

		public GPTMessage getMessage() {
			return message;
		}

		public void setMessage(GPTMessage message) {
			this.message = message;
		}
		
		@Override
		public String toString() {
			return "GPTChoice [message=" + message + "]";
		}

		public static class GPTMessage{
			private String content;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public GPTMessage() {}
			
			public GPTMessage(String content) {
				super();
				this.content = content;
			}

			@Override
			public String toString() {
				return "GPTMessage [content=" + content + "]";
			}
		}
	}
}
