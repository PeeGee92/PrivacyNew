package comp_431.privacytracking.company.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.R;

public class OriginalContractsAdapter extends RecyclerView.Adapter<OriginalContractsAdapter.ViewHolder>{


    List<String> originalLists;
    RecyclerView recyclerView;


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
        }
    };

    public OriginalContractsAdapter(List<String> originalLists){
        this.originalLists=originalLists;
    }

    @Override
    public OriginalContractsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptor_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new OriginalContractsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OriginalContractsAdapter.ViewHolder holder, int position) {
        // TODO get names from list
        holder.tv.setText(originalLists.get(position));
    }

    @Override
    public int getItemCount() {
        if (originalLists != null)
            return originalLists.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tvText);
        }
    }

}
