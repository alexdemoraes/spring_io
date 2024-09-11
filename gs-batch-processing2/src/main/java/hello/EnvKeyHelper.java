package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class EnvKeyHelper {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EnvKeyHelper.class);
	private static final String PATH_SECRET_DEFAULT = "/etc/secret/";
	private static final String GRAVAR_B9 = "GRAVAR_B9";
	private static Map<String, String> map = new HashMap<>();
	
	static {
		initMap(GRAVAR_B9);
	}
	
	public static String get(final String envVar, final String defaultValue) {
		String response = "";
		try{
			String _rp = getMap(envVar);
			if (_rp != null && !_rp.isEmpty()){
				return _rp;
			}
			response = System.getenv(envVar);
			response = response != null ? response : "";
			if (response.isEmpty()){
				response = System.getProperty(envVar);
			}
			LOGGER.info(String.format("======================EnvKeyHelper/get => %s && withPath => %s", envVar, response));
		}catch(RuntimeException e){
			LOGGER.error("get",e);
		}
		if ((response == null || response.isEmpty())
		&&	(defaultValue != null)){
			response = defaultValue;
		}
		addMap(envVar, response);
		return response;
	}
	
	private static void addMap(String key, String value){
		try{
			map.put(key, value);
		}catch(Exception e){
			LOGGER.error("addMap", e);
		}
	}
	
	private static void initMap(String key) {
		try{
			if (getMap(key) == null){
				map.put(key, get(key, "false"));
			}
		}catch(Exception e){
			LOGGER.error("config", e);
		}
	}

	private static String getMap(String envVar) {
		try{
			return map.get(envVar);
		}catch(Exception e){
			LOGGER.error("getMap",e);
		}
		return "";
	}

	public static String secretFileWithPathDefault(String file, String defaultValue) {
		String pathSecret = get("PATH_SECRET", PATH_SECRET_DEFAULT);
		String lastChar = pathSecret.substring(pathSecret.length()-1), _lastChar="";
		if (!lastChar.equals("/")&&!lastChar.equals("\\")){
			_lastChar="/";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(pathSecret);
		sb.append(_lastChar);
		sb.append(file);
		String _file = sb.toString();
		return secretFile(_file, defaultValue);
	}
	
	public static String secretFile(String file, String defaultValue){
		String response = "";
		try{
			LOGGER.info(String.format("======================EnvKeyHelper/secretFile => %s", file));
		    String line =null;
		    Optional<String> lines  =Files.lines(Paths.get(file)).findFirst();
		    if(lines.isPresent())
		        line = lines.get();
		 
		    if (line != null){
		    	response = line;
		    }
		    
		}catch(RuntimeException | IOException e){
			LOGGER.error(e.getMessage(), e);
		}
		if ((response == null || response.isEmpty())
		&&	(defaultValue != null)){
			response = defaultValue;
		}
		return response;
	}
	
	public static List<String> getCollectionsFiles(ClassLoader cl){
		return getFilenames(cl);
	}
	
	private static List<String> getFilenames(ClassLoader cl){
		List<String> domainFiles = new ArrayList<>();
		try{
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
			Resource[] resources = resolver.getResources("classpath:domains/*.json") ;
			for (Resource resource: resources){
				String fname = resource.getFilename();
				LOGGER.info("filename : {}", fname);
				domainFiles.add(fname.replaceAll(".json", ""));
			}
		}catch(IOException e){
			LOGGER.error("getFilenames", e);
		}
		return domainFiles;
	}
	
	public static String getLoadFileFromDomain(String domain){
		return getStringFromInputStream(getFile("domains/"+domain+".json"));
	}

	public static InputStream getFile(String fileName){
		try{
			Resource resource = new ClassPathResource(fileName);
			LOGGER.info(fileName);
			return resource.getInputStream();

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getStringFromInputStream(InputStream resourceInputStream) {
		StringBuilder response = new StringBuilder();
		if (resourceInputStream == null){
			return "";
		}
		BufferedReader br = null;

		String line = " ";
		try {
			br = new BufferedReader(new InputStreamReader(resourceInputStream));
			while ((line = br.readLine()) != null) {
				response.append(line);
				response.append(" ");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		LOGGER.info(response.toString());
		return response.toString();
	}
	
	public static boolean permissionToWriteInDb(){
		return "true".equalsIgnoreCase(get(GRAVAR_B9, "false"));
	}
}