package ec.com.android.comercio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import ec.com.android.comercio.type.Tienda;

public class ProductoActivity extends AppCompatActivity {

    private TextView lblSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        lblSaludo=(TextView) findViewById(R.id.lblSaludo);
        Bundle bundle=getIntent().getExtras();
        if(null!=bundle){
            Tienda tienda=bundle.getParcelable("tienda");
            lblSaludo.setText("Bienvenido a la tienda  "+tienda.getNombre());
        }else{
            lblSaludo.setText("No se encontro tienda...!");
        }

    }
}