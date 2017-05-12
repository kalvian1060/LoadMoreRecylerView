package id.kalviansofian.belajarrecylerviewloadmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by root on 12/05/17.
 */

public class BarangHolder  extends RecyclerView.ViewHolder{
    public TextView tvBarang;

    public BarangHolder(View v) {
        super(v);
        tvBarang=(TextView)v.findViewById(R.id.tvBarang);

    }
}
