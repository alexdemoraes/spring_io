package hello;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.santander.cef.analysis.web.model.ParecerCollection;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	
	@Autowired
	public MongoTemplate mongoTemplate;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>()
				.name("parecerItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[]{"firstName", "lastName"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
					setTargetType(Person.class);
				}})
				.build();
	}
	
//	@Bean
	@StepScope
	public MongoItemReader<ParecerCollection> mongoReader(
//			@Value("#{jobParameters[fromDate]}") String fromDate, 
//			@Value("#{jobParameters[toDate]}") String toDate) {
			String fromDate, 
			String toDate) {
		MongoItemReader<ParecerCollection> reader = new MongoItemReader<ParecerCollection>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(ParecerCollection.class);
//		reader.setQuery(String.format("{user: '%s' }", "X197809"));
//		reader.setQuery(String.format("{date: { $gte : '%s', $lt : '%s' } }", fromDate, toDate));
//		reader.setQuery(String.format("{date: { $gte : '%s' } }", fromDate));
		reader.setQuery("{}");
//		Query query = new Query();
//		query.addCriteria(Criteria.where("date").gte(fromDate).lt(toDate));
//		reader.setQuery(query.getQueryObject().toJson());
		Map<String, Direction> sorts = new HashMap<>(1);
		sorts.put("status", Sort.Direction.ASC);
		reader.setSort(sorts);
		return reader;
	}
	
	
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}
	
	@Bean
	public ParecerItemProcessor parecerProcessor() {
		return new ParecerItemProcessor();
	}

	@Bean 
	public JdbcBatchItemWriter<ParecerCollection> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<ParecerCollection>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (first_name, last_name) VALUES (:user, :area)")
				.dataSource(dataSource)
				.build();
	}

	@Bean
	public FlatFileItemWriter<ParecerCollection> parecerItemWriter() {
		FlatFileItemWriter<ParecerCollection> writer = new FlatFileItemWriter<>();
//		writer.setResource(new FileSystemResource("src/main/resources/sample-data-output.csv"));
		writer.setResource(new FileSystemResource("src/main/resources/sample-data-output.json"));
		
//		DelimitedLineAggregator<ParecerCollection> aggregator = new DelimitedLineAggregator<>();
//		aggregator.setDelimiter(";");
//		BeanWrapperFieldExtractor<ParecerCollection> extractor = new BeanWrapperFieldExtractor<>();
//		extractor.setNames(new String [] { "id", "user", "date" } );
//		aggregator.setFieldExtractor(extractor);
		JsonLineAggregator<ParecerCollection> aggregator = new JsonLineAggregator<>();
		
		writer.setLineAggregator(aggregator);
		writer.setFooterCallback(new JsonFlatFileFooterCallback());
		
		return writer;
	}
	
//	@Bean 
//	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
//		return new JdbcBatchItemWriterBuilder<Person>()
//				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//				.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
//				.dataSource(dataSource)
//				.build();
//	}

	// end::readerwriterprocessor[]

	@Bean
	public Job exportParecerJob(JobCompletionNotificationListener listener, Step stepParecer) {
		return jobBuilderFactory.get("exportParecerJob")
				.incrementer(new RunIdIncrementer())
//				.listener(listener)
				.flow(stepParecer)
				.end()
				.build();
	}
	
//	@Bean
//	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
//		return jobBuilderFactory.get("importUserJob")
//				.incrementer(new RunIdIncrementer())
//				.listener(listener)
//				.flow(step1)
//				.end()
//				.build();
//	}
	
//	@Bean
//	public Step step1(JdbcBatchItemWriter<Person> writer) {
//		return stepBuilderFactory.get("step1")
//				.<Person, Person> chunk(10)
//				.reader(reader())
//				.processor(processor())
//				.writer(writer)
//				.build();
//	}
	
//	@Bean
//	public Step stepParecer(JdbcBatchItemWriter<ParecerCollection> writer) {
//		return stepBuilderFactory.get("step1")
//				.<ParecerCollection, ParecerCollection> chunk(10)
//				.reader(mongoReader("2018-06-01", "2018-06-11"))
//				.processor(parecerProcessor())
//				.writer(writer)
//				.build();
//	}
	
	@Bean
	public Step stepParecer(FlatFileItemWriter<ParecerCollection> writer) {
		return stepBuilderFactory.get("step1")
				.<ParecerCollection, ParecerCollection> chunk(10)
				.reader(mongoReader("2018-05-27", "2018-05-30"))
				.processor(parecerProcessor())
				.writer(writer)
				.build();
	}
}
