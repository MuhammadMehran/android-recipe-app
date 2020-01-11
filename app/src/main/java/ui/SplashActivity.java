package ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.R;
import adapters.DatabaseAdapter;
import models.Direction;
import models.Ingredient;
import models.Recipe;
import models.User;
import utils.UserPreferences;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_TO_WRITE = 1;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseAdapter = DatabaseAdapter.getInstance(this);

        if (UserPreferences.isFirstRun(this)) {
            UserPreferences.setIsFirstRun(this, false);

            databaseAdapter.addNewUser(new User("testrun",
                    "test runner", "testrun@recipeapp.com", "password"));

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_TO_WRITE);
            } else {

                loadDefaultRecipes();
                navigateToMainPage();
            }
        } else {
            if (UserPreferences.isUserLoggedIn(this))
                navigateToMainPage();
            else
                navigateToMainPage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_TO_WRITE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    loadDefaultRecipes();
                else
                    Toast.makeText(this, "Permission denied to write default recipes.", Toast.LENGTH_LONG)
                            .show();

                navigateToMainPage();
                break;
        }
    }

    private void loadDefaultRecipes() {
        try {
            LoadRecipes();
            Toast.makeText(this, "created", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




    private void LoadRecipes() throws IOException {


        List<Ingredient> ingredients = new ArrayList<>();

        List<Direction> directions = new ArrayList<>();

        ingredients.add(new Ingredient("Milk powder 2 cups"));
        ingredients.add(new Ingredient("Sugar  1/2 cup or to taste"));
        ingredients.add(new Ingredient("Cream 200 ml"));
        ingredients.add(new Ingredient("Nuts as required"));
        directions.add(new Direction("In a heat resistant bowl add powder milk, cream, sugar and mix well."));
        directions.add(new Direction("Microwave it for 30 seconds and mix, again microwave it for 30 seconds and mix. Do not over cook it will become hard."));
        directions.add(new Direction("You can cook on stove as well."));
        directions.add(new Direction("Line a pan with cling wrap and pour in it, sprinkle nuts on top and press with hands."));
        directions.add(new Direction("Keep it in refrigerator for 1/2 hour, cut pieces and serve."));
        databaseAdapter.addNewRecipe(new Recipe("KALAKAND SWEET / MITHAI", "All", "KALAKAND SWEET / MITHAI", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2019/10/kalakand-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Chicken fillets 2 flattened"));
        ingredients.add(new Ingredient("Salt to tatse"));
        ingredients.add(new Ingredient("White pepper 1/2 tsp"));
        ingredients.add(new Ingredient("Black pepper 1/2 tsp"));
        ingredients.add(new Ingredient("Garlic paste 1/2 tsp"));
        ingredients.add(new Ingredient("Lemon juice 1 tbsp"));
        ingredients.add(new Ingredient("Worcestershire sauce 1 tbsp"));
        ingredients.add(new Ingredient("Oyster sauce 1 tbsp"));
        ingredients.add(new Ingredient("Chili sauce 1 tbsp"));
        ingredients.add(new Ingredient("Plain flour as required {for coating}"));
        ingredients.add(new Ingredient("Butter 2 tbsp"));
        ingredients.add(new Ingredient("Parsely as needed"));
        ingredients.add(new Ingredient("Garlic chopped 1/2 tsp"));
        ingredients.add(new Ingredient("Oil for frying"));
        ingredients.add(new Ingredient("White Sauce as needed"));
        ingredients.add(new Ingredient("Saute Vegetabels as needed"));
        directions.add(new Direction("Marinate chicken fillet with salt, pepper, mustard paste, egg, garlic and lemon juice, oyster sauce, worcestershire sauce and chili sauce  for 1/2 hour."));
        directions.add(new Direction("Coat fillets in dry flour and than dip in egg and shallow fry it."));
        directions.add(new Direction("Fry till tender but do not over cook."));
        directions.add(new Direction("Heat 1 tbsp butter in a pan add chopped garlic, parsely and 1/22 tsp white pepper. Stir and remove immediately and pour over chicken fillet."));
        directions.add(new Direction("Make a white sauce."));
        directions.add(new Direction("In a pan add 2 tsp butter and 1 tsp oil, heat and add 2 tbsp plain flour. Stir for few seconds."));
        directions.add(new Direction("Add salt and black pepper to taste and 3/4 cup milk. Stir till thick. You can add cheese too. (optional)"));
        directions.add(new Direction("Pour a spoon full sauce on steak and serve."));
        directions.add(new Direction("Serve steak with Fried Rice and Saute vegetables."));
        databaseAdapter.addNewRecipe(new Recipe("ARIZONA CHICKEN STEAK", "All", "ARIZONA CHICKEN STEAK", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2019/10/m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Chicken boiled and shredded  1 cup"));
        ingredients.add(new Ingredient("Onion chopped 1 medium "));
        ingredients.add(new Ingredient("Butter 1 tbsp"));
        ingredients.add(new Ingredient("Black pepper 1/2 tsp"));
        ingredients.add(new Ingredient("Chicken powder 1 tsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Red chili flakes 1 tsp or to taste"));
        ingredients.add(new Ingredient("Flour 2 tbsp"));
        ingredients.add(new Ingredient("Milk 3/4 cup"));
        ingredients.add(new Ingredient("Egg 1 beaten"));
        ingredients.add(new Ingredient("Sesame seeds as needed"));
        ingredients.add(new Ingredient("Puff pastry or pizza dough as needed"));
        directions.add(new Direction("In a pan add butter and onion, stir it for few seconds. Add flour and stir till raw smell remove."));
        directions.add(new Direction("Add chicken and mix well."));
        directions.add(new Direction("Now add chicken powder, salt, black pepper and crushed red chilies. Mix well."));
        directions.add(new Direction("Add chicken milk and stir well. Thick mixture will form."));
        directions.add(new Direction("Roll puff pastry or pizza dough."));
        directions.add(new Direction("Cut in squares."));
        directions.add(new Direction("Fold the corner and cut a strip as mentioned in pic."));
        directions.add(new Direction("Do this on opposite sides."));
        directions.add(new Direction("Place a spoon full of mixture in center and brush egg wash on all corners."));
        directions.add(new Direction("Overlap both sides."));
        directions.add(new Direction("Brush egg wash on top of pie, sprinkle sesame seeds."));
        directions.add(new Direction("Bake at 180c till done."));
        databaseAdapter.addNewRecipe(new Recipe("CHICKEN CREAMY PIE", "All", "CHICKEN CREAMY PIE", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2017/06/mince-pie-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Cooking Chocolate / Milk chocolate  200g"));
        ingredients.add(new Ingredient("Whipping cream 2 cups "));
        directions.add(new Direction("Add 1/2 cup cream in chocolate and microwave it. You can melt chocolate on double boiler too. Melt chocolate and mix well."));
        directions.add(new Direction("Now whip cream(in my cream vanilla and sugar was added). Whip cream till peaks form."));
        directions.add(new Direction("Fold chocolate in cream."));
        directions.add(new Direction("Fill in pipping bag and make small servings."));
        directions.add(new Direction("Dust cocoa and serve chill."));
        databaseAdapter.addNewRecipe(new Recipe("2 INGREDIENTS CHOCOLATE MOUSSE", "All", "2 INGREDIENTS CHOCOLATE MOUSSE", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2019/06/2-ingredient-mousse-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();
        ingredients.add(new Ingredient("Rice Basmati 1/2 kg"));
        ingredients.add(new Ingredient("Chicken  1/2 kg"));
        ingredients.add(new Ingredient("Red chili powder 1 tbsp"));
        ingredients.add(new Ingredient("Black pepper 1 tsp"));
        ingredients.add(new Ingredient("White pepper 1 tsp"));
        ingredients.add(new Ingredient("Turmeric powder 1 tsp"));
        ingredients.add(new Ingredient("Chopped green chilies 1 tbsp"));
        ingredients.add(new Ingredient("Whole Spices Mix 2 tbsp"));
        ingredients.add(new Ingredient("Dhania powder 1 -1/2 tbsp"));
        ingredients.add(new Ingredient("Jaifal / Javtri powder 1/4 tsp"));
        ingredients.add(new Ingredient("Ajino moto (optional) 1 tbsp"));
        ingredients.add(new Ingredient("All spice powder 1 tbsp"));
        ingredients.add(new Ingredient("Cumin powder 1 tbsp"));
        ingredients.add(new Ingredient("Ginger garlic paste 1 -1/2 tbsp"));
        ingredients.add(new Ingredient("Zarda / orange food colour 1/2 tsp"));
        ingredients.add(new Ingredient("Kewra water 1 tsp"));
        ingredients.add(new Ingredient("Tomato paste 4 tomatoes"));
        ingredients.add(new Ingredient("Brown onion 1 cup"));
        ingredients.add(new Ingredient("Yogurt  1 cup"));
        ingredients.add(new Ingredient("Green chillies 3-4"));
        ingredients.add(new Ingredient("Green coriander  1/2 cup"));
        ingredients.add(new Ingredient("Min leaves 1/2 cup"));
        ingredients.add(new Ingredient("Biryani essence 1/2 tsp"));
        directions.add(new Direction("Soak rice for 20 minutes."));
        directions.add(new Direction("Boil rice and add salt, biryani essence, whole spices, Oil 2tbsp and vinegar 1 tbsp. Add rice and cook till half done . Now run cold water from rice to stop cooking process of rice."));
        directions.add(new Direction("Set aside."));
        directions.add(new Direction("You can cook Chicken gravy and than boil rice. Its up to you."));
        directions.add(new Direction("In a pot add 1 cup oil and add ginger garlic paste, saute for few minutes and add tomato paste."));
        directions.add(new Direction("Stir for few minutes and add chicken, stir."));
        directions.add(new Direction("Add salt to taste, Yogurt, red chili powder, ajino moto, cumin powder, coriander powder, turmeric powder, black pepper, white pepper, green chilli chopped, jaifal javitri, kewra essence, zarda colour, mix all well. let it cook for 5 minutes."));
        directions.add(new Direction("Add coriander and mint leaves and 1 cup water. Cook on high flame and add brown onion."));
        directions.add(new Direction("Cook till gravy become thick. Let water dry and add all spice powder and green chilies. Mix well. Do not over cook chicken."));
        directions.add(new Direction("Spread Boiled rice on top of gravy and cover with lid for 10-15 minutes."));
        directions.add(new Direction("Let it simmer on low flame."));
        directions.add(new Direction("Mix and serve with Raita and salad."));
        databaseAdapter.addNewRecipe(new Recipe("KARACHI FAMOUS STUDENT BIRYANI", "All", "KARACHI FAMOUS STUDENT BIRYANI", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/10/biryani-m1.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Any Cake  250 g or as required"));
        ingredients.add(new Ingredient("Fruits, seasonal or use cocktail fruit  1 can or as required"));
        ingredients.add(new Ingredient("Dry fruits (raisins, almond, pistachio) as required"));
        ingredients.add(new Ingredient("Jelly 1 packet, prepare and set"));
        ingredients.add(new Ingredient("Desiccated coconut 4 tbsp"));
        ingredients.add(new Ingredient("Cream 250 g, whipped"));
        ingredients.add(new Ingredient("Fresh milk 1 cup"));
        ingredients.add(new Ingredient("Jam e shiren 2 tbsp"));
        ingredients.add(new Ingredient("Condensed milk 2 tbsp"));
        directions.add(new Direction("In 1 cup milk add Jam-e-shiren 2 tbsp and condensed milk, mix well. Set aside."));
        directions.add(new Direction("In a bowl or any serving dish pour prepared milk, 2-3 tbsp and rotate bowl, this process will set the cake with walls of the bowl."));
        directions.add(new Direction("Arrange cake slices on the bottom and sides of the bowl."));
        directions.add(new Direction("Cut fruits in small cubes or take cocktail tin, spread on cake layer."));
        directions.add(new Direction("Sprinkle raisin, almond, pistachio and a layer of jelly."));
        directions.add(new Direction("Again a layer of cake and sprinkle milk, spread fruit and desiccated coconut."));
        directions.add(new Direction("Now on top of fruits pour milk with spoon."));
        directions.add(new Direction("Beat cream till fluffy."));
        directions.add(new Direction("Spread cream on top."));
        directions.add(new Direction("Garnish with sprinkles."));
        directions.add(new Direction("Serve chill."));
        databaseAdapter.addNewRecipe(new Recipe("FRUIT PUNCH DELIGHT", "All", "FRUIT PUNCH DELIGHT", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/05/fruit-punch-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Flour 1 cup"));
        ingredients.add(new Ingredient("Poppy seeds 1/4 cup or 2 tbsp"));
        ingredients.add(new Ingredient("Sesame seeds 1 tbsp"));
        ingredients.add(new Ingredient("Fennel seeds 1 tbsp"));
        ingredients.add(new Ingredient("Brown sugar 1/2 cup"));
        ingredients.add(new Ingredient("Oil 2 tbsp"));
        ingredients.add(new Ingredient("Baking Soda 1/4 tsp/ pinch"));
        ingredients.add(new Ingredient("Cardamom  crushed 2-3"));
        directions.add(new Direction("Add all ingredients in a bowl and mix."));
        directions.add(new Direction("Knead with water or milk."));
        directions.add(new Direction("Set aside for few minutes."));
        directions.add(new Direction("Dust flour on surface and roll the dough."));
        directions.add(new Direction("Cut rounds from cutter or cover of bottle and fry. I cut them in diamond shape. In my video I have demonstrated, so you can check it in video."));
        directions.add(new Direction("After frying place on paper towel and let it cool."));
        directions.add(new Direction("Enjoy with tea."));
        databaseAdapter.addNewRecipe(new Recipe("MEETHI TIKKIYAN OR PORIAN", "All", "MEETHI TIKKIYAN OR PORIAN", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/04/tukkiyan-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Beef Mince  1 kg"));
        ingredients.add(new Ingredient("Yogurt 1/2 cup"));
        ingredients.add(new Ingredient("Ginger garlic paste 1 and 1/2 tbsp"));
        ingredients.add(new Ingredient("Meat tenderizer or raw papaya paste 1 tbsp"));
        ingredients.add(new Ingredient("Gram flour/ basan 3 tbsp"));
        ingredients.add(new Ingredient("Red chili powder 2 tbsp"));
        ingredients.add(new Ingredient("Red chili flakes  1 tbsp"));
        ingredients.add(new Ingredient("Turmeric powder 1 tsp"));
        ingredients.add(new Ingredient("Coriander powder 2 tbsp"));
        ingredients.add(new Ingredient("Freshly grounded black pepper 1 tbsp"));
        ingredients.add(new Ingredient("All spice powder 1 tbsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Oil/ ghee/ butter 1 cup"));
        ingredients.add(new Ingredient("Onion, chopped 1 small"));
        directions.add(new Direction("Marinate mince with yogurt, meat tenderizer and ginger garlic paste. Keep it for 1/2 an hour."));
        directions.add(new Direction("Now on a pan or tawa toast gram flour, add in mince."));
        directions.add(new Direction("Add all spices in mince and mix with hand."));
        directions.add(new Direction("Leave it for 1/2 more hour. I normally marinate it overnight for smooth texture."));
        directions.add(new Direction("Now in a pot add oil and onion. In original recipe generous amount of oil was used but I have used according to my need."));
        directions.add(new Direction("Fry onion till light brown and add mince in it. Stir for few minutes."));
        directions.add(new Direction("Now cover with the lid and cook on very low flame till oil separate."));
        directions.add(new Direction("In the end Give smoke of charcoal."));
        directions.add(new Direction("Garnish with green chilies and coriander."));
        directions.add(new Direction("Serve with Naan or parathas along with Raita and salad."));
        databaseAdapter.addNewRecipe(new Recipe("BURNS ROAD’S WAHEED KE KEBAB", "All", "BURNS ROAD’S WAHEED KE KEBAB", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/02/waheed-ke-kebab-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Semolina / whole wheat flour (chaki ka atta) 1 cup"));
        ingredients.add(new Ingredient("Desi ghee/ Un salted Butter 1/2 cup or 3/4 cup"));
        ingredients.add(new Ingredient("Cardamom 3-4"));
        ingredients.add(new Ingredient("Gond ka tera 3-4 tbsp. (powder)"));
        ingredients.add(new Ingredient("Milk 1 cup"));
        ingredients.add(new Ingredient("Sugar 3/4 cup"));
        ingredients.add(new Ingredient("Coconut  3 tbsp"));
        ingredients.add(new Ingredient("Mixed nuts as required"));
        directions.add(new Direction("Fry gond in ghee (2 tbsp.) It will pop and become crispy. Place on paper towel and let it cool. Grind it and set aside."));
        directions.add(new Direction("In ghee add semolina/ Atta and cardamom, fry till light golden color appear."));
        directions.add(new Direction("Now add all crushed nuts and sugar. Mix well."));
        directions.add(new Direction("Add gond powder and mix well."));
        directions.add(new Direction("Add Milk and stir."));
        directions.add(new Direction("Keep on mixing till ghee separates."));
        directions.add(new Direction("Add nuts and serve hot."));
        directions.add(new Direction("You can store in a air tight container  and keep in refrigerator."));
        databaseAdapter.addNewRecipe(new Recipe("GOND KA HALWA", "All", "GOND KA HALWA", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/02/GOND-HALWA-M.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Rice  2 cups"));
        ingredients.add(new Ingredient("Eggs  2"));
        ingredients.add(new Ingredient("Garlic  2 cloves, chopped"));
        ingredients.add(new Ingredient("Yellow food color, optional pinch"));
        ingredients.add(new Ingredient("Milk/ water 1 tsp"));
        ingredients.add(new Ingredient("Capsicum 1 large"));
        ingredients.add(new Ingredient("Cabbage 1 cup"));
        ingredients.add(new Ingredient("Carrot 1/4 cup"));
        ingredients.add(new Ingredient("Spring onion few leaves"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Chicken powder 1 tbsp"));
        ingredients.add(new Ingredient("Chinese salt, optional 1 tbsp"));
        ingredients.add(new Ingredient("Black pepper 1 tsp"));
        ingredients.add(new Ingredient("Sugar  1 tsp"));
        ingredients.add(new Ingredient("Vinegar 1 tbsp"));
        ingredients.add(new Ingredient("Soya sauce 1-2 tbsp"));
        ingredients.add(new Ingredient("Oil 1/2 cup"));
        directions.add(new Direction("Boil Rice and let them cool completely."));
        directions.add(new Direction("Beat egg with milk or water and pinch of yellow food color."));
        directions.add(new Direction("In a wok add oil and heat it. Cook on high flame now."));
        directions.add(new Direction("Add chopped ginger and stir, add eggs and stir."));
        directions.add(new Direction("Now add vegetables and stir fast."));
        directions.add(new Direction("Add all spices and keep on mixing."));
        directions.add(new Direction("Add Rice and stir."));
        directions.add(new Direction("Before serving sprinkle green part of spring onion and serve hot."));
        databaseAdapter.addNewRecipe(new Recipe("EGG FRIED RICE- RESTAURANT STYLE", "All", "EGG FRIED RICE- RESTAURANT STYLE", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/01/fried-rice-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Chicken Breast fillet, cut in long strips 2 nos"));
        ingredients.add(new Ingredient("Chicken powder 1 tsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("White pepper 1/2 tsp"));
        ingredients.add(new Ingredient("Egg 1"));
        ingredients.add(new Ingredient("Plain flour 2tbsp"));
        ingredients.add(new Ingredient("Corn flour 2 tbsp"));
        ingredients.add(new Ingredient("Chili oil 1/2 cup or adjust accordin to your taste buds"));
        ingredients.add(new Ingredient("Garlic, chopped 3 tbsp."));
        ingredients.add(new Ingredient("Honey 1 tbsp"));
        ingredients.add(new Ingredient("Chili powder 1 tsp"));
        ingredients.add(new Ingredient("Brown sugar 1 tsp"));
        ingredients.add(new Ingredient("Vinegar 2 tbsp"));
        ingredients.add(new Ingredient("Chili garlic sauce 1/4 cup"));
        ingredients.add(new Ingredient("Red food color pinch"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Sesame seeds 1 tbsp"));
        ingredients.add(new Ingredient("Oil 3 cups"));
        ingredients.add(new Ingredient("Red chili powder 1/2 cup"));
        ingredients.add(new Ingredient("Red chili flakes 1/2 cup"));
        directions.add(new Direction("Marinate Chicken with all ingredients."));
        directions.add(new Direction("Set aside."));
        directions.add(new Direction("Just before serving fry it in oil and add in Sauce."));
        databaseAdapter.addNewRecipe(new Recipe("DRAGON CHICKEN- RESTAURANT STYLE", "All", "DRAGON CHICKEN- RESTAURANT STYLE", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/01/Dragon-chicken-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Powdered milk 2 cups"));
        ingredients.add(new Ingredient("Water 1/2 cup"));
        ingredients.add(new Ingredient("Ghee 1/2 cup"));
        directions.add(new Direction("In a pan add ghee and water and let it cook on low flame till ghee melts."));
        directions.add(new Direction("Add powdered milk and mix well. A thick paste will form, keep on mixing."));
        directions.add(new Direction("Remove from stove and keep in refrigerator for few minutes."));
        directions.add(new Direction("Ready to use…"));
        databaseAdapter.addNewRecipe(new Recipe("HOME MADE KHOYA", "All", "HOME MADE KHOYA", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/01/khoya-m.jpg"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Mash ki Daal 1 cup"));
        ingredients.add(new Ingredient("Black cardamom 1"));
        ingredients.add(new Ingredient("Cinamon stick 1 inch"));
        ingredients.add(new Ingredient("Cloves 4-5"));
        ingredients.add(new Ingredient("Onion 1 large and sliced"));
        ingredients.add(new Ingredient("Ginger garlic paste 1 tbsp"));
        ingredients.add(new Ingredient("Red chili powder 1 and 1/2 tsp"));
        ingredients.add(new Ingredient("Turmeric powder 1/2 tsp"));
        ingredients.add(new Ingredient("Coriander powder 1 tsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Chicken cube 1"));
        ingredients.add(new Ingredient("Oil 1/2 cup"));
        ingredients.add(new Ingredient("Green chilies 3-4"));
        ingredients.add(new Ingredient("Coriander leaves few"));
        ingredients.add(new Ingredient("Ginger sticks  for garnish"));
        ingredients.add(new Ingredient("All spice powder for garnish"));
        directions.add(new Direction("Soak Daal in warm water for 1 hour."));
        directions.add(new Direction("Boil it with 4-5 cloves, 1 black cardamom and a cinnamon stick, till half cook. Strain it and set aside."));
        directions.add(new Direction("In a pot add oil and sliced onion, fry till light brown. Add ginger garlic paste and stir for few seconds."));
        directions.add(new Direction("Add salt, red chili powder, turmeric powder, coriander powder and little water, keep on frying till oil separates and onion dissolve. (you can add one small tomato, if you like)"));
        directions.add(new Direction("Add Daal in it mix well and add water just to cook daal. Add Chicken cube and cover the lid."));
        directions.add(new Direction("Cook on low flame till Daal completely cooked and water evaporates."));
        directions.add(new Direction("Now Fry for few seconds and add green chilies ,coriander and Kasuri methi (few leaves). Simmer for few seconds."));
        databaseAdapter.addNewRecipe(new Recipe("DHABA STYLE MASH KI DAAL", "All", "DHABA STYLE MASH KI DAAL", ingredients, directions, "http://www.ainycooks.com/wp-content/uploads/2018/01/dhaba-dal-m.jpg"));



        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Cottage cheese 200 gm"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Paprika ½ tsp"));
        ingredients.add(new Ingredient("Oregano ¼ tsp"));
        ingredients.add(new Ingredient("Cumin powder ½ tsp"));
        ingredients.add(new Ingredient("White flour 1 tbsp"));
        ingredients.add(new Ingredient("Corn flour 2 tbsp"));
        ingredients.add(new Ingredient("Milk ½ cup"));
        ingredients.add(new Ingredient("Breadcrumbs as required"));
        ingredients.add(new Ingredient("Oil for shallow fry"));
        directions.add(new Direction("Marinate the cheese with salt, paprika, oregano and cumin powder."));
        directions.add(new Direction("Mix white flour, corn flour, salt and milk to make smooth batter."));
        directions.add(new Direction("Sprinkle flour on cheese slices."));
        directions.add(new Direction("Then coat with batter and breadcrumbs."));
        directions.add(new Direction("Shallow fry cheese in oil until crusted from both side."));
        databaseAdapter.addNewRecipe(new Recipe("Crusted Cottage Cheese", "Popular", "Crusted Cottage Cheese", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/c954d3dd-367b-4dee-9cc4-f16980c6cf64-Crusted Cottage Cheese.jpg&w=250&h=250"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Boneless chicken breast 3 (skinless)"));
        ingredients.add(new Ingredient("Bread crumbs 1 cup"));
        ingredients.add(new Ingredient("Parmesan cheese ½ cup (grated)"));
        ingredients.add(new Ingredient("Salt 1 tsp or to taste"));
        ingredients.add(new Ingredient("Dried thyme 1 tsp"));
        ingredients.add(new Ingredient("Dried basil 1 tsp"));
        ingredients.add(new Ingredient("Butter ½ cup (melted)"));
        directions.add(new Direction("Preheat oven to 200 degrees C."));
        directions.add(new Direction("Cut chicken breasts into 1 1/2-inch pieces."));
        directions.add(new Direction("In a bowl, combine the bread crumbs, cheese, salt, thyme and basil."));
        directions.add(new Direction("Put melted butter in a bowl."));
        directions.add(new Direction("First dip chicken pieces into the melted butter, then coat with the breadcrumb mixture."));
        directions.add(new Direction("Place chicken pieces on greased baking tray in a single layer and bake in the preheated oven for 20 minutes."));
        databaseAdapter.addNewRecipe(new Recipe("Baked Chicken Nuggets", "Popular", "Baked Chicken Nuggets", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/198709d4-f802-44a7-844e-0a133f9df813-Baked Chicken Nuggets.jpg&w=250&h=250"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Potatoes (boiled & mashed) 250 grams"));
        ingredients.add(new Ingredient("Eggs (boiled & chopped) 4"));
        ingredients.add(new Ingredient("Onion (chopped) half cup"));
        ingredients.add(new Ingredient("Spring onions (chopped) half cup"));
        ingredients.add(new Ingredient("Coriander leaves (chopped) 2 tbsp"));
        ingredients.add(new Ingredient("Red pepper (crushed) 1 ½ tsp"));
        ingredients.add(new Ingredient("Green chilies (chopped) 4"));
        ingredients.add(new Ingredient("Garam masala powder 1/2 tsp"));
        ingredients.add(new Ingredient("Salt 1 tsp"));
        ingredients.add(new Ingredient("Bread slices (soaked in water and squeezed) 4"));
        ingredients.add(new Ingredient("Eggs 2"));
        ingredients.add(new Ingredient("Bread Crumbs for coating"));
        ingredients.add(new Ingredient("Oil for frying"));
        directions.add(new Direction("In a bowl mix together mashed potato, chopped eggs, onion, spring onion, coriander leaves, red pepper, green chilies, garm masala, salt, squeezed bread slice and one egg."));
        directions.add(new Direction("Make round kabab, dip in other egg, coat bread crumbs."));
        directions.add(new Direction("Heat oil in a frying pan and fry in hot oil until golden from both sides."));
        databaseAdapter.addNewRecipe(new Recipe("Anday kay Kabab", "Popular", "Anday kay Kabab", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/016dfd71-8b0c-4921-8f7b-2f267f076de0-Anday kay kabab.jpg&w=250&h=250"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Chicken Mince (Double Grinded)    ½ kg"));
        ingredients.add(new Ingredient("Ginger Garlic Paste        1 tbsp"));
        ingredients.add(new Ingredient("Onion                 1"));
        ingredients.add(new Ingredient("Crushed Black Pepper         ½ tsp"));
        ingredients.add(new Ingredient("Green Cardamoms (Crushed Seeds)    1/3 tsp"));
        ingredients.add(new Ingredient("White Cumin Powder         ½ tsp"));
        ingredients.add(new Ingredient("Black Cumin Powder         ½ tsp"));
        ingredients.add(new Ingredient("Paprika Powder            1/3 tsp"));
        ingredients.add(new Ingredient("Cinnamon Powder            1/3tsp"));
        ingredients.add(new Ingredient("White Pepper Powder         1/3 tsp"));
        ingredients.add(new Ingredient("Cream                 2-3 tbsp"));
        ingredients.add(new Ingredient("Hung Yogurt             2-3 tbsp"));
        ingredients.add(new Ingredient("Baking Powder             1 pinch"));
        ingredients.add(new Ingredient("Finely Chopped Green Chilies     5-6"));
        ingredients.add(new Ingredient("Oil                 2-3 tbsp"));
        ingredients.add(new Ingredient("Salt                 to taste"));
        directions.add(new Direction("Take a chopper add chicken mince (double ground, ginger garlic paste, onion, crushed black pepper, green cardamoms(crushed seeds), white cumin powder, black cumin powder, paprika powder, white pepper powder, cream, yogurt, baking powder, finely chopped green chilies and salt mix it and chop."));
        directions.add(new Direction("Now grease the hands with oil and make balls out of mince and put on skewer or rods and shape them like a kabab."));
        directions.add(new Direction("Cook on coals, or bake it in a oven or fry in a pan then smoke with coals."));
        directions.add(new Direction("When it’s ready take it out in a serving platter and garnish with vegetables and serve it."));
        databaseAdapter.addNewRecipe(new Recipe("Chicken Reshmi Kabab ", "Popular", "Chicken Reshmi Kabab ", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/658c9898-5e49-4570-ab77-b2b5663e6f4f-Chicken Reshmi Kabab.jpg&w=250&h=250"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Firm tofu 1 pack (bite sized cube)"));
        ingredients.add(new Ingredient("Red onion 1 medium (diced)"));
        ingredients.add(new Ingredient("Capsicum 1 medium (diced)"));
        ingredients.add(new Ingredient("Tomato 1 (seed and center part removed, diced)"));
        ingredients.add(new Ingredient("Oil as required"));
        ingredients.add(new Ingredient("For Marination"));
        ingredients.add(new Ingredient("Thick yogurt ½ cup"));
        ingredients.add(new Ingredient("Red chili powder ½ tsp"));
        ingredients.add(new Ingredient("Cumin powder ½ tsp"));
        ingredients.add(new Ingredient("Garam masala powder ¼ tsp"));
        ingredients.add(new Ingredient("Kasoori methi ½ tsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Oil 1 tbsp"));
        ingredients.add(new Ingredient("Lemon juice ½ tsp"));
        directions.add(new Direction("Press tofu with the help of plate to remove moisture and leave for 30-40 minutes, then cut in cubes."));
        directions.add(new Direction("In a mixing bowl, mix all marination ingredients except kasoori methi."));
        directions.add(new Direction("In marination add tofu, onion, capsicum and tomato. Mix gently to coat everything. Cover and leave for 1 hour."));
        directions.add(new Direction("Now put tofu and vegetables on wooden skewers one by one and broil at 180 degree C until golden brown. Or drizzle oil in frying pan and fry."));
        directions.add(new Direction("Serve hot."));
        databaseAdapter.addNewRecipe(new Recipe("Tofu Tikka", "Popular", "Tofu Tikka", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/46448877-94a8-49f7-9721-c177ba8c0ca9-Tofu Tikka.jpg&w=250&h=250"));




        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Potatoes 2"));
        ingredients.add(new Ingredient("Garlic chopped 1 tsp"));
        ingredients.add(new Ingredient("Carom seeds (roasted & ground) ½ tsp"));
        ingredients.add(new Ingredient("Chili powder ½ tsp"));
        ingredients.add(new Ingredient("Cumin seeds (roasted & ground) ½ tsp"));
        ingredients.add(new Ingredient("Salt ½ tsp"));
        ingredients.add(new Ingredient("Corn flour 2 tbsp"));
        ingredients.add(new Ingredient("Oil for frying"));
        directions.add(new Direction("Cut potatoes into fries, wash and dry with a napkin."));
        directions.add(new Direction("Now marinate with chopped garlic, carom seeds, chili powder, cumin seeds, salt and corn flour."));
        directions.add(new Direction("Pack the fries in a polythene bag and freeze it."));
        directions.add(new Direction("For frying, deep fry in oil till golden brown and crisp. Fries are ready."));
        databaseAdapter.addNewRecipe(new Recipe("Frozen French Fries", "New", "Frozen French Fries", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/e02963db-be5a-467c-8f25-5ccfacc261e8-frozen french fries.jpg&w=250&h=250"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Chicken 1 kg"));
        ingredients.add(new Ingredient("Eggs 2"));
        ingredients.add(new Ingredient("All purpose flour 3 tbsp"));
        ingredients.add(new Ingredient("Cornflour 5 tbsp"));
        ingredients.add(new Ingredient("Baking Soda 1/2 tsp"));
        ingredients.add(new Ingredient("Ginger garlic paste 1-1/2 tbsp"));
        ingredients.add(new Ingredient("Red Chili powder 1-1/2 tbsp"));
        ingredients.add(new Ingredient("Vinegar 2 tbsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Cornflakes as required"));
        directions.add(new Direction("Take a bowl add chicken then mix all the ingredients together else cornflakes."));
        directions.add(new Direction("Marinate for 1 hour"));
        directions.add(new Direction("Grind the cornflakes into a powder form."));
        directions.add(new Direction("Take a piece of chicken, roll in the cornflakes and deep fry."));
        directions.add(new Direction("Serve hot with tomato ketchup or chillie sauce."));
        databaseAdapter.addNewRecipe(new Recipe("Crispy Chicken", "New", "Crispy Chicken", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/e3355c81-faa1-487e-8e2d-a179e32830c2-chicken.jpg&w=250&h=250"));


        ingredients = new ArrayList<>();

        directions = new ArrayList<>();
        ingredients.add(new Ingredient("Mix fruits 1 bowl (cubed)"));
        ingredients.add(new Ingredient("Sugar 2 tbsp"));
        ingredients.add(new Ingredient("Chaat masala as required"));
        ingredients.add(new Ingredient("A pinch of salt"));
        ingredients.add(new Ingredient("Lemon 1 nos"));
        ingredients.add(new Ingredient("Red syrup 3 tbsp"));
        directions.add(new Direction("Put fruits in a bowl ."));
        directions.add(new Direction("Add sugar, chat masala, lemon juice and salt."));
        directions.add(new Direction("Put a cover on the bowl and toss well up and down without using a spoon."));
        directions.add(new Direction("Drop red syrup on top. Chill and serve."));
        databaseAdapter.addNewRecipe(new Recipe("Fruit Chaat", "New", "Fruit Chaat", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/9c3551cb-e34a-4b9c-b0e8-5e038d8a297b-Fruit Chaat.jpg&w=250&h=250"));

        ingredients = new ArrayList<>();

        directions = new ArrayList<>();

        ingredients.add(new Ingredient("Yeast 1 tbsp"));
        ingredients.add(new Ingredient("Sugar 1 tsp"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Flour 250 gm"));
        ingredients.add(new Ingredient("Egg 1"));
        ingredients.add(new Ingredient("Butter 2 tbsp"));
        ingredients.add(new Ingredient("Milk ½ cup"));
        ingredients.add(new Ingredient("Oil 2 tbsp"));
        ingredients.add(new Ingredient("Ginger garlic paste 1 tbsp"));
        ingredients.add(new Ingredient("Mince 250 gm"));
        ingredients.add(new Ingredient("Onion 1 (chopped)"));
        ingredients.add(new Ingredient("Coriander leaves ¼ bunch"));
        ingredients.add(new Ingredient("Green chilies 2 (chopped)"));
        ingredients.add(new Ingredient("Salt to taste"));
        ingredients.add(new Ingredient("Black pepper ¼ tsp"));
        ingredients.add(new Ingredient("Water as required"));
        directions.add(new Direction("Dissolve yeast in little lukewarm milk, keep aside."));
        directions.add(new Direction("Now add in sugar, salt, flour, egg, butter and remaining milk, mix well and knead to dough with water as required."));
        directions.add(new Direction("Now cover the dough with plastic foil, keep aside till rise and doubled."));
        directions.add(new Direction("Heat oil in a wok, add ginger garlic paste and mince, fry well till water dries."));
        directions.add(new Direction("Then add onion, coriander leaves, green chilies, salt and black pepper powder."));
        directions.add(new Direction("Fry well, then remove from fire."));
        directions.add(new Direction("Make small balls with the dough, stuff each ball with the prepared mince."));
        directions.add(new Direction("Again make balls and keep aside to rise."));
        directions.add(new Direction("Grease an oven proof tray with a little oil, put each bun on greased tray."));
        directions.add(new Direction("Brush top with beaten egg."));
        directions.add(new Direction("Lastly bake in a preheated oven on 180 degrees for 10 – 15 minutes."));
        directions.add(new Direction("Then remove from oven and serve."));
        databaseAdapter.addNewRecipe(new Recipe("Beef Bun", "New", "Beef Bun", ingredients, directions, "http://www.khanapakana.com/ShowImage.aspx?img=/recipe/articlefiles/57b8263d-e204-4659-9ad0-031b4739ce3b-Beef Bun.jpg&w=250&h=250"));

    }



    private void navigateToMainPage() {
        Intent startIntent = new Intent(this, MainActivity.class);
        startActivity(startIntent);
        finish();
    }
}
