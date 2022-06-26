package com.example.tdelete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private Toolbar toolbar;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    boolean isContexualModelEnable = false;
    TextView itemCounter;
    int counter = 0;


    // image Array

    private int []arr = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j};

    String[] name;

    ArrayList<Image> list;
    ArrayList<Image> selectionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.recyclerView);
        itemCounter = findViewById(R.id.itemCounter);

        imageAdapter = new ImageAdapter(list, MainActivity.this);
        list = new ArrayList<>();
        selectionList = new ArrayList<>();

        // actionbar
        setSupportActionBar(toolbar);
        itemCounter.setText("My Gallery");

        name = getResources().getStringArray(R.array.image_name);

        for (int i=0; i<name.length;i++){
            Image image = new Image(arr[i],name[i]);
            list.add(image);
        }

        // to set Image from recyclerview
        imageAdapter = new ImageAdapter(list, MainActivity.this );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_menu, menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view) {
        isContexualModelEnable = true;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.contexual_menu);
        getSupportActionBar().setTitle("0 item Selected");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88898A")));
        imageAdapter.notifyDataSetChanged();
        return true;
    }

    // make selection
    public void MakeSelection(View view, int adapterPosition) {
        if (((CheckBox)view).isChecked()){
           selectionList.add(list.get(adapterPosition));
           counter++;
           updateCounter();
        }else{
            selectionList.remove(list.get(adapterPosition));
            counter--;
            updateCounter();
        }
    }

    // Method to update Counter....
    public void updateCounter(){
        itemCounter.setText(counter + " item selected");
    }

    // Method to delete selected item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            imageAdapter.RemoveItem(selectionList);
            removeContexualActionMode();
        }else if (item.getItemId()==android.R.id.home){
            removeContexualActionMode();
            imageAdapter.notifyDataSetChanged();
        }
        return true;
    }

    private void removeContexualActionMode() {
        isContexualModelEnable = false;
        itemCounter.setText("My Gallery");
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.normal_menu);
        counter = 0;
        selectionList.clear();
        imageAdapter.notifyDataSetChanged();
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#224E12")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}