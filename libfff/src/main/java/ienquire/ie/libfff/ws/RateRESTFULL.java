package ienquire.ie.libfff.ws;

import com.google.gson.*;

import ienquire.ie.libfff.model.*;
import ienquire.ie.libfff.util.*;

/**
 * Created by barryoreilly on 13/10/15.
 */
public class RateRESTFULL {

    private Gson gson = new Gson();

    public boolean add(Rate rate) throws Exception {
        String clientJSON = gson.toJson(rate);
        String[] response = HttpClientSingleton.post(FFFUtil.WS_URL + "rate/add", clientJSON);
        return response[1].equals("OK");
    }

}
