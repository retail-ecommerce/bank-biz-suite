package com.doublechain.bank.userdomain;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechain.bank.BankObjectPlainCustomSerializer;
public class UserDomainSerializer extends BankObjectPlainCustomSerializer<UserDomain>{

	@Override
	public void serialize(UserDomain userDomain, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, userDomain, provider);
		
	}
}


