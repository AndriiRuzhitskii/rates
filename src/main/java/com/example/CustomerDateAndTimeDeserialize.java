package com.example;

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

public class CustomerDateAndTimeDeserialize extends JsonDeserializer<java.time.LocalDate> {

//    private SimpleDateFormat dateFormat = new SimpleDateFormat(
//            "yyyy-MM-dd HH:mm:ss");

    DateTimeFormatter formatter =
    		DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    formatter = formatter.withLocale( putAppropriateLocaleHere ); 
//    LocalDate date = LocalDate.parse("2005-nov-12", formatter);
    
	@Override
	public java.time.LocalDate deserialize(com.fasterxml.jackson.core.JsonParser arg0,
			DeserializationContext arg1) throws IOException,
			JsonProcessingException {
        String str = arg0.getText().trim();
        
            //				return dateFormat.parse(str);
			return LocalDate.parse(str, formatter);
//			return null;
      
//        return arg1.parseDate(str);
	}

//    @Override
//    public Date deserialize(JsonParser paramJsonParser,
//            DeserializationContext paramDeserializationContext)
//            throws IOException, JsonProcessingException {
//        String str = paramJsonParser.getText().trim();
//        try {
//            return dateFormat.parse(str);
//        } catch (ParseException e) {
//
//        }
//        return paramDeserializationContext.parseDate(str);
//    }

}