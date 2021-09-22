package com.company;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class to build json files from .txt files, and .txt files from .json files.
 */
public class BuildFile {

    //TODO: save recipes as .txt
    //TODO: CLEAN UP, REFACTOR, OPTIMIZE
    //TODO: MAKE DIRECTORIES somewhere, I guess?


    /**
     * Checks if a given directory exists.
     *
     * @param filePath
     * @return boolean
     */
    public static boolean directoryExists(String filePath){ //TODO: may be redundant

        Path path = Paths.get(filePath).toAbsolutePath();

        if(Files.exists(path) && Files.isDirectory(path)) {
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Checks if a json file exists for the given recipe ID.
     * If the filepath given leads to a text file, the method checks the directory of
     * the given file for the existence of the json file.
     *
     * ex) if filePath = "recipe_folder/recipe_0001.txt" and recipeID = "1234"
     *
     *     then the method will check if "recipe_1234.json" exists inside the "recipe_folder" directory.
     *
     *     If filePath leads to a directory, then that directory will be checked.
     *
     * @param filePath
     * @param recipeID
     * @return boolean -- true if the file exists, false if not
     */
    public static boolean JsonExists(String filePath, String recipeID) { // TODO: BUG RISK, AM I REDUNDANT?

        // replace spaces with underscores.
        String rID = recipeID.replaceAll("\\s+", "_").toLowerCase();

        String jsonFile = "recipe_" + rID + ".json";

        //String jsonFile = "recipe_" + recipeID + ".json";
        Path path1 = Paths.get(filePath).toAbsolutePath();

        if (Files.exists(path1)) {
            if (Files.isDirectory(path1)) {

                System.out.println("Searching directory...");

                //String nuPath = String.valueOf(path1) + "/" + jsonFile;
                String nuPath = String.valueOf(path1) + File.separator + jsonFile;

                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    System.out.println("A .json file exists for recipe ID: " +recipeID);
                    return true;
                }
                // else{ System.out.println("Could not locate .json file for recipe ID: " +recipeID); }

            } else if (!Files.isDirectory(path1)) {

                System.out.println("The path given is to a file and not a directory. Searching parent directory...");

                // get directory
                String dir = String.valueOf(Paths.get(filePath).toAbsolutePath().getParent());
                //String nuPath = dir + "/" + jsonFile;
                String nuPath = dir + File.separator + jsonFile;

                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    System.out.println("A .json file exists for recipe ID: " +recipeID);
                    return true;
                }
            }
        } else { System.out.println(path1 + " does not exist."); }

        System.out.println("Could not locate .json file for: " +recipeID+" with the given path: " + path1);

        return false;
    }


    // TODO: BUG RISK
    /**
     * Checks if a .json or .txt file exists for the given recipe ID.
     * If the filepath given doesn't lead to a directory, the method checks the directory of
     * the given file for the existence of the recipe file.
     *
     * ex) if filePath = "recipe_folder/recipe_0001.txt" and recipeID = "1234"
     *
     *     then the method will check if "recipe_1234.json" or "recipe_1234.json"
     *     exists inside the "recipe_folder" directory.
     *
     *     If filePath leads to a directory, then that directory will be checked.
     *
     * @param filePath
     * @param recipeID
     * @return boolean -- true if the file exists, false if not
     */
    public static boolean RecipeExists(String filePath, String recipeID) {

        // replace spaces with underscores.
        String rID = recipeID.replaceAll("\\s+", "_").toLowerCase();

        String jsonFile = "recipe_" + rID + ".json";
        String txtFile = "recipe_" + rID + ".txt";

        Path path1 = Paths.get(filePath).toAbsolutePath();

        if (Files.exists(path1)) {
            if (Files.isDirectory(path1)) {

                //System.out.println("Searching directory...");

                String jpathstr = path1 + File.separator + jsonFile;
                String txtpathstr = path1 + File.separator + txtFile;

                Path jpath = Paths.get(jpathstr).toAbsolutePath();
                Path tpath = Paths.get(txtpathstr).toAbsolutePath();

                if (Files.exists(jpath) && !Files.isDirectory(jpath)) {
                    //System.out.println("A .json file exists for recipe ID: " +recipeID);
                    return true;
                }

                if(Files.exists(tpath) && !Files.isDirectory(tpath)){
                    //System.out.println("A .txt file exists for recipe ID: " +recipeID);
                    return true;
                }

            } else if (!Files.isDirectory(path1)) {

                //System.out.println("The path given is to a file and not a directory. Searching parent directory...");

                // get directory
                String dir = String.valueOf(Paths.get(filePath).toAbsolutePath().getParent());

                String jpathstr = dir + File.separator + jsonFile;
                String txtpathstr = dir + File.separator + txtFile;

                Path jpath = Paths.get(jpathstr).toAbsolutePath();
                Path tpath = Paths.get(txtpathstr).toAbsolutePath();

                if (Files.exists(jpath) && !Files.isDirectory(jpath)) {
                    //System.out.println("A .json file exists for recipe ID: " +recipeID);
                    return true;
                }

                if(Files.exists(tpath) && !Files.isDirectory(tpath)){
                    //System.out.println("A .txt file exists for recipe ID: " +recipeID);
                    return true;
                }
            }
        } else {
            System.out.println(path1 + " does not exist.");
        }

        System.out.println("Could not locate .json or .txt file for: " +recipeID+" with the given path: " + path1);

        return false;
    }


    /**
     * Checks if a .json or .txt file exists for the given recipe ID and returns the recipe.
     *
     * If the filepath given doesn't lead to a directory, the method checks the directory of
     * the given file for the existence of the recipe file.
     *
     * ex)
     * if filePath = "recipe_folder/recipe_0001.txt" and recipeID = "1234"
     * then the method will check if "recipe_1234.json" or "recipe_1234.json"
     * exists inside the "recipe_folder" directory.
     *
     * If filePath leads to a directory, then that directory will be checked.
     *
     * @param filePath
     * @param recipeID
     * @return null or a recipe, if it exists
     */
    public static Recipe RecipeFromID(String filePath, String recipeID) {

        // replace spaces with underscores.
        String rID = recipeID.replaceAll("\\s+", "_").toLowerCase();

        String jsonFile = "recipe_" + rID + ".json";
        String txtFile = "recipe_" + rID + ".txt";

        Path path1 = Paths.get(filePath).toAbsolutePath();

        Recipe r = null;

        if (Files.exists(path1)) {
            if (Files.isDirectory(path1)) {

                //System.out.println("Searching directory...");

                String jpathstr = path1 + File.separator + jsonFile;
                String txtpathstr = path1 + File.separator + txtFile;

                Path jpath = Paths.get(jpathstr).toAbsolutePath();
                Path tpath = Paths.get(txtpathstr).toAbsolutePath();

                if (Files.exists(jpath) && !Files.isDirectory(jpath)) {
                    //System.out.println("A .json file exists for recipe ID: " +recipeID);

                    try {
                        r = new Recipe(jpathstr);
                    } catch (IOException | JsonException e) {
                        // e.printStackTrace();
                    }
                }

                if (Files.exists(tpath) && !Files.isDirectory(tpath)) {

                    try {
                        r = new Recipe(txtpathstr);
                    } catch (IOException | JsonException e) {
                        // e.printStackTrace();
                    }
                }

            } else if (!Files.isDirectory(path1)) {

                //System.out.println("The path given is to a file and not a directory. Searching parent directory...");

                // get directory
                String dir = String.valueOf(Paths.get(filePath).toAbsolutePath().getParent());

                String jpathstr = dir + File.separator + jsonFile;
                String txtpathstr = dir + File.separator + txtFile;

                Path jpath = Paths.get(jpathstr).toAbsolutePath();
                Path tpath = Paths.get(txtpathstr).toAbsolutePath();

                if (Files.exists(jpath) && !Files.isDirectory(jpath)) {
                    try {
                        r = new Recipe(jpathstr);
                    } catch (IOException | JsonException e) {
                        // e.printStackTrace();
                    }
                }

                if (Files.exists(tpath) && !Files.isDirectory(tpath)) {
                    try {
                        r = new Recipe(txtpathstr);
                    } catch (IOException | JsonException e) {
                        // e.printStackTrace();
                    }
                }
            } else {
                // System.out.println(path1 + " does not exist.");
            }
        }

        return r;

    }


    //TODO: should probably be merged with BuildJsonFile
    // recipeID can be a name or an ID.
    private static String getJsonPath(String filePath, String recipeID){

        String rID = recipeID.replaceAll("\\s+", "_").toLowerCase();

        String jsonFile = "recipe_" + rID + ".json";

        Path path1 = Paths.get(filePath).toAbsolutePath();
        if (Files.exists(path1)) {

            if (Files.isDirectory(path1)) {
                //String nuPath = String.valueOf(path1) + "/" + jsonFile;

                String nuPath = String.valueOf(path1) + File.separator + jsonFile;

                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path)) {
                    System.out.println("A .json file already exists for recipe ID: " +recipeID);
                    return "";
                }

                else {
                    return path.toString();
                }

            } else if (!Files.isDirectory(path1)) {

                //System.out.println("The path given is to a file and not a directory. Searching parent directory...");

                // get directory
                String dir = String.valueOf(Paths.get(filePath).toAbsolutePath().getParent());
                //String nuPath = dir + "/" + jsonFile;
                String nuPath = dir + File.separator + jsonFile;

                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    System.out.println("A .json file already exists for recipe ID: " +recipeID);
                    return "";
                }
                else {
                    return path.toString();
                }
            }
        }

        System.out.println(path1 + " does not exist.");

        //System.out.println("Could not locate .json file for: " +recipeID+" with the given path: " + path1);

        return "";
    }




    //TODO: ENSURE THAT FILEPATH CONTAINS "recipe_folder" !!!
    /**
     * Saves a recipe as a .json file.
     *
     * @param recipe -- recipe to be saved
     * @param filePath -- destination folder
     * @throws IOException
     */
    public static void BuildJsonFile(Recipe recipe, String filePath) throws IOException {

        if(recipe==null){
            System.out.println("null recipe in BuildJsonFile");
            //return;
        }

        String folder = "recipe_folder";

        if (filePath.toLowerCase().contains(folder)){
            //yay!
        } else {
            // String msg = "Please store your recipes in a folder named \"recipe_folder\"...
            // throw new Exception(msg)
        }

        // recipe must not be null

        JsonObject rj = recipe.getRecipeJson();

        // if(recipe.getRecipeID().equals(rj.get("recipeID").toString())){ }

        String rID = (String) rj.get("recipeID");
        String jpath = getJsonPath(filePath,rID);

        if(!jpath.isEmpty()){

            try (FileWriter fileWriter = new FileWriter(jpath)) {
                Jsoner.serialize(rj, fileWriter);
            }

        }
    }


    //TODO: INCOMPLETE METHOD.
    /**
     * Saves a JsonArray using a destination directory and desired filename
     *
     * @param jar
     * @param directory
     * @param fileName
     * @throws IOException
     */
    public static void SaveJsonArray(JsonArray jar, String directory, String fileName) throws IOException {

        boolean overwrite = true;

        String nuPath = directory + File.separator + fileName;
        Path path = Paths.get(nuPath).toAbsolutePath();

        if (directoryExists(directory)) {
            if (Files.exists(path)) {
                System.out.println("A .json file of that name already exists.");
                //TODO: option to overwrite or leave it alone
                // for now, just overwrite.
            }


        } else {
            System.out.println("No such directory found.");

            //TODO: OPTION TO CREATE DIRECTORY
        }


        if (!overwrite) {
            //return
        }



        try (FileWriter fileWriter = new FileWriter(String.valueOf(path))) {
            Jsoner.serialize(jar, fileWriter);
        }

    }



    //TODO: FOR TESTING
    public static JsonArray ReadJsonMenu(String filePath) throws FileNotFoundException, JsonException {

        Path path = Paths.get(filePath).toAbsolutePath(); // TODO: bug risk

        FileReader fileReader = new FileReader(String.valueOf((path)));
        JsonArray menu = (JsonArray) Jsoner.deserialize(fileReader);

        return menu;
    }


    /**
     * Extracts and returns a recipe from a JsonObject.
     * @param jo
     * @return
     */
    public static Recipe recipeFromJsonOb(JsonObject jo) {

        if (jo == null){
            return null;
        }

        JsonObject contents = (JsonObject) jo.get("recipe");
        String recipeName = (String) contents.get("recipeName");
        String recipeID = (String) contents.get("recipeID");
        JsonArray ingjar = (JsonArray) contents.get("ingredient_list");

        if(ingjar==null){
            return null;
        }

        ArrayList<Ingredient> ings = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();


        for (int i = 0; i < ingjar.size(); i++) {
            JsonObject thing = (JsonObject) ingjar.get(i);

            if (thing != null) {

                Ingredient nuIngr = new Ingredient(thing);

                if (names.contains(nuIngr.ingredientName)) {

                    //TODO: implement something that prevents duplicates
                    // maybe this should happen when a recipe is saved instead?

                } else { // if ingredient not in list
                    ings.add(nuIngr);
                    names.add(nuIngr.ingredientName);

                }
            } else {
                System.out.println("null json object");
                return null;
            }
        }

        Recipe r = new Recipe(recipeName, recipeID, ings);

        return r;
    }


    public static Recipe recipeFromJsonMenu(JsonObject jo, String rn) {

        if (jo == null){
            return null;
        }

        JsonObject contents = (JsonObject) jo.get(rn);

        if (contents==null){
            return null;
        }


        String recipeName = (String) contents.get("recipeName");
        String recipeID = (String) contents.get("recipeID");
        JsonArray ingjar = (JsonArray) contents.get("ingredient_list");

        if(ingjar==null){
            return null;
        }

        ArrayList<Ingredient> ings = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();


        for (int i = 0; i < ingjar.size(); i++) {
            JsonObject thing = (JsonObject) ingjar.get(i);

            if (thing != null) {

                Ingredient nuIngr = new Ingredient(thing);

                if (names.contains(nuIngr.ingredientName)) {

                    //TODO: implement something that prevents duplicates
                    // maybe this should happen when a recipe is saved instead?

                } else { // if ingredient not in list
                    ings.add(nuIngr);
                    names.add(nuIngr.ingredientName);

                }
            } else {
                System.out.println("null json object");
                return null;
            }
        }

        Recipe r = new Recipe(recipeName, recipeID, ings);

        return r;
    }



    public static ArrayList<Recipe> RecipesFromMenu(String filePath) throws FileNotFoundException, JsonException {

        JsonArray menu = ReadJsonMenu(filePath);

        ArrayList<Recipe> recipes = new ArrayList<>();

        //ArrayList<JsonObject> jos = new ArrayList<>();

        for (int i =0; i < menu.size(); i++){
            JsonObject recipe = (JsonObject) menu.get(i);

            Recipe r = recipeFromJsonOb(recipe);
            recipes.add(r);

        }
        return recipes;
    }


    /*
Add a recipe to the database
May need to be moved elsewhere
*/
    // TODO
    public void saveNewRecipe(){ }



    /////////////////////////////////////
    // TESTING HAPPENS DOWN HERE //


    public static void main(String[] args) throws Exception {

        //Recipe recipe_from_json = new Recipe("recipe_folder/recipe_0001.json");
        //System.out.println(recipe_from_json.toString());
        //Recipe r2 = new Recipe("recipe_folder/recipe_0002.txt");


        Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");
        Recipe r9000 = new Recipe("recipe_folder/recipe_9000.txt");
        Recipe r8000 = new Recipe("recipe_folder/recipe_8000.txt");

        BuildJsonFile(r1, "recipe_folder");
        //BuildJsonFile(r2, "recipe_folder");
        BuildJsonFile(r9000, "recipe_folder");
        BuildJsonFile(r8000, "recipe_folder");

        //BuildJsonFile(r1, "filetests");
        //BuildJsonFile(r2, "filetests");
        //BuildJsonFile(r9000, "filetests");
        //BuildJsonFile(r8000, "filetests");

        JsonObject j1 = r1.packForMenu();
        JsonObject j9 = r9000.packForMenu();
        JsonObject j8 = r8000.packForMenu();

        JsonArray jar = new JsonArray();

        jar.add(j1);
        jar.add(j9);
        jar.add(j8);

        //SaveJsonArray(jar, "filetests","mock_menu.json");


        SaveJsonArray(jar, "mock_menus","FILE_NAME.json");


        MockDB mdb = new MockDB("mock_dbs/db1.txt");
        mdb.saveAsJson("mock_dbs","db1.json");



        // MockDB mdb = new MockDB("mock_inventory_database/db1.txt");

        //mdb.saveAsJson("filetests","db1.json");

        //MockDB mdb = new MockDB("filetests/db1.json");

        /*HashMap<String,Item> map = mdb.getDb();
        mdb = null;
        MockDB nuDB = new MockDB(map);
        System.out.println(nuDB.getKeys().toString());
        */

        //System.out.print(mdb.toString());

        //SaveJsonArray(jar, "nufolder","lol.json");

       /*

        JsonArray jar = new JsonArray();
        ArrayList<Ingredient> ilist = new ArrayList<>();

        Ingredient t1 = new Ingredient("thing1","9891",8,"lbs");
        Ingredient t2 = new Ingredient("thing2","7777",5,"kgs");
        Ingredient t3 = new Ingredient("thing3","1234",9000,"mL");

        ilist.add(t1);
        ilist.add(t2);
        ilist.add(t3);

        Recipe nuR = new Recipe("idek", "1009",ilist);
        JsonObject n0 = nuR.packForMenu();

        Recipe r = new Recipe("recipe_folder/recipe_0001.txt");
        JsonObject n1 = r.packForMenu();

        Recipe r2 = new Recipe("recipe_folder/recipe_0002.txt");
        JsonObject n2 = r2.packForMenu();

        jar.add(n0);
        jar.add(n1);
        jar.add(n2);

        SaveJsonAr(jar, "filetests/mock_menu.json");

        ArrayList<Recipe> rfm = RecipesFromMenu("filetests/mock_menu.json");


*/

        /*
        ArrayList<Ingredient> ilist = new ArrayList<>();

        Ingredient cheese = new Ingredient("cheese","5534",8,"lbs");
        Ingredient rice = new Ingredient("rice","7881",5,"kgs");
        Ingredient milk = new Ingredient("milk","2003",9000,"mL");
        Ingredient butter = new Ingredient("butter","2001",20,"oz");
        Ingredient sardines = new Ingredient("blended sardines","0019",60,"fl.oz");

        ilist.add(cheese);
        ilist.add(rice);
        ilist.add(milk);
        ilist.add(butter);
        ilist.add(sardines);

        Recipe nuR = new Recipe("idek", "1009",ilist);

        BuildJsonFile(nuR,"recipe_folder");
        */

        // boolean b0 = JsonExists("nuFolder","9190");
        //  boolean b1 = JsonExists("recipe_folder", "0004");
        //  boolean b2 = JsonExists("recipe_folder/","0002");
        //  boolean b3 = JsonExists("recipe_folder/recipe_0001.txt","0001");

    }
}
