package com.example.nuevo_proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private MainActivity localDataSet;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private ArrayList<Pokemon> dataset;
    private String[] DataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;


        //private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById((R.id.nombreTextView));
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.nombretextview);
        }


        //public TextView getTextView() {
        //  return textView;
        //}
    }

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Pokemon p = dataset.get(position);

        holder.name.setText(p.getName());
    }

    public void add(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }


}
// Create new views (invoked by the layout manager)


// Replace the contents of a view (invoked by the layout manager)

// Return the size of your dataset (invoked by the layout manager)


