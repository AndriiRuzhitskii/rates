package com.example;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.models.Rate;
import com.example.models.RateDao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class RateControllerTest {

	private MockMvc mockMvc;
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	 private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(),
	            Charset.forName("utf8"));
	 
	@Autowired
	private RateDao repository;

	@Autowired
    private WebApplicationContext webApplicationContext;
		
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
    @Before
    public void setup() {
    	this.mockMvc = webAppContextSetup(webApplicationContext).build();
    	repository.deleteAll();
//    	repository.save(new Rate((short)840,"Долар США",(float)24.806543,"USD","01.08.2016"));
//    	repository.save(new Rate((short)978,"Євро",(float)27.668327,"EUR","02.08.2016"));
//    	repository.save(new Rate((short)643,"Російський рубль",(float)0.37115,"RUB","03.08.2016"));
//    	repository.save(new Rate((short)959,"Золото",(float)33722.473,"XAU","04.08.2016"));
//    	repository.save(new Rate((short)961,"Срiбло",(float)500.151,"XAG","05.08.2016"));    	
    }
    
	@Test
	public void testGetRate() throws Exception {
        mockMvc.perform(get("/rate")
                .contentType(contentType))
                .andExpect(status().isOk())
                //Rate [id=1, r030=840, txt=Долар США, rate=24.806543, cc=USD, exchangedate=01.08.2016]
                .andExpect(jsonPath("$[0].r030", is(840)))
                .andExpect(jsonPath("$[0].txt", is("Долар США")))
                .andExpect(jsonPath("$[0].rate", is(24.806543)))
                .andExpect(jsonPath("$[0].cc", is("USD")))
                .andExpect(jsonPath("$[0].exchangedate", is("01.08.2016")))
                //Rate [id=40, r030=978, txt=Євро, rate=27.668327, cc=EUR, exchangedate=02.08.2016]
                .andExpect(jsonPath("$[1].r030", is(978)))
                .andExpect(jsonPath("$[1].txt", is("Євро")))
                .andExpect(jsonPath("$[1].rate", is(27.668327)))
                .andExpect(jsonPath("$[1].cc", is("EUR")))
                .andExpect(jsonPath("$[1].exchangedate", is("02.08.2016")))
                //Rate [id=79, r030=643, txt=Російський рубль, rate=0.37115, cc=RUB, exchangedate=03.08.2016]
                .andExpect(jsonPath("$[2].r030", is(643)))
                .andExpect(jsonPath("$[2].txt", is("Російський рубль")))
                .andExpect(jsonPath("$[2].rate", is(0.37115)))
                .andExpect(jsonPath("$[2].cc", is("RUB")))
                .andExpect(jsonPath("$[2].exchangedate", is("03.08.2016")))
                //Rate [id=118, r030=959, txt=Золото, rate=33722.473, cc=XAU, exchangedate=04.08.2016]
                .andExpect(jsonPath("$[3].r030", is(959)))
                .andExpect(jsonPath("$[3].txt", is("Золото")))
                .andExpect(jsonPath("$[3].rate", is(33722.473)))
                .andExpect(jsonPath("$[3].cc", is("XAU")))
                .andExpect(jsonPath("$[3].exchangedate", is("04.08.2016")))
                //Rate [id=157, r030=961, txt=Срiбло, rate=500.151, cc=XAG, exchangedate=05.08.2016]
                .andExpect(jsonPath("$[4].r030", is(961)))
                .andExpect(jsonPath("$[4].txt", is("Срiбло")))
                .andExpect(jsonPath("$[4].rate", is(500.151)))
                .andExpect(jsonPath("$[4].cc", is("XAG")))
                .andExpect(jsonPath("$[4].exchangedate", is("05.08.2016")));
	}
	
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
