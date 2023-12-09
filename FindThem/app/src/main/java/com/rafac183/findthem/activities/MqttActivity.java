package com.rafac183.findthem.activities;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rafac183.findthem.R;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.adapter.MqttAdapter;
import com.rafac183.findthem.adapter.MqttData;
import com.rafac183.findthem.databinding.ActivityMqttBinding;
import com.rafac183.findthem.interfaces.MqttInterface;
import com.rafac183.findthem.model.MqttViewModel;
import com.rafac183.findthem.ui.registered_people.PeopleFragment;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_people.PeopleViewModel;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import java.util.ArrayList;

public class MqttActivity extends AppCompatActivity implements MqttInterface{
    private ActivityMqttBinding binding;
    private static final String TAG = "MyMQTTApp";
    private String clienteId="";

    //Conexión al servidor MQTT
    //mqtt://mqtt-findthem:DUmNKLlLd4Z7O0Tc@mqtt-findthem.cloud.shiftr.io
    private static  String mqttHost = "tcp://find-key.cloud.shiftr.io:1883";
    private static  String mqttUser = "find-key";
    private static  String mqttPass = "tH6GuodkwXi0Q2F1";

    private MqttAndroidClient cliente;
    private MqttConnectOptions opciones;

    private static String topic = "LED";
    private static String topicMsgActivate = "Activate Location";
    private static String topicMsgDefuse = "Defuse Location";
    private boolean permisoPublicar;
    private MaterialButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MqttViewModel mqttViewModel = new ViewModelProvider(MqttActivity.this).get(MqttViewModel.class); //esto se trae la lista

        super.onCreate(savedInstanceState);
        binding = ActivityMqttBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mqttViewModel.getPeopleData().observe(this, this::initRecyclerView);

        mqttViewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.chargingScreen.setVisibility(View.VISIBLE);
            } else {
                binding.chargingScreen.setVisibility(View.GONE);
            }
        });

        exit = binding.exit;
        Btns();

        getNombreCliente();
        connectBroker();
    }

    private void checkConnection(){
        if(this.cliente.isConnected()){
            this.permisoPublicar = true;
        }
        else{
            this.permisoPublicar = false;
            connectBroker();
        }
    }

    private void enviarMensaje(String topic, String msg){
        checkConnection();
        try{
            int qos = 0; //puede ser 0 (envía una vez), 1(envía hasta recibir) o 2(envía hasta que el cliente confirme)
            this.cliente.publish(topic, msg.getBytes(), qos, false);
            Toast.makeText(getBaseContext(), topic + ":" + msg, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    // Dentro de tu clase MainActivity
    private void connectBroker() {
        try {
            new Thread(this::doConnectBroker).start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doConnectBroker() {
        this.cliente = new MqttAndroidClient(this.getApplicationContext(), mqttHost, this.clienteId);
        this.opciones = new MqttConnectOptions();
        this.opciones.setUserName(mqttUser);
        this.opciones.setPassword(mqttPass.toCharArray());

        try {
            IMqttToken token = this.cliente.connect(opciones);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    runOnUiThread(() -> {
                        Toast.makeText(MqttActivity.this, "Conectado", Toast.LENGTH_SHORT).show();
                        suscribirseTopic();
                    });
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    runOnUiThread(() -> Toast.makeText(MqttActivity.this, "Falló conexión", Toast.LENGTH_SHORT).show());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }


    private void suscribirseTopic(){
        try {
            this.cliente.subscribe(topic, 0);
        }
        catch (MqttSecurityException e){
            e.printStackTrace();
        }
        catch (MqttException e){
            e.printStackTrace();
        }
        this.cliente.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(getBaseContext(), "Se desconectó el servidor", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                LinearLayout cv = findViewById(R.id.LYCV);
                if(topic.matches(topic)){
                    String msg = new String(message.getPayload());
                    if(msg.matches(topicMsgActivate)){
                        cv.setBackgroundColor(GREEN);
                    }
                    else if(msg.matches(topicMsgDefuse)){
                        cv.setBackgroundColor(RED);
                    }
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    private void getNombreCliente(){
        String manufacturer = Build.MANUFACTURER;
        String modelName = Build.MODEL;
        this.clienteId = manufacturer + " " + modelName;

        TextView txtIdCliente = findViewById(R.id.txtIdCliente);
        txtIdCliente.setText(this.clienteId);
    }

    private void Btns(){
        exit.setOnClickListener(v -> {
            startActivity(new Intent(MqttActivity.this, NavigationActivity.class));
            finish();
        });
    }

    public void initRecyclerView(ArrayList<PeopleModel> peopleList){
        LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerMqtt.getContext()); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerMqtt.getContext(), manager.getOrientation());
        binding.recyclerMqtt.setHasFixedSize(true); //Extra
        binding.recyclerMqtt.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerMqtt.setLayoutManager(manager);
        binding.recyclerMqtt.setAdapter(new MqttAdapter(peopleList, MqttActivity.this));
    }

    @Override
    public void onCLickActivate() {
        enviarMensaje(topic, topicMsgActivate);
    }

    @Override
    public void onCLickDefuse() {
        enviarMensaje(topic, topicMsgDefuse);
    }
}