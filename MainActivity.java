package com.example.user1.proj;

import android.graphics.drawable.TransitionDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    String[] Text1 = {"One", "Two", "Three", "Four", "Five", "Six", "Seven"};
    int[] image = {R.drawable.coffee2, R.drawable.dinner, R.drawable.coffee, R.drawable.dinner2, R.drawable.hotel,
                                R.drawable.hotel, R.drawable.hotel, R.drawable.hotel};

    LinearLayout linear1;
    ImageView add;
    ImageView del;
    //NavigationView nv = (NavigationView) findViewById(R.id.nav_view);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear1 = (LinearLayout) findViewById(R.id.main_linear);
        add = (ImageView) findViewById(R.id.add_note);
        if (add != null) {
            add.setOnClickListener(this);
        }
        del = (ImageView) findViewById(R.id.del_note);
        if (del != null) {
            del.setOnClickListener(this);
        }

        /*
        ViewPager pager;
        pager = (ViewPager) findViewById(R.id.pager_main);
        MyPagerAdapter madapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(madapter);

        ListView listview = (ListView) findViewById(R.id.list1);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), Text1, image);
        listview.setAdapter(adapter);
        */
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_note)
        {
            EditText etext1 = new EditText(getApplicationContext());
            etext1.getText();
            linear1.addView(etext1);
            Log.d("ADD_text", String.valueOf(etext1));
            Log.d("ADD_time", String.valueOf(System.currentTimeMillis()));

            final DBHandler db = new DBHandler(getApplicationContext());
            db.new_note("try", String.valueOf(etext1));
        }else if (v.getId()==R.id.del_note){
            Log.d("DEL","del working or not");
        }

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.nav_item_1){

        }else if(id==R.id.nav_item_2){

        }else if(id==R.id.nav_item_3){

        }else if(id==R.id.nav_item_4){

        }

        return true;
    }
}

