package id.kalviansofian.belajarrecylerviewloadmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by root on 12/05/17.
 */

public class ProgressViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public ProgressViewHolder(View v) {
        super(v);
        progressBar=(ProgressBar)v.findViewById(R.id.pBar);
    }
}
