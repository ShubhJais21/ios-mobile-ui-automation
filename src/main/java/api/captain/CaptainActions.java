package api.captain;

import com.rapido.api.captain.entities.Info;
import com.rapido.api.customer.entities.Location;
import com.rapido.api.customer.entities.ServiceType;
import com.rapido.api.data.Captain;
import com.rapido.api.endpoints.auth.AuthClient;
import com.rapido.api.endpoints.auth.login.LoginResponse;
import com.rapido.api.endpoints.captain.acceptReject.AcceptRejectResponse;
import com.rapido.api.endpoints.captain.clients.CaptainClient;
import com.rapido.api.endpoints.captain.location.LocationMessageResponse;
import com.rapido.api.endpoints.captain.orderStatus.OrderStatusResponse;
import com.rapido.api.endpoints.captain.riderStatus.RiderStatusMessageResponse;
import com.rapido.api.utils.RedisUtil;
import com.rapido.api.utils.ReporterUtil;
import io.qameta.allure.Step;
import org.testng.Assert;
import retrofit2.Response;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class CaptainActions {

    private AuthClient authClient;
    private Captain captain;
    private CaptainClient captainClient;
    private RedisUtil redisUtil;

    private String orderId;


    public CaptainActions(Captain captain) {
        this.redisUtil = new RedisUtil();
        redisUtil.connect();

        this.authClient = new AuthClient();
        this.captain= captain;
        this.captainClient= new CaptainClient(captain);
    }

    @Step("Ensure Captain is Available at Customer Location")
    public void ensureCaptainIsAvailableAtSameLocation(Location pickupLocation, ServiceType serviceType){
        captain.setLocation(pickupLocation);

        LoginResponse captainLoginResponse = authClient.loginCaptain(captain);
        Assert.assertEquals(captainLoginResponse.getStatus(), SC_OK);
        captain.setId(captainLoginResponse.getProfile().get_id());
        captain.setAccessToken(captainLoginResponse.getToken());

        RiderStatusMessageResponse riderStatusMessageResponse = captainClient.setCaptainOnline();
        Assert.assertEquals(riderStatusMessageResponse.getStatus(), SC_OK);
        ReporterUtil.log("CaptainId:"+captain.getId());
        LocationMessageResponse locationMessageResponse = captainClient.updateLocation(serviceType);
        Assert.assertEquals(locationMessageResponse.getStatus(), SC_OK);

    }

    @Step("Captain Accept Link Ride")
    public void acceptRide(){
        // write code to fetch orderId for captain
        String orderId ="";

        AcceptRejectResponse acceptResponse = captainClient.captainAcceptRide(orderId, ServiceType.RAPIDO_TEST);
        assertEquals(acceptResponse.getStatus(), SC_OK);
        this.orderId=acceptResponse.getOrderId();
    }

    @Step("Captain Accept Auto Ride")
    public void acceptAutoRide(){
        // write code to fetch orderId for captain
        String orderId ="";

        AcceptRejectResponse acceptResponse = captainClient.captainAcceptRide(orderId, ServiceType.RAPIDO_AUTO_BLR);
        assertEquals(acceptResponse.getStatus(), SC_OK);
        this.orderId=acceptResponse.getOrderId();
    }

    @Step("Captain Arrive at the location")
    public void arriveAtLocation(String customerId){

        OrderStatusResponse statusResponse = captainClient.captainArrives(customerId, orderId);
        assertEquals(statusResponse.getStatus(), SC_OK);

    }

    @Step("Captain Start Ride")
    public void startRide(String customerId, String pin){
        OrderStatusResponse statusResponse = captainClient.captainStartRide(customerId,orderId, pin);
        assertEquals(statusResponse.getStatus(), SC_OK);

    }

    @Step("Captain Complete Ride")
    public void completeRide(String customerId, Location dropLocation){
        OrderStatusResponse statusResponse = captainClient.captainCompletesRide(customerId, orderId, dropLocation);
        assertEquals(statusResponse.getStatus(), SC_OK);

    }

    @Step("Captain Complete Ride")
    public void collectCash(){
        Response<Info> statusResponse = captainClient.captainCashCollected(orderId);
        Assert.assertEquals(statusResponse.code(), 200);
    }
}
