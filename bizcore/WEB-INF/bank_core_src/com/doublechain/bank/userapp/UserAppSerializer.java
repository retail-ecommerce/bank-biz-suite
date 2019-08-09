package com.doublechain.bank.userapp;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechain.bank.BankObjectPlainCustomSerializer;
public class UserAppSerializer extends BankObjectPlainCustomSerializer<UserApp>{

	@Override
	public void serialize(UserApp userApp, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, userApp, provider);
		
	}
}


