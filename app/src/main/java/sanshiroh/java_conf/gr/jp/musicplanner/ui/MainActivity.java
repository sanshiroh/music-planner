package sanshiroh.java_conf.gr.jp.musicplanner.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import sanshiroh.java_conf.gr.jp.musicplanner.model.ChordsGenerator;
import sanshiroh.java_conf.gr.jp.musicplanner.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private final ChordsGenerator mChordsGenerator = new ChordsGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view_sections);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // Sets callback to remove swiped item
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.remove(viewHolder.getAdapterPosition());
            }
        };
        new ItemTouchHelper(callback).attachToRecyclerView(mRecyclerView);

        findViewById(R.id.button_generator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.add(mChordsGenerator.generate());
                mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
    }
}
