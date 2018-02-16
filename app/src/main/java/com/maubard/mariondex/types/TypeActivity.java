package com.maubard.mariondex.types;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maubard.mariondex.R;
import com.maubard.mariondex.data.entity.TypeEntity;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Marion Aubard on 16/02/2018.
 */

public class TypeActivity extends AppCompatActivity {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private RecyclerView recyclerView;
    private TypeAdapter typeAdapter;

    /***********************************************************
     *  Managing LifeCycle
     **********************************************************/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), VERTICAL));

        typeAdapter = new TypeAdapter(getApplicationContext(), new ArrayList<TypeEntity>());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(typeAdapter);

    }
}
