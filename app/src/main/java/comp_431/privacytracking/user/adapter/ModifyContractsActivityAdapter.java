package comp_431.privacytracking.user.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.R;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.ChangeContractActivity;
import comp_431.privacytracking.user.ModifyContractsActivity;
import comp_431.privacytracking.user.TrackRecursiveActivity;

public class ModifyContractsActivityAdapter extends RecyclerView.Adapter<ModifyContractsActivityAdapter.ViewHolder> {

    RecyclerView recyclerView;
    List<MetaDB> contracts;

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            MetaDB id = contracts.get(itemPosition);
            view.getContext().startActivity(new Intent(view.getContext(),ChangeContractActivity.class).putExtra("Record",id.getUri()));
        }
    };
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    public ModifyContractsActivityAdapter(List<MetaDB> contracts){
        this.contracts= contracts;
    }

    @Override
    public ModifyContractsActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new ModifyContractsActivityAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ModifyContractsActivityAdapter.ViewHolder holder, int position) {
        holder.tv.setText(contracts.get(position).getUri()); //TODO: Should we show the Uri?
    }

    @Override
    public int getItemCount() {
        if (contracts != null)
            return contracts.size();
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
