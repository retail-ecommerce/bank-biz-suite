package com.doublechain.bank.accountchange;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechain.bank.BankObjectPlainCustomSerializer;
public class AccountChangeSerializer extends BankObjectPlainCustomSerializer<AccountChange>{

	@Override
	public void serialize(AccountChange accountChange, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, accountChange, provider);
		
	}
}


