package com.expanse.app.payoneer.utils;

import com.expanse.app.payoneer.model.ListResult;
import com.google.gson.Gson;

public class DummyData {

	public final static String errorMessage = "An error occurred";
	private final static String jsonItem = "{ \"returnCode\": { \"name\": \"OK\", \"source\": \"GATEWAY\" }, \"status\": { \"code\": \"listed\", \"reason\": \"listed\" }, \"interaction\": { \"code\": \"PROCEED\", \"reason\": \"OK\" }, \"identification\": { \"longId\": \"6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it\", \"shortId\": \"05753-51161\", \"transactionId\": \"20678\" }, \"networks\": { \"applicable\": [ { \"code\": \"AMEX\", \"label\": \"American Express\", \"method\": \"CREDIT_CARD\", \"grouping\": \"CREDIT_CARD\", \"registration\": \"OPTIONAL\", \"recurrence\": \"NONE\", \"redirect\": false, \"links\": { \"logo\": \"https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png\", \"self\": \"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX\", \"lang\": \"https://resources.integration.oscato.com/resource/lang/MOBILETEAM/en_US/AMEX.json\", \"operation\": \"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX/charge\", \"validation\": \"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/MOBILETEAM/en_US/AMEX/standard/validate\" }, \"selected\": false, \"operationType\": \"CHARGE\" } ] }, \"operationType\": \"CHARGE\", \"payment\": { \"reference\": \"Mobile Team Exercise\", \"amount\": 13, \"currency\": \"EUR\" } }";

	public static ListResult getDummyData() {
		return new Gson().fromJson(jsonItem, ListResult.class);
	}
}