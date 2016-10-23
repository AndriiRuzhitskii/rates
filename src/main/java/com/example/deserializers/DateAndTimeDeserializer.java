package com.example.deserializers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.boot.json.JsonParser;
import org.springframework.expression.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateAndTimeDeserializer extends
		JsonDeserializer<java.time.LocalDate> {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public java.time.LocalDate deserialize(
			com.fasterxml.jackson.core.JsonParser arg0,
			DeserializationContext arg1) throws IOException,
			JsonProcessingException {
		String str = arg0.getText().trim();

		return LocalDate.parse(str, formatter);

	}

}