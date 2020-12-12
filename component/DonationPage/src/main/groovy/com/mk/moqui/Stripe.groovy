package com.mk.moqui;
import org.moqui.context.ExecutionContext;
import java.util.HashMap;
import java.util.Map;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

class StripeHandler {
    public static void getDonation(ExecutionContext ec) {
        Stripe.apiKey = "sk_test_51HumgNAdtIHPrxZ54C8e1bvcbuz1NSEBwpIf5Dd4gC63HrDbOcbDglj9l5fD2P9WOei7X19meQLI6joOa9p6ybcw00boWsARNM";
        Map<String, Object> params = new HashMap<String, Object>();

        // store donation data in db
        ec.entity.makeValue("Donation").setAll(ec.context).setSequencedIdPrimary().create();

        params.put("amount", Integer.parseInt(ec.context.amount));
        params.put("currency", "usd");
        params.put("source", "tok_visa");
        params.put("receipt_email", ec.context.email);
        Charge charge = Charge.create(params);
    }
}