package com.rafac183.findthem.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rafac183.findthem.Adapter.CustomAdapter;
import com.rafac183.findthem.Adapter.DataModel;
import com.rafac183.findthem.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter; //Tambien puede ser custom adapter
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> dataList;
    public static View.OnClickListener myOnClick;
    private static ArrayList<Integer> removedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myOnClick = new MyOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_home);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataList = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            dataList.add(new DataModel(
                    MyData.id_[i],
                    MyData.nameArray[i],
                    MyData.descArray[i],
                    MyData.drawableArray[i]
            ));
        }
        removedItems = new ArrayList<Integer>();
        adapter = new CustomAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;
        private MyOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            removeItem(v);
        }
        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            dataList.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.add_item) {
            //check if any items to add
            if (removedItems.size() != 0) {
                addRemovedItemToList();
            } else {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        dataList.add(addItemAtListPosition, new DataModel(
                MyData.id_[removedItems.get(0)],
                MyData.nameArray[removedItems.get(0)],
                MyData.descArray[removedItems.get(0)],
                MyData.drawableArray[removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }
}
