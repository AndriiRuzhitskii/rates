package com.example;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.coyote.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.models.Rate;
import com.example.models.RateDao;
import com.example.services.RateService;
import com.example.services.RateServiceImpl;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class RateServiceMockTest {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	private RateService service;
	
	@Autowired
	private RateDao repository;
		
	@Before
	public void setup() {
		log.info("Before");
		
		repository.deleteAll();
		service = mock(RateServiceImpl.class);
		service.setRepository(repository);
	}
	
	@Test
	public void testGetAllRatesByDate() {
		log.info("Test");

//		when(service.getRateByDate("20160801", "USD")).thenReturn(new Rate((short)840, "Долар США", (float)24.806543, "USD", "01.08.2016"));
//		when(service.getRateByDate("20160802", "USD")).thenReturn(new Rate((short)840, "Долар США", (float)24.783525, "USD", "02.08.2016"));
//		
//		when(service.getRateByDate("20160801", "EUR")).thenReturn(new Rate((short)978, "Євро", (float)27.567513, "EUR", "01.08.2016"));
//		when(service.getRateByDate("20160802", "EUR")).thenReturn(new Rate((short)978, "Євро", (float)27.668327, "EUR", "02.08.2016"));
//		
//		when(service.getRateByDate("20160801", "RUB")).thenReturn(new Rate((short)643, "Російський рубль", (float)0.36996, "RUB", "01.08.2016"));
//		when(service.getRateByDate("20160802", "RUB")).thenReturn(new Rate((short)643, "Російський рубль", (float)0.37576, "RUB", "02.08.2016"));
//		
//		when(service.getRateByDate("20160801", "XAU")).thenReturn(new Rate((short)959, "Золото", (float)33054.72, "XAU", "01.08.2016"));
//		when(service.getRateByDate("20160802", "XAU")).thenReturn(new Rate((short)959, "Золото", (float)33449.086, "XAU", "02.08.2016"));
//		
//		when(service.getRateByDate("20160801", "XAG")).thenReturn(new Rate((short)961, "Срiбло", (float)497.123, "XAG", "01.08.2016"));
//		when(service.getRateByDate("20160802", "XAG")).thenReturn(new Rate((short)961, "Срiбло", (float)508.31, "XAG", "02.08.2016"));
		
		LocalDate start = LocalDate.parse("2016-08-01"), // 1998-01-01
		end = LocalDate.parse("2016-08-02");
		
		when(service.getAllRatesByDate(start, end, "USD")).thenCallRealMethod();
		when(service.getAllRatesByDate(start, end, "EUR")).thenCallRealMethod();
		when(service.getAllRatesByDate(start, end, "RUB")).thenCallRealMethod();
		when(service.getAllRatesByDate(start, end, "XAU")).thenCallRealMethod();
		when(service.getAllRatesByDate(start, end, "XAG")).thenCallRealMethod();
		
		assertEquals(2,service.getAllRatesByDate(start, end, "USD").size());
		assertEquals(2,service.getAllRatesByDate(start, end, "EUR").size());
		assertEquals(2,service.getAllRatesByDate(start, end, "RUB").size());
		assertEquals(2,service.getAllRatesByDate(start, end, "XAU").size());
		assertEquals(2,service.getAllRatesByDate(start, end, "XAG").size());
	}
	
}
