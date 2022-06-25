package com.jdjhaha.vocab.vocab.service.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jdjhaha.vocab.vocab.mapper.VocabDicMapper;
import com.jdjhaha.vocab.vocab.service.VocabDictionaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VocabDictionaryServiceImpl implements VocabDictionaryService {
	@Autowired
	private VocabDicMapper vocabDicMapper;
	
	@Override
	public HashMap<Object, Object> selectData(HashMap<String, String> paramMap) {
		return vocabDicMapper.selectData(paramMap);
	}

	@Override
	public List<HashMap<Object, Object>> requestData(HashMap<String, String> paramMap) {
		String vocab = paramMap.get("vocab");
		String username = paramMap.get("username");
		//log.info(vocab);
		
		RestTemplate restTemplate = new RestTemplate();

        HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<>();
        httpMessageConverter.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(httpMessageConverter);

        URI targetUrl= UriComponentsBuilder.fromUriString("https://api.dictionaryapi.dev")
                .path("api/v2/entries/en/"+vocab)
                .build()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
        httpHeaders.setContentType(new MediaType("applicaton", "json", StandardCharsets.UTF_8));

        HttpEntity<String> httpEntity = new HttpEntity<String>("param", httpHeaders);
        ResponseEntity<String> responseEntity;
        
        try {
			responseEntity = restTemplate.exchange(targetUrl.toURL().toString(), HttpMethod.GET, httpEntity, String.class);
			String result = responseEntity.getBody();
			
			HashMap<Object, Object> vocabDicParam = new HashMap<Object, Object>();
			vocabDicParam.put("vocab", vocab);
			vocabDicParam.put("vocab_dic_json", result);
			vocabDicParam.put("username", username);
			
			HashMap<String, String> dicMap = new HashMap<>();
			dicMap.put("vocab", vocab);
			dicMap.put("username", username);
			
			HashMap<Object, Object> dicSelectData = selectData(dicMap);
			if(dicSelectData == null) {
				vocabDicMapper.insertData(vocabDicParam);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public HashMap<String, String> requestDataMacMillan(HashMap<String, String> paramMap) {
		
		String vocab = paramMap.get("vocab");
		String username = paramMap.get("username");
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String audio = "";
		String text = vocab;
		
		//String url = "https://www.macmillandictionary.com/dictionary/american/"+vocab;
		String url = "https://www.macmillandictionary.com/search/american/direct/?q="+vocab.replace(' ', '+');
		Connection conn = Jsoup.connect(url);
		
		Document document = null;
		try {
			document = conn.get();
			//data-src-mp3  [attr]    
			//<span class="PRON"><span class="SEPPRON-before"> /</span>ˌʌnəˈveɪləb(ə)l<span class="SEPPRON-after">/</span></span>
			Element element = document.selectFirst("[data-src-mp3]");
			audio=element.attr("data-src-mp3");
			
			Element before = document.selectFirst(".SEPPRON-before");
			Element after = document.selectFirst(".SEPPRON-after");
			if(before != null && after != null) {
				before.remove();
				after.remove();
			}
			Element pron = document.selectFirst(".PRON");
			if(pron != null) {
				text = "/"+document.selectFirst(".PRON").html()+"/";
			}
			
			JSONArray dicArr = new JSONArray();
			JSONObject wordObj = new JSONObject();
			wordObj.put("word", vocab);
			JSONArray phoneticsArr = new JSONArray();
			JSONObject phoneticObj = new JSONObject();
			phoneticObj.put("text", text);
			phoneticObj.put("audio", audio);
			phoneticsArr.put(phoneticObj);
			wordObj.put("phonetics", phoneticsArr);
			dicArr.put(wordObj);
			
			HashMap<Object, Object> vocabDicParam = new HashMap<Object, Object>();
			vocabDicParam.put("vocab", vocab);
			vocabDicParam.put("vocab_dic_json", dicArr.toString());
			vocabDicParam.put("username", username);
			
			HashMap<String, String> dicMap = new HashMap<>();
			dicMap.put("vocab", vocab);
			dicMap.put("username", username);
			
			HashMap<Object, Object> dicSelectData = selectData(dicMap);
			if(dicSelectData == null) {
				vocabDicMapper.insertData(vocabDicParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultMap.put("audio", audio);
		resultMap.put("text", text);
		
		return resultMap;
	}

}
