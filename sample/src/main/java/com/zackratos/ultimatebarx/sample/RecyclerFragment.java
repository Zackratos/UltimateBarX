package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zackratos.ultimatebarx.library.UltimateBarX;

import java.util.Locale;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:19 PM
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class RecyclerFragment extends InnerFragment {

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    private RecyclerView rvMain;

    public RecyclerFragment() {
        super(R.layout.fragment_recycler);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUltimateBarX();
        rvMain = view.findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvMain.setAdapter(new Adapter());
    }

    private void initUltimateBarX() {
        UltimateBarX.with(this)
                .fitWindow(false)
                .light(false)
                .colorRes(R.color.alphaGreen)
                .applyStatusBar();
        UltimateBarX.with(this)
                .fitWindow(false)
                .light(false)
                .colorRes(R.color.alphaBlack)
                .applyNavigationBar();
    }

    private static class Adapter extends RecyclerView.Adapter<RecyclerFragment.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv.setText(String.format(Locale.getDefault(), "%d", position));
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }
}
