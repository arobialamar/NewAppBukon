package arobial.ta.bukukondangan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import java.util.List;

public class LihatDataKondangan extends AppCompatActivity{
    RecyclerView mRecyclerView;
    RecyclerView_Config.DataKondanganAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_kondangan);
        mRecyclerView = findViewById(R.id.list_data_kondangan);

        new FirebaseDatabaseHelper().readDataKondangan(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<DataKondangan> dataKondangans, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, LihatDataKondangan.this, dataKondangans, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                if(adapter == null){
//                    adapter.getFilter().filter(newText);
//                    Log.d("myTag", "This is my message");
//                }
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
