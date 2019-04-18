package uk.uoa.cs.princSwEng.resource;

import com.fasterxml.jackson.core.*;
import java.io.*;

public class Result extends Resource {
	private final int sentence;
	private final int group_id;
	private final boolean correct;
	private final String problem_phrase;
	private final int confidence;
	private final String own_translation;
	
	public Result(final int sentence, final int group_id, final boolean correct, final String problem_phrase, final int confidence, final String own_translation) {
		this.sentence = sentence;
		this.group_id = group_id;
		this.correct = correct;
		this.problem_phrase = problem_phrase;
		this.confidence = confidence;
		this.own_translation = own_translation;
	}
	
	public final int getResultSentence() {
		return sentence;
	}
	
	public final int getResultGroup() {
		return group_id;
	}
	
	public final boolean getResultCorrect() {
		return correct;
	}
	
	public final String getResultProblemPhrase() {
		return problem_phrase;
	}
	
	public final int getResultConfidence() {
		return confidence;
	}
	
	public final String getResultOwnTranslation() {
		return own_translation;
	}
	
	public final void toJSON(final OutputStream out) throws IOException { }
}
