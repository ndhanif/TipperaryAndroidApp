package ienquire.ie.libfff.ws;

import com.google.gson.*;

import ienquire.ie.libfff.model.*;
import ienquire.ie.libfff.util.*;

/**
 * Created by barryoreilly on 06/10/15.
 */
public class CustomerRESTFULL {

    private Gson gson = new Gson();

    public boolean add(Customer customer) throws Exception {
        String clientJSON = gson.toJson(customer);
        String[] response = HttpClientSingleton.post(FFFUtil.WS_URL + "customer/add", clientJSON);
        return response[1].equals("OK");
    }

    public boolean checkCustomer(String token) throws Exception {
        String[] response = HttpClientSingleton.get(FFFUtil.WS_URL + "customer/get/" + token);
        return response[1].contains("yes");
    }

}