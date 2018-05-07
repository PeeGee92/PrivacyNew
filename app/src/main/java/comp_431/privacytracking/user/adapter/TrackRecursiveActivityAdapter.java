package comp_431.privacytracking.user.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp_431.privacytracking.R;
import comp_431.privacytracking.database.meta_data.MetaDB;
import comp_431.privacytracking.user.TrackRecursiveActivity;
import comp_431.privacytracking.user.UserEnum;

public class TrackRecursiveActivityAdapter extends RecyclerView.Adapter<TrackRecursiveActivityAdapter.ViewHolder>{

    RecyclerView recyclerView;
    List<MetaDB> records;

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            MetaDB id = records.get(itemPosition);
            view.getContext().startActivity(new Intent(view.getContext(),TrackRecursiveActivity.class).putExtra("Record",id.getUri()));
        }
    };

    public TrackRecursiveActivityAdapter(List<MetaDB> originalRecords) {
        this.records = originalRecords;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public TrackRecursiveActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new TrackRecursiveActivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackRecursiveActivityAdapter.ViewHolder holder, int position) {
        holder.tv.setText(RecordToString(records.get(position)));
    }
    private String RecordToString(MetaDB record){
        String result;
        String sharelist = shareListTotString(record.getShareList());

        result=
                "URI".concat(record.getUri()).concat("\n")
                        .concat("User Id").concat(record.getUserId()).concat("\n")
                        .concat("BackWard Ref Id ").concat(record.getBackRefId()).concat("\n")
                        .concat("Company Id ").concat(record.getCompanyId()).concat("\n")
                        .concat("Root ID ").concat(record.getRootId()).concat("\n")
                        .concat("Creation Time ").concat(record.getCreationTime().toString()).concat("\n")
                        .concat("Expiration Time ").concat(record.getExpirationTime().toString()).concat("\n")
                        .concat("Deleted ").concat(record.getDeleted().toString()).concat("\n")
                        .concat("Deleted").concat(sharelist).concat("\n");

        return result;
    }

    private String shareListTotString(ArrayList<Boolean> shareList){
        String result ="Share List: \n";
        UserEnum index = new UserEnum();
        for(int i=0;i<shareList.size();i++){
            if(shareList.get(i)){
                result.concat(index.returnFiel(i)).concat( ": True").concat("\n");
            }
            else{
                result.concat(index.returnFiel(i)).concat( ": False").concat("\n");
            }
        }
        return result;
    }


    @Override
    public int getItemCount() {
        if (records != null)
            return records.size();
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
