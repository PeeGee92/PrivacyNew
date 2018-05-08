package comp_431.privacytracking.user.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp_431.privacytracking.LoginActivity;
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

    // Make a list with all this user´s contracts.
    private String RecordToString(MetaDB record){
        String result = "";
        String sharelist = shareListTotString(record.getShareList());

        result += (record.getUri());
        result += ("\n");
        result += ("User Id");
        result += (record.getUserId());
        result += ("\n");
        result += ("BackWard Ref Id ");
        result += (record.getBackRefId());
        result += ("\n");
        result += ("Company Id ");
        result += (record.getCompanyId());
        result += ("\n");
        result += ("Root ID ");
        result += (record.getRootId());
        result += ("\n");
        result += ("Creation Time ");
        result += (LoginActivity.dbmanag.timeStampFormat.format(record.getCreationTime()));
        result += ("\n");
        result += ("Expiration Time ");
        result += (LoginActivity.dbmanag.timeStampFormat.format(record.getExpirationTime()));
        result += ("\n");
        result += ("Deleted ");
        result += (record.getDeleted().toString());
        result += ("\n");
        result += (sharelist);
        result += ("\n");

        return result;
    }

    private String shareListTotString(ArrayList<Boolean> shareList){
        String result = "";
        UserEnum index = new UserEnum();
        for(int i=0;i<shareList.size();i++){
            if(shareList.get(i)){
                result += (index.returnFiel(i));
                result += ( ": True");
                result += ("\n");
            }
            else{
                result += (index.returnFiel(i));
                result += ( ": False");
                result += ("\n");
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
