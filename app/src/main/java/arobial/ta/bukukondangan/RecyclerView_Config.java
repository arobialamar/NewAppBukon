package arobial.ta.bukukondangan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private DataKondanganAdapter mDataKondanganAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<DataKondangan> dataKondangans, List<String> keys){
        mContext = context;
        mDataKondanganAdapter = new DataKondanganAdapter(dataKondangans, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mDataKondanganAdapter);

    }

    class ItemDataKondangan extends RecyclerView.ViewHolder {
        TextView iNama, iKecamatan, iKelurahan, iAlamat, iUang, iLiter, iStatus;
        private String key;

        public ItemDataKondangan(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.item_data_kondangan, parent, false));

            iNama = itemView.findViewById(R.id.tv_nama);
            iKecamatan = itemView.findViewById(R.id.tv_kcamatan);
            iKelurahan = itemView.findViewById(R.id.tv_klurahan);
            iAlamat = itemView.findViewById(R.id.tv_alamat);
            iUang = itemView.findViewById(R.id.tv_rp);
            iLiter = itemView.findViewById(R.id.tv_liter);
            iStatus = itemView.findViewById(R.id.tv_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UpdateDeleteActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("nama", iNama.getText().toString());
                    intent.putExtra("kecamatan", iKecamatan.getText().toString());
                    intent.putExtra("kelurahan", iKelurahan.getText().toString());
                    intent.putExtra("alamat", iAlamat.getText().toString());
                    intent.putExtra("rp", iUang.getText().toString());
                    intent.putExtra("liter", iLiter.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(DataKondangan dataKondangan, String key){
            iNama.setText(dataKondangan.getNama());
            iKecamatan.setText(dataKondangan.getKecamatan());
            iKelurahan.setText(dataKondangan.getKelurahan());
            iAlamat.setText(dataKondangan.getAlamat());
            iUang.setText(dataKondangan.getRp());
            iLiter.setText(dataKondangan.getLiter());
            iStatus.setText(dataKondangan.getStatus());
            this.key=key;
        }
    }

    class DataKondanganAdapter extends RecyclerView.Adapter<ItemDataKondangan> implements Filterable {
        private List<DataKondangan> DaKonList;
        private List<DataKondangan> DaKonListFull;
        private List<String> mKeys;

        public DataKondanganAdapter(List<DataKondangan> DaKonList, List<String> mKeys){
            this.DaKonList = DaKonList;
            DaKonListFull = new ArrayList<>(DaKonList);
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ItemDataKondangan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new  ItemDataKondangan(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemDataKondangan holder, int position) {
            holder.bind(DaKonList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return DaKonList.size();
        }

        @Override
        public Filter getFilter() {
            return DaKonFilter;
        }

        private Filter DaKonFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<DataKondangan> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(DaKonListFull);

                }else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (DataKondangan item : DaKonListFull){
                        if (item.getNama().toLowerCase().contains(filterPattern)){
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                DaKonList.clear();
                DaKonList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
