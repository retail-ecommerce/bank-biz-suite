package com.doublechain.bank.namechangeevent;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechain.bank.BankObjectPlainCustomSerializer;
public class NameChangeEventSerializer extends BankObjectPlainCustomSerializer<NameChangeEvent>{

	@Override
	public void serialize(NameChangeEvent nameChangeEvent, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, nameChangeEvent, provider);
		
	}
}


