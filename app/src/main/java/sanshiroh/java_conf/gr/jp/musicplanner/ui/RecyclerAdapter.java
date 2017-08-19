package sanshiroh.java_conf.gr.jp.musicplanner.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sanshiroh.java_conf.gr.jp.musicplanner.R;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final List<String> mSections = new ArrayList<>();

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mSections.get(position));
    }

    @Override
    public int getItemCount() {
        return mSections.size();
    }

    public void add(String section) {
        mSections.add(section);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mSections.remove(position);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.text_view_chords);
        }
    }
}
