package id.kalviansofian.belajarrecylerviewloadmore;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterBarang.onLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, MainView {
    IPresentMain presentMain;
    RecyclerView rvBarang;
    SwipeRefreshLayout refreshLayout;
    ArrayList<Barang> barangList;
    AdapterBarang adapterBarang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presentMain = new IPresentMain(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void setupView() {
        barangList = new ArrayList<Barang>();
        rvBarang = (RecyclerView) findViewById(R.id.rvBarang);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        rvBarang.setLayoutManager(linearLayout);
        adapterBarang = new AdapterBarang(this);
        adapterBarang.setLinearLayoutManager(linearLayout);
        adapterBarang.setRecyclerView(rvBarang);
        rvBarang.setAdapter(adapterBarang);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onLoadMore() {
        adapterBarang.setProgressMore(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                barangList.clear();
                adapterBarang.setProgressMore(false);
                int start = adapterBarang.getItemCount();
                int end = start + 15;

                for (int i = start + 1; i <= end; i++) {
                    barangList.add(new Barang("Item " + i));
                }
                adapterBarang.addItemLoadMore(barangList);
                adapterBarang.setMoreLoading(false);
            }
        }, 2000);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                loadData();
            }
        }, 2000);
    }

    public void loadData() {
        barangList.clear();
        for (int i = 0; i <= 20; i++) {
            barangList.add(new Barang("Barang Ke" + i));
        }
        adapterBarang.addAll(barangList);
    }
}
