package com.doublechain.bank.objectaccess;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechain.bank.BankObjectPlainCustomSerializer;
public class ObjectAccessSerializer extends BankObjectPlainCustomSerializer<ObjectAccess>{

	@Override
	public void serialize(ObjectAccess objectAccess, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, objectAccess, provider);
		
	}
}


