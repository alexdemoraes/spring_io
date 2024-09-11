package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class DataAccessConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessConfiguration.class);

	private static final String MONGO_DB = "MONGO_DB";
	private static final String MONGO_HOST = "MONGO_HOST";
	private static final String MONGO_PORT = "MONGO_PORT";
	private static final String MONGO_USER = "MONGO_USER";
	private static final String MONGO_PASS = "MONGO_PASS";
	private static final String MONGO_PORT_DEFAULT = "27017";
	private static final String MONGO_SOCKET_TIMEOUT = "MONGO_SOCKET_TIMEOUT";

	private static final String SECRET_MONGO_USER = "MONGODB_USERNAME";
	private static final String SECRET_MONGO_PASS = "MONGODB_PASSWORD";
	private static final String MONGO_DBASE = "MONGODB_DBNAME";

	@Bean
	public MongoTemplate mongoTemplate() {
		MongoDbFactory factory = mongoDbFactory();
		MongoTemplate template = new MongoTemplate(factory, new MappingMongoConverter(new DefaultDbRefResolver(factory), new MongoMappingContext()));
//		((MappingMongoConverter) template.getConverter()).setTypeMapper(new DefaultMongoTypeMapper(null));
		return template;
	}
	
	@Bean
	public MongoDbFactory mongoDbFactory() {
		String dbName = EnvKeyHelper.get(MONGO_DBASE, "cef");
		LOGGER.info(String.format("databaseName ==> %s", dbName));
		
		MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient(), dbName);
		return factory;
	}
	
	@Bean
	public MongoClient mongoClient() {

		String user = EnvKeyHelper.secretFile(
				EnvKeyHelper.get(SECRET_MONGO_USER, ""),
				EnvKeyHelper.get(MONGO_USER, ""));
		String pass = EnvKeyHelper.secretFile(
						EnvKeyHelper.get(SECRET_MONGO_PASS, ""),
						EnvKeyHelper.get(MONGO_PASS, ""));
		/*
		* ===============================================================================================================
		*/
//		String databaseName = EnvKeyHelper.get(MONGO_DB, "admin"), mongoHost = EnvKeyHelper.get(MONGO_HOST, "localhost");
		String databaseName = EnvKeyHelper.get(MONGO_DB, "cef"), mongoHost = EnvKeyHelper.get(MONGO_HOST, "localhost");
		String mongoPort = EnvKeyHelper.get(MONGO_PORT, MONGO_PORT_DEFAULT);
		// Set credentials
		MongoCredential credential = MongoCredential.createCredential(user, databaseName, pass.toCharArray());
		ServerAddress serverAddress = new ServerAddress(mongoHost, Integer.valueOf(mongoPort));
		
		MongoClient client;
		if (credential == null || user.isEmpty() || pass.isEmpty()) {
			LOGGER.info("mongoDbFactory.mongoClient.withoutCredentials");
			client = new MongoClient(serverAddress, mongoClientOptions());
		} else {
			client = new MongoClient(serverAddress, credential, mongoClientOptions());
		}
		return client;
		
	}

	@Bean
	public MongoClientOptions mongoClientOptions() {
		String timeoutMongo = EnvKeyHelper.get(MONGO_SOCKET_TIMEOUT, "1000");
		MongoClientOptions options = MongoClientOptions.builder()
				.socketTimeout(Integer.parseInt(timeoutMongo))
				.connectTimeout(Integer.parseInt(timeoutMongo))
				.serverSelectionTimeout(Integer.parseInt(timeoutMongo))
				.build();
		return options;
	}
	
}
