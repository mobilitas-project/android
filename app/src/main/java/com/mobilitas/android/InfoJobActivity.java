package com.mobilitas.android;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mobilitas.android.adapter.HouseCustomAdapter;
import com.mobilitas.android.house.House;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfoJobActivity extends AppCompatActivity {

    Button btn_view_houses;

    private RecyclerView recyclerView;
    private HouseCustomAdapter houseCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_job);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Informacao da vaga");
        setTitleColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor((R.color.background_red)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_view_houses = findViewById(R.id.btn_view_houses);

        btn_view_houses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoJobActivity.this);
                builder.setTitle("Lista de casas");
                View viewInflated = LayoutInflater.from(InfoJobActivity.this).inflate(R.layout.popup_text, (ViewGroup) v.getRootView(), false);
                builder.setView(viewInflated);

                recyclerView = (RecyclerView) viewInflated.findViewById(R.id.recycler_view);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(viewInflated.getContext());
                recyclerView.setLayoutManager(mLayoutManager);


                List<House> l = new ArrayList<House>();
                l.add(new House("33", -23.5848364, -46.6777537, "Apartamento com 1 dorm, 70m²", 4852.00, "Rua Bandeira Paulista, Itaim Bibi, São Paulo"));
                l.add(new House("34", -23.587439, -46.6758302, "Studio com 1 dorm, 79m²", 5017.00, "Rua Benedito Lapin, Vila Olímpia, São Paulo"));
                l.add(new House("32", -23.5931217, -46.6837197, "Apartamento com 1 dorm, 32m²", 6035.00, "Rua Coronel Joaquim Ferreira Lobo, Vila Olímpia, São Paulo"));
                l.add(new House("31", -23.5923958, -46.6830733, "Studio com 1 dorm, 52m²", 6622.00, "Rua Ministro Jesuíno Cardoso, Vila Olímpia, São Paulo"));
                l.add(new House("35", -23.5815706, -46.6838131, "Apartamento com 3 dorms, 147m²", 6520.00, "Rua André Fernandes, Itaim Bibi, São Paulo"));
                l.add(new House("36", -23.5814246, -46.6792123, "Apartamento com 1 dorm, 60m²", 6914.00, "Rua Pedroso Alvarenga, Itaim Bibi, São Paulo"));

                houseCustomAdapter = new HouseCustomAdapter(getBaseContext(), l);

                recyclerView.setAdapter(houseCustomAdapter);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onResume();
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
