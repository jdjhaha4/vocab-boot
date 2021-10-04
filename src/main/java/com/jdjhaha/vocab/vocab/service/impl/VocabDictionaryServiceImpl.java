package com.jdjhaha.vocab.vocab.service.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public List<HashMap<Object, Object>> requestData(HashMap<String, String> paramMap) {
		String vocab = paramMap.get("vocab");
		log.info(vocab);
		
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
			log.info(result);
			HashMap<Object, Object> vocabDicParam = new HashMap<Object, Object>();
			vocabDicParam.put("vocab", vocab);
			vocabDicParam.put("vocab_dic_json", result);
			vocabDicMapper.insertData(vocabDicParam);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}

}
