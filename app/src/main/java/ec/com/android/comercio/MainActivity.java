package ec.com.android.comercio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ec.com.android.comercio.type.Tienda;
import ec.com.android.comercio.util.ConstantesComercio;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ListView listViewTiendas;
    private List<Tienda> tiendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tiendas=new ArrayList<>();
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, ConstantesComercio.URL + "/tienda", null, new Response.Listener<JSONArray>() {
                @Override
                    public void onResponse(JSONArray response) {
                    for (int i=0;i<response.length();i++){
                        try {
                            System.out.println("Respuesta de servicio==> "+response);
                            JSONObject jo= (JSONObject) response.get(i);
                            tiendas.add(
                                    new Tienda(jo.getLong("id"),
                                            jo.getString("codigo"),
                                            jo.getString("nombre"),
                                            jo.getString("direccion"),
                                            jo.getString("correo"),
                                            jo.getString("telefono"))
                            );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(MainActivity.this,response.toString() +"==>"+tiendas.size(),Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"No se encontro el servicio ...!",Toast.LENGTH_LONG).show();
                }
            });
            queue.add(jsonArrayRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        /**/
        List<String>lstTiendas=new ArrayList<>();
        for (Tienda t:tiendas){
            lstTiendas.add(t.getNombre());
        }
        listViewTiendas=(ListView) findViewById(R.id.listViewTiendas);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lstTiendas);
        listViewTiendas.setAdapter(adapter);

        button=(Button) findViewById(R.id.btnSaludar);
        button.setOnClickListener(view -> {
            Intent intent=new Intent(this,ProductoActivity.class);
            intent.putExtra("tienda",tiendas.get(2));
            startActivity(intent);
        });
    }



}