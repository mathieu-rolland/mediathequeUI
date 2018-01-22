package com.mediatheque.serializer;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<Date> {


	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
		return date == null ? null : new JsonPrimitive(date.getTime());
	}

}
