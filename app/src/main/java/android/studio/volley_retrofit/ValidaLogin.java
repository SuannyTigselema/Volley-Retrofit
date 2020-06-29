package android.studio.volley_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.studio.volley_retrofit.Interfaces.LoginRetrofit;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import android.studio.volley_retrofit.Models.usuario;
import android.widget.Toast;

import java.io.IOException;

public class ValidaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_login);

        final TextView txtResultado=(TextView) findViewById(R.id.txtResult);

        //Para los parametros de la activity anterior
        Bundle b = this.getIntent().getExtras();

        //Instancia de retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://uealecpeterson.net/ws/")
                .build();

        //Llamar a la interfaz
        LoginRetrofit loginRetrofit = retrofit.create(LoginRetrofit.class);

        //Enviar parametros a la interfaz
         Call<ResponseBody> call = loginRetrofit.Login(b.getString("user"), b.getString("clave"));

        //Resultado del retrofit
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s=response.body().string();
                    //Toast.makeText(ValidaLogin.this,s,Toast.LENGTH_LONG).show();
                    txtResultado.setText(s);
                } catch (IOException e) {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ValidaLogin.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}

