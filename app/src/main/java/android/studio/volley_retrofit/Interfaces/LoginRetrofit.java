package android.studio.volley_retrofit.Interfaces;

import android.studio.volley_retrofit.Models.login;
import android.studio.volley_retrofit.Models.usuario;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRetrofit {

    @POST("login.php")
    Call<ResponseBody> Login(
            @Query("usr") String usr,
            @Query("pass") String pass
            );

}
