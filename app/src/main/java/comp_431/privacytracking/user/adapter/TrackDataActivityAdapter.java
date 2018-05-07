//package comp_431.privacytracking.user.adapter;
//
//
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
//import comp_431.privacytracking.R;
//import comp_431.privacytracking.company.UserContractsActivity;
//import comp_431.privacytracking.database.company.CompanyDB;
//import comp_431.privacytracking.database.meta_data.MetaDB;
//
//public class TrackDataActivityAdapter extends RecyclerView.Adapter<TrackDataActivityAdapter.ViewHolder> {
//
//    RecyclerView recyclerView;
//    List<MetaDB> originalRecords;
//
//    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
//        public void onClick(View view) {
//            int itemPosition = recyclerView.getChildLayoutPosition(view);
//            MetaDB id = originalRecords.get(itemPosition);
//            //TODO: Create a new class for showing all the contrats with one user.
//            view.getContext().startActivity(new Intent(view.getContext(), UserContractsActivity.class).putExtra("Record",id.getUri()));
//        }
//    };
//
//    public TrackDataActivityAdapter(List<MetaDB> originalRecords) {
//        this.originalRecords = originalRecords;
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//
//        this.recyclerView = recyclerView;
//    }
//
//    @Override
//    public TrackDataActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptor_item, parent, false);
//        view.setOnClickListener(myOnClickListener);
//        return new TrackDataActivityAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(TrackDataActivityAdapter.ViewHolder holder, int position) {
//        holder.tv.setText(companiesList.get(position).getCompanyName());
//    }
//
//    @Override
//    public int getItemCount() {
//        if (companiesList != null)
//            return companiesList.size();
//        else
//            return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tv;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            tv = itemView.findViewById(R.id.tvText);
//        }
//    }
//}
