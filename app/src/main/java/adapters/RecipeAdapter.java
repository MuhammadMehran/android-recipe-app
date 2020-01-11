package adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import models.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private Context mContext;
    private RecipeListener recipeListener;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        mContext = context;
        this.recipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_row, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView titleLabel;
        ImageView thumbnail;
        ImageView overflow;

        public RecipeViewHolder(View itemView) {
            super(itemView);

            titleLabel = itemView.findViewById(R.id.titleLabel);
            thumbnail = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(v -> {
                if (recipeListener != null)
                    recipeListener.onShowRecipe(recipeList.get(getAdapterPosition()), new Pair[]{
                            Pair.create(thumbnail, "image_shared")
                    });
            });

        }

        public void bind(Recipe recipe) {
            titleLabel.setText(recipe.getName());
//            thumbnail.setImageURI(Uri.fromFile(new File(recipe.getImagePath())));
            Picasso.get().load(recipe.getImagePath()).into(thumbnail);
        }


    }

    public void setRecipeListener(RecipeListener recipeListener) {
        this.recipeListener = recipeListener;
    }

    public interface RecipeListener {
        void onShowRecipe(Recipe recipe, Pair<View, String>[] pairs);
        void onEditRecipe(Recipe recipe);
        void onDeleteRecipe(long recipeId);
    }
}
