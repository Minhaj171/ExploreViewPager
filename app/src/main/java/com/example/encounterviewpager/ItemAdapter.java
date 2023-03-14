package com.example.encounterviewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.encounterviewpager.databinding.FakeItemBinding;
import com.example.encounterviewpager.databinding.ItemRowBinding;

import java.util.List;

/**
 * Created by Md Minhajul Islam on 13/03/2023.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<PojoData> pojoDataList;

    public ItemAdapter(List<PojoData> pojoDataList) {
        this.pojoDataList = pojoDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            ItemRowBinding binding = ItemRowBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding);
        } else {
            FakeItemBinding binding = FakeItemBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding\);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PojoData pojoData = pojoDataList.get(position);
        holder.bindView(pojoData);
    }

    @Override
    public int getItemCount() {
        return pojoDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        PojoData pojoData = pojoDataList.get(position);
        return pojoData.isRealItem ? 0 : 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewBinding binding;

        public ViewHolder(@NonNull ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(PojoData pojoData) {
            if (binding instanceof ItemRowBinding) {
                ItemRowBinding itemRowBinding = (ItemRowBinding) binding;
                itemRowBinding.imageView.setImageResource(pojoData.imageId);
                itemRowBinding.textView.setText(pojoData.description);
            } else {
                FakeItemBinding fakeItemBinding = (FakeItemBinding) binding;
                fakeItemBinding.textView2.setText(pojoData.description);
            }
        }
    }
}

