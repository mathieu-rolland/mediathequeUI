package com.perso.serializer;

import java.lang.reflect.Type;

import com.api.allocine.decod.IDecoder;
import com.api.allocine.factory.IFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InterfaceSerializer<T> implements JsonSerializer<T>{

	private IFactory factory;
	private IDecoder decoder;
	
	public InterfaceSerializer(IFactory factory, IDecoder decoder){
		this.factory = factory;
		this.decoder = decoder;
	}
	
	@Override
	public JsonElement serialize(T object, Type type, JsonSerializationContext arg2) {
		Gson gson = decoder.getGson();
		return gson.toJsonTree( factory.create(type).getClass().cast( object ) );
	}

}
