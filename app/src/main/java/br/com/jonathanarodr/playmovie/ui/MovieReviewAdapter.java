package br.com.jonathanarodr.playmovie.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.jonathanarodr.playmovie.R;
import br.com.jonathanarodr.playmovie.model.MovieReview;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewViewHolder> {

    private List<MovieReview> mReviews;

    public void setReviews(List<MovieReview> reviews) {
        this.mReviews = reviews;
        notifyDataSetChanged();
    }

    class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        private final TextView mAuthor;
        private final TextView mContent;

        MovieReviewViewHolder(View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author_review);
            mContent = itemView.findViewById(R.id.content_review);
        }

    }

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new MovieReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
        MovieReview review = mReviews.get(position);
        holder.mAuthor.setText(review.getAuthor().trim());
        holder.mContent.setText(review.getContent().trim());
    }

    @Override
    public int getItemCount() {
        return (mReviews == null) ? 0 : mReviews.size();
    }

}
