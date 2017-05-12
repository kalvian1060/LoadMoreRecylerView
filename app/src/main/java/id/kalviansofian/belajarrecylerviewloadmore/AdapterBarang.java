package id.kalviansofian.belajarrecylerviewloadmore;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/05/17.
 */
public class AdapterBarang extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM=1;
    private final int VIEW_PROG=0;

    private ArrayList<Barang> barangList;
    private onLoadMoreListener loadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isMoreLoading=false;
    private int visibleThreshold=1;
    int firstVisibleItem,visibleItemCount,totalItemCount;

    public interface onLoadMoreListener{
        void onLoadMore();
    }

    public AdapterBarang(onLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
        barangList=new ArrayList<>();
    }
    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager){
        mLinearLayoutManager=linearLayoutManager;
    }

    public void setRecyclerView(RecyclerView mView){
        mView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount=recyclerView.getChildCount();
                totalItemCount=mLinearLayoutManager.getItemCount();
                firstVisibleItem=mLinearLayoutManager.findFirstVisibleItemPosition();

                if(!isMoreLoading && (totalItemCount-visibleItemCount) <= (firstVisibleItem+visibleThreshold)){
                    if(loadMoreListener!=null){
                        loadMoreListener.onLoadMore();
                    }
                    isMoreLoading=true;
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return barangList.get(position) !=null? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==VIEW_ITEM){
            return new BarangHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang,parent,false));
        }else{
            return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress,parent,false));
        }
    }
    public void addAll(List<Barang> list){
        barangList.clear();
        barangList.addAll(list);
        notifyDataSetChanged();
    }

    public void addItemLoadMore(List<Barang> list){
        barangList.addAll(list);
        notifyItemChanged(0,barangList.size());

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BarangHolder){
            Barang singleItem=(Barang)barangList.get(position);
            ((BarangHolder)holder).tvBarang.setText(singleItem.getNamaBarang());
        }
    }

    public void setMoreLoading(boolean isMoreLoading){
        this.isMoreLoading=isMoreLoading;
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public void setProgressMore(final boolean isProgress){
        if(isProgress){
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    barangList.add(null);
                    notifyItemInserted(barangList.size()-1);
                }
            });
        }else{
            barangList.remove(barangList.size()-1);
            notifyItemChanged(barangList.size());
        }
    }
}
