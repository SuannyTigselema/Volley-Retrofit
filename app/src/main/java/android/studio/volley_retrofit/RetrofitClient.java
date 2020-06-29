package android.studio.volley_retrofit;

import android.studio.volley_retrofit.Interfaces.LoginRetrofit;

import retrofit2.Retrofit;

public class RetrofitClient {
    private static final String BASE_URL = "http://uealecpeterson.net/ws/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }

    public LoginRetrofit getLoginRetrofit(){
        return retrofit.create(LoginRetrofit.class);
    }
}
