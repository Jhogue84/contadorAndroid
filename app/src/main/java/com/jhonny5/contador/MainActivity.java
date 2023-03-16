package com.jhonny5.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int contador;
    public TextView numContador;
    public CheckBox chkNumNeg;
    public EditText numReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contador = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nueva linea para mostrar el texto oncreate
        Toast.makeText(this, "onCreate",Toast.LENGTH_SHORT).show();
        //onCreate, la actividad se crea.
        numContador = findViewById(R.id.txtContador);
        //poner el objeto a la escucha que desencadena la accion y enviar el numero del txtreseta al contador.
        EventoTecladoOk tecladoOk = new EventoTecladoOk();
        //despues de crear la instancia, creamos un objeto editext para almacenar
        EditText numReset = (EditText) findViewById(R.id.txtNumReset);
        numReset.setOnEditorActionListener(tecladoOk);
    }

    //codigo de mi aplicacion del contador
    public void incrementar(View vista){
        contador++;
        numContador.setText(""+contador);
    }

    public void decrementar(View vista){
        contador--;
        if (contador < 0){
            chkNumNeg = findViewById(R.id.chkNumNeg);
            if (!chkNumNeg.isChecked()){
                contador=0;
            }
        }
        numContador.setText(""+contador);
    }

    public void reset(View vista){
        numReset = findViewById(R.id.txtNumReset);
        try {
            contador = Integer.parseInt(numReset.getText().toString());
        }catch (Exception e){
            contador = 0;
        }

        numReset.setText("");
        numContador.setText(""+contador);

        //capturamos en un objeto el medio de introduccion de datos, en esta caso el teclado
        InputMethodManager miTeclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        miTeclado.hideSoftInputFromWindow(numReset.getWindowToken(),0);

    }

    //crear clase interna parar acceder al metodo reset, al pulsar el visto o el ok del teclado.
    class EventoTecladoOk implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == EditorInfo.IME_ACTION_DONE){//informacion de API constante
                reset(null);//que solo se ejecute la funcion sin pasar argumentos, ya que es la misma vista.
            }
            return false;
        }
    }

    //fin codigo de mi aplicacion contador

}