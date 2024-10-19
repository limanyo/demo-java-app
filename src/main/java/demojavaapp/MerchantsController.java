package demojavaapp;

import demojavaapp.io.CreateMerchantRequest;
import demojavaapp.io.CreateMerchantResponse;
import demojavaapp.io.GetMerchantOrdersResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/merchants")
public class MerchantsController {

    private final Database database;

    public MerchantsController() {
        this.database = Database.INSTANCE;
    }

    @PostMapping
    public CreateMerchantResponse create(CreateMerchantRequest request) {
        final Merchant merchant = this.database.createMerchant(request.name());
        return new CreateMerchantResponse(merchant.id());
    }

    @GetMapping(path = "/{merchantId}/orders")
    public GetMerchantOrdersResponse getOrders(@RequestHeader("Merchant-ID") int merchantId) {
        return new GetMerchantOrdersResponse(this.database.findMerchantOrders(merchantId));
    }
}
