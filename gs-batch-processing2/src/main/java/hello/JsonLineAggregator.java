package hello;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.transform.LineAggregator;

import com.google.gson.Gson;

public class JsonLineAggregator<T> implements LineAggregator<T>, StepExecutionListener {

	private static final String IS_FIRST_OBJECT = "isFirstObject";  
	private Gson gson = new Gson();
	private boolean isFirstObject = true;
	
	@Override
	public String aggregate(final T item) {
		if (isFirstObject) {
			isFirstObject = false;
			return "[" + gson.toJson(item);
		}

		return "," + gson.toJson(item);
	}
	
//	public void setGson(final Gson gson) {
//		this.gson = gson;
//	}
	
	@Override
	public void beforeStep(final StepExecution stepExecution) {
		if (stepExecution.getExecutionContext().containsKey(IS_FIRST_OBJECT)) {
			isFirstObject = Boolean.parseBoolean(stepExecution.getExecutionContext().getString(IS_FIRST_OBJECT));
		}
	}
	
	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		stepExecution.getExecutionContext().putString(IS_FIRST_OBJECT, Boolean.toString(isFirstObject));
		return null;
	}

	
}
