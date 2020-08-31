package arobial.ta.bukukondangan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataKondanganAdapter extends RecyclerView.Adapter<DataKondanganAdapter.DataKondanganViewHolder> implements Filterable {

    private List<DataKondangan> DaKonList; //DaKon singkatan Data Kondangan
    private List<DataKondangan> DaKonListFull; //DaKon singkatan Data Kondangan

    public DataKondanganAdapter(List<DataKondangan> lidakon) {
        this.DaKonList = lidakon;
        DaKonListFull = new ArrayList<>(DaKonList);
    }

    @NonNull
    @Override
    public DataKondanganAdapter.DataKondanganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_kondangan,
                parent, false);
        return new DataKondanganViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DataKondanganAdapter.DataKondanganViewHolder holder, final int position) {

        DataKondangan currentItem = DaKonList.get(position);

        holder.te_nama.setText("Nama : "+currentItem.getNama());
        holder.te_kcmtn.setText("Kecamatan : "+currentItem.getKecamatan());
        holder.te_klrhn.setText("Kelurahan : "+currentItem.getKelurahan());
        holder.te_alamat.setText("Alamat : "+currentItem.getAlamat());
        holder.te_rp.setText(currentItem.getRp());
        holder.te_liter.setText("Beras : "+currentItem.getLiter());
    }

    @Override
    public int getItemCount() {
        return DaKonList.size();
    }

    class DataKondanganViewHolder  extends RecyclerView.ViewHolder {
        TextView te_nama;
        TextView te_kcmtn;
        TextView te_klrhn;
        TextView te_alamat;
        TextView te_rp;
        TextView te_liter;
        Button btn_lihat;
        String key;

        DataKondanganViewHolder (View itemView) {
            super(itemView);
            te_nama = itemView.findViewById(R.id.tv_nama);
            te_kcmtn = itemView.findViewById(R.id.tv_kcamatan);
            te_klrhn = itemView.findViewById(R.id.tv_klurahan);
            te_alamat = itemView.findViewById(R.id.tv_alamat);
            te_rp = itemView.findViewById(R.id.tv_rp);
            te_liter = itemView.findViewById(R.id.tv_liter);
        }
    }

    @Override
    public Filter getFilter() {
        return DataKondanganFilter;
    }

    private Filter DataKondanganFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataKondangan> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(DaKonListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DataKondangan item : DaKonListFull) {
                    if (item.getNama().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
//                    if (item.getTahun().toLowerCase().contains(filterPattern)){
//                        filteredList.add(item);
//                    }
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
