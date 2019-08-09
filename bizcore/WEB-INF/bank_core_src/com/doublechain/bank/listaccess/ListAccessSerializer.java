package com.doublechain.bank.listaccess;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechain.bank.BankObjectPlainCustomSerializer;
public class ListAccessSerializer extends BankObjectPlainCustomSerializer<ListAccess>{

	@Override
	public void serialize(ListAccess listAccess, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, listAccess, provider);
		
	}
}


