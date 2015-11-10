package com.geekylab.general.network.api;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/11/15.
 */
public class CustomRequest extends Request<JSONObject> {
    private Map<String, String> mheaders;
    private Response.Listener<JSONObject> mlistener;
    private Map<String, String> mparams;

    public CustomRequest(int method,
                         String url,
                         Response.Listener<JSONObject> listener,
                         Response.ErrorListener errorListener,
                         Map<String, String> params,
                         Map<String, String> headers) {
        super(method, url, errorListener);
        mlistener = listener;
        mheaders = headers;
        mparams = params;
    }

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return mparams;
    };

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        if(mheaders!=null) {
            headers.putAll(mheaders);
        }
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
//        request.setRetryPolicy(new DefaultRetryPolicy(60*1000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mlistener.onResponse(response);
    }
}