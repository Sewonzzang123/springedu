package com.kh.portfolio.openapi;

import java.io.BufferedInputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger 
		= LoggerFactory.getLogger("MovieController.class");

	@GetMapping
	public String movie() {

		return "/openapi/rank_movie";
	}

	@GetMapping(value="/rank/{day}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> rank(
			@PathVariable(value="day") long day
			){
		ResponseEntity<String> resp = null;

		BufferedInputStream reader = null;
		StringBuffer sb = new StringBuffer();

		URL url = null;
		//url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt="+day);
		String reqUrl ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		String key = "430156241533f1d058c603178cc3ca0e";
		String targetDt = String.valueOf(day);
		String uri = reqUrl +"?key="+key+"&targetDt="+day;
	
		try {
			url = new URL(uri);
			
			reader = new BufferedInputStream(url.openStream());
			int i=0;
			byte[] b = new byte[4096];
			while((i=reader.read(b)) != -1) {
				sb.append(new String(b,0,i));
			}
			logger.info(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp = new ResponseEntity<String>(sb.toString(),HttpStatus.OK);
		return resp;
	}

}