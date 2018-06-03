package com.mobilitas.android;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

    private ArrayList<House> userList;
    private RecyclerView recyclerView;
    private HouseCustomAdapter houseCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_job);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Information Job");
        setTitleColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundColor(getResources().getColor((R.color.background_red)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_view_houses = findViewById(R.id.btn_view_houses);

        btn_view_houses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoJobActivity.this);
                builder.setTitle("List Houses");
                View viewInflated = LayoutInflater.from(InfoJobActivity.this).inflate(R.layout.popup_text, (ViewGroup) v.getRootView(), false);
                builder.setView(viewInflated);

                recyclerView = (RecyclerView) viewInflated.findViewById(R.id.recycler_view);

                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(viewInflated.getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);


                List<House> l = new ArrayList<House>();
                l.add(new House("1",0, 0, "House 1", 1200, "Av. Braz Leme, 1000"));
                l.add(new House("1",0, 0, "House 1", 1200, "Av. Braz Leme, 1000"));
                l.add(new House("1",0, 0, "House 1", 1200, "Av. Braz Leme, 1000"));
                l.add(new House("1",0, 0, "House 1", 1200, "Av. Braz Leme, 1000"));
                l.add(new House("1",0, 0, "House 1", 1200, "Av. Braz Leme, 1000"));
                l.add(new House("1",0, 0, "House 1", 1200, "Av. Braz Leme, 1000"));

                houseCustomAdapter = new HouseCustomAdapter(getBaseContext(), l);

                recyclerView.setAdapter(houseCustomAdapter);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onResume();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
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
