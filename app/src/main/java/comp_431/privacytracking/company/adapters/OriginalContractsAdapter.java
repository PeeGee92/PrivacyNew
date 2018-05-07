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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contract_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new OriginalContractsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OriginalContractsAdapter.ViewHolder holder, int position) {
        // TODO get names from list
        holder.tvUserName.setText(originalLists.get(position));
        holder.tvCompanyName.setText(originalLists.get(position));
    }

    @Override
    public int getItemCount() {
        if (originalLists != null)
            return originalLists.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        TextView tvCompanyName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
        }
    }
}
