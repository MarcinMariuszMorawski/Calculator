package pl.lodz.uni.math.morawski.marcin.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import pl.lodz.uni.math.morawski.marcin.calculator.database.DatabaseManager;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;
    private ArrayAdapter<String> equationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListVIew);
        readHistory();

        final Button deleteHistoryButton = findViewById(R.id.deleteHistoryButton);
        deleteHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHistory();
            }
        });
    }


    private void readHistory() {
        List<String> equationList = DatabaseManager.loadHistory(getBaseContext());
        equationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, equationList);
        historyListView.setAdapter(equationAdapter);
    }


    private void deleteHistory() {
        equationAdapter.clear();
        DatabaseManager.clearHistory(getBaseContext());
    }
}
