package android.studio.volley_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue= Volley.newRequestQueue(MainActivity.this);
        ListarBancos();
    }

    public void Ingresar(View view)
    {
        Intent intent = new Intent(MainActivity.this,ValidaLogin.class);
        EditText txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        EditText txtClave=(EditText) findViewById(R.id.txtClave);

        Bundle b = new Bundle();
        b.putString("user",txtUsuario.getText().toString());
        b.putString("clave",txtClave.getText().toString());

        intent.putExtras(b);

        startActivity(intent);
    }
    public void ListarBancos()
    {
        final TextView ListaBancos=(TextView)findViewById(R.id.txtBancos);
        String url="https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
         new Response.Listener<JSONArray>(){
             @Override
             public void onResponse(JSONArray response) {
                // ListaBancos.setText(response.toString());
                 String result=response.toString();
                 String lstBancos="";
                 try {
                     JSONArray JSONLista = new JSONArray(result);
                     for (int i = 0; i < JSONLista.length(); i++) {
                         JSONObject banco = JSONLista.getJSONObject(i);
                         lstBancos = lstBancos + "\n" + banco.getString("code").toString() + " " + banco.getString("name").toString();

                     }
                 }catch(JSONException e) {
                     e.printStackTrace();
                 }
                 ListaBancos.setText("Respuesta Lista Bancos:"+lstBancos);
             }
         }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ListaBancos.setText(error.toString());
                }
            }){
            @Override
            public Map getHeaders() throws AuthFailureError{
                HashMap headers = new HashMap();
                headers.put("Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
                return headers;
            }
        };;

        queue.add(request);
    }
}
