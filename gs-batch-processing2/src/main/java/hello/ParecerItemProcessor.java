package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.santander.cef.analysis.web.model.ParecerCollection;

public class ParecerItemProcessor implements ItemProcessor<ParecerCollection, ParecerCollection> {

	static final Logger log = LoggerFactory.getLogger(ParecerItemProcessor.class);
	
	@Override
	public ParecerCollection process(final ParecerCollection parecer) throws Exception {

		return parecer;
	}

}
