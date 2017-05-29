package com.starwars.people.list.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.starwars.R;
import com.starwars.core.listener.OnItemClick;
import com.starwars.people.list.presentation.model.PresentationPerson;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListOfPeopleAdapter extends RecyclerView.Adapter<ListOfPeopleAdapter.ViewHolder> {

    private List<PresentationPerson> presentationPeople;
    private OnItemClick onItemClick;

    public ListOfPeopleAdapter(){
        presentationPeople = new ArrayList<>(0);
    }

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_person, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PresentationPerson presentationPerson = presentationPeople.get(position);
        holder.textViewName.setText(presentationPerson.name());
        holder.textViewUrl.setText(presentationPerson.url());
    }

    @Override
    public int getItemCount() {
        return presentationPeople.size();
    }

    public void addPresentationPerson(@Nonnull PresentationPerson presentationPerson) {
        presentationPeople.add(presentationPerson);
        notifyItemInserted(presentationPeople.size() - 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_picture)
        ImageView imageViewPicture;

        @BindView(R.id.textView_name)
        TextView textViewName;

        @BindView(R.id.textView_url)
        TextView textViewUrl;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        public void onClickContainer(){
            onItemClick.onClick(textViewUrl.getText().toString());
        }

    }

}
