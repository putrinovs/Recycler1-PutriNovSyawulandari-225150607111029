package id.ac.ub.papb.recycler1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.CollationElementIterator;
import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {

    private ArrayList<Mahasiswa> mahasiswaList;
    private Context context; // Tambahkan Context

    // Constructor
    public MahasiswaAdapter(ArrayList<Mahasiswa> mahasiswaList, Context context) {
        this.mahasiswaList = mahasiswaList;
        this.context = context; // Simpan Context
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate recycler item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set data for the item
        Mahasiswa currentMahasiswa = mahasiswaList.get(position);
        holder.tvNim.setText(currentMahasiswa.getNim());
        holder.tvNama.setText(currentMahasiswa.getNama());
        holder.tvAsal.setText(currentMahasiswa.getAsal()); // Menambahkan asal
        holder.tvTglLahir.setText(currentMahasiswa.getTanggalLahir());
        // Anda juga bisa menambahkan informasi lainnya di sini
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTglLahir, tvAsal, tvNim, tvNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvTglLahir = itemView.findViewById(R.id.tglLahir);
            tvAsal = itemView.findViewById(R.id.asal);
        }
    }
}
