package com.example.simpleparadox.listycity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;
    String TAG = "SAMPLE";
    Button addCityButton;
    EditText addCityText;
    EditText addProvinceText;
    CustomList customList;
    FirebaseFirestore db;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.addCityButton);
        addCityText = findViewById(R.id.add_city_field);
        addProvinceText = findViewById(R.id.add_province_edit_text);
        delete = findViewById(R.id.delete);

        //String []cities ={"Edmonton", "Vancouver", "Toronto", "Hamilton", "Denver", "Los Angeles"};
        //String []provinces = {"AB", "BC", "ON", "ON", "CO", "CA"};
        db = FirebaseFirestore.getInstance();
        final CollectionReference citiesRef = db.collection("cities");

        cityDataList = new ArrayList<>();


        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final City city = cityDataList.get(i);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteCity(city);
                    }
                });
            }
        });



        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cityName = addCityText.getText().toString();
                final String provinceNAme = addProvinceText.getText().toString();

                HashMap<String,String> data = new HashMap<>();
                if (cityName.length() > 0 && provinceNAme.length() > 0){
                    data.put("Province",provinceNAme);
                    citiesRef.document(cityName)
                            .set(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>(){
                                @Override
                                public void onSuccess(Void aVoid){
                                    Log.d(TAG, "data is added to firestore");
                                }
                        })
                        .addOnFailureListener(new OnFailureListener(){
                            @Override
                            public void onFailure(@NonNull Exception e){
                                Log.d(TAG,"Error: " + e.getMessage());
                            }
                        });
                        addCityText.setText("");
                        addProvinceText.setText("");
                    }
                }
            });

        citiesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                cityDataList.clear();

                for (DocumentSnapshot city: queryDocumentSnapshots) {
                    Log.d(TAG,String.valueOf(city.getData().get("Province")));
                    String cityName = city.getId();
                    String provinceName = (String) city.getData().get("Province");
                    cityDataList.add(new City(cityName, provinceName));
                }
                cityAdapter.notifyDataSetChanged();
            }
        });


        }



    public void deleteCity(City city){
        db.collection("cities").document(city.getCityName())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });



    }


}
