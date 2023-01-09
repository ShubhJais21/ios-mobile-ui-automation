package api.customer;

import com.rapido.api.data.Captain;
import com.rapido.api.data.Customer;
import com.rapido.api.db.DbHelper;
import com.rapido.api.endpoints.auth.AuthClient;
import com.rapido.api.endpoints.captain.clients.CaptainClient;
import com.rapido.api.endpoints.services.ordermanagement.OrderManagementClient;
import com.rapido.api.endpoints.services.ordermanagement.orders.OrderResponse;
import com.rapido.api.utils.RedisUtil;
import com.rapido.api.utils.ReporterUtil;
import org.testng.Assert;

import static org.apache.http.HttpStatus.SC_OK;

public class CustomerAction {
    private AuthClient authClient;
    private RedisUtil redisUtil;
    private OrderManagementClient orderManagementClient;
    private Customer customer;
    private Captain captain;

    private String orderId;


    public CustomerAction() {
        this.redisUtil = new RedisUtil();
        redisUtil.connect();
        this.authClient = new AuthClient();
        this.orderManagementClient=new OrderManagementClient();
        this.customer=new Customer();
        this.captain=new Captain();
    }

//    public void cancelLastCustomerOrder(String mobileNumber){
//        OrderResponse orderResponse = orderManagementClient.getOrder(orderId);
//        Assert.assertEquals(orderResponse.getData().getStatus(), "new");
//        Assert.assertEquals(orderResponse.getData().getOrderType(), "app");
//        Assert.assertEquals(orderResponse.getData().getMapRiders().get(0), captain.getId());
//
//        orderId=DbHelper.getCusLatestOrderId(mobileNumber);
//        ReporterUtil.log("OrderId: "+orderId);
//        String userId=DbHelper.getRiderId(mobileNumber);
//        ReporterUtil.log("UserId: "+userId);
//
//        orderResponse = orderManagementClient.cancelOrder(orderId, userId , "cancelled", "Changed my mind");
//        Assert.assertEquals(orderResponse.getStatus(), SC_OK);
//    }
}
