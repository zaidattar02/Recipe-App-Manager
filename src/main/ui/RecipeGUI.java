package ui;

import model.MyState;
import model.Recipe;
import model.RecipeBook;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.RecipeApp;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;

public class RecipeGUI implements ActionListener {
    private JPanel recipeBookPanel;
    private JPanel recipeFavPanel;
    private JPanel vegPanel;
    private JLabel recipeBookLabel;
    private JLabel recipeFavLabel;
    private JFrame frame = new JFrame();
    private JButton loadButton = new JButton("Load");
    private JButton showFavButton = new JButton("Display Favorites");
    private JButton recToFavButton = new JButton("Add recipes to Favorites");
    private JButton filterVeganButton = new JButton("Filter Vegan Recipes");
    private JButton saveButton = new JButton("Save");
    private RecipeBook recipeBook = new RecipeBook();
    private DefaultListModel listModel;
    private DefaultListModel listModel2;

    private JList recipesList;
    private JList veganRecipes = new JList<>();
    private JList fav = new JList<>();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/MyState.json";
//    private JList favList;
//    private ArrayList<Recipe> favRec;
//    private MyState myState;
    Border border = BorderFactory.createLineBorder(Color.black, 3);

    public RecipeGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        initializePanels();
        initializeButtons();
        frame.setVisible(true);
//        addRecToFav();
        recipeBookPanel.add(recipeBookLabel);
        vegPanel.add(veganRecipes);
        recipeFavPanel.add(fav);
        recipeBookPanel.add(getRecipesJList());
        //recipeFavPanel.add(getFavJList());
        frame.setTitle("RecipeBook");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750, 750);
        frame.setResizable(true);
        frame.add(recipeBookPanel);
        frame.add(vegPanel);
        frame.add(recipeFavPanel);
        frame.add(loadButton);
        frame.add(showFavButton);
        frame.add(filterVeganButton);
        frame.add(saveButton);
        //frame.pack(); //Adjusts the size of the frame to accommodate all components in it
    }

    private void initializePanels() {
        recipeBookPanel = new JPanel();
        recipeBookPanel.setBackground(Color.LIGHT_GRAY);
        recipeBookPanel.setBounds(0, 0, 300, 300);
        recipeBookPanel.setBorder(border);
        recipeBookLabel.setText("Welcome to your Recipe Book");

        recipeFavPanel = new JPanel();
        recipeFavPanel.setBackground(Color.LIGHT_GRAY);
        recipeFavPanel.setBounds(445, 0, 300, 300);
        recipeFavPanel.setBorder(border);
        recipeFavLabel = new JLabel();
        recipeFavLabel.setText("Here are your favorite recipes");


        vegPanel = new JPanel();
        vegPanel.setBackground(Color.LIGHT_GRAY);
        vegPanel.setBounds(0, 300, 300, 300);
        vegPanel.setBorder(border);
    }

    private void initializeButtons() {
        loadButton.setBounds(0, 640, 100, 50);
        loadButton.setVisible(true);
        loadButton.setLayout(null);
        loadButton.setSize(105, 50);

        showFavButton.setBounds(125, 640, 100, 50);
        showFavButton.setVisible(true);
        showFavButton.setLayout(null);
        showFavButton.setSize(150, 50);
        showFavButton.addActionListener(this);

        recToFavButton.setBounds(275, 640, 100, 50);
        recToFavButton.setVisible(true);
        recToFavButton.setLayout(null);
        recToFavButton.setSize(175, 50);

        filterVeganButton.setBounds(450, 640, 100, 50);
        filterVeganButton.setVisible(true);
        filterVeganButton.setLayout(null);
        filterVeganButton.setSize(175, 50);
        filterVeganButton.addActionListener(this);


        saveButton.setBounds(640, 640, 100, 50);
        saveButton.setVisible(true);
        saveButton.setLayout(null);
        saveButton.setSize(105, 50);
    }

    private JList getRecipesJList() {
        recipeBook = new RecipeBook();
        ArrayList<Recipe> arrayList = recipeBook.getRecipes();
        listModel = new DefaultListModel<>();

        for (Recipe recipe : arrayList) {
//            JLabel recipeName = new JLabel(n);
//            recipeName.addMouseListener(new MouseAdapter() {
//                public void mouseClicked(MouseEvent me) {
//                    System.out.println(recipeName.getName());
//                }
//            });

            listModel.addElement(recipe.getRecipeName() + recipe.isFavourite());
        }
        recipesList = new JList<>(listModel);
        recipesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                recipeBook.addToFav(recipesList.getSelectedIndex() + 1);
                Recipe r = arrayList.get(recipesList.getSelectedIndex());
               filterForFav();
            }
        });
        recipesList.updateUI();
        return recipesList;
    }

//    public MyState getMyState() {
//        return myState;
//    }

//    private JList getFavJList() {
//        myState = new MyState("Zaid's state");
//        favRec = new ArrayList<>();
//        favRec = myState.getFav();
//        listModel2 = new DefaultListModel<>();
//        for (Recipe r : favRec) {
//            listModel2.addElement(r);
//        }
//        favList = new JList<>(listModel2);
//        return favList;
//    }

    private void addRecToFav() {
        //get user input from dialog box
//        Recipe recipe = recipeBook.getRecipeByNum(1);
//        myState.addFavorites(recipe);
        //myState.getFav();
    }

    private JList filterForVegan() {
        ArrayList<Recipe> recipeArrayList = recipeBook.getRecipes();
        //recipeArrayList = recipeBook.getRecipes();
        listModel2 = new DefaultListModel<>();

        for (Recipe r : recipeArrayList) {
            if (r.isRecipeVegan()) {
                listModel2.addElement(r.getRecipeName());
            }
        }

        veganRecipes.setModel(listModel2);
        return veganRecipes;
    }

    private JList filterForFav() {
        ArrayList<Recipe> recipeArrayList = recipeBook.getRecipes();
        //recipeArrayList = recipeBook.getRecipes();
        listModel2 = new DefaultListModel<>();

        for (Recipe r : recipeArrayList) {
            if (r.isFavourite()) {
                listModel2.addElement(r.getRecipeName());
            }
        }

        fav.setModel(listModel2);
        return fav;
    }

    //ToDO add method for filterforVeganinFav

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterVeganButton) {
            vegPanel.add(filterForVegan());
            //filterForVegan().setVisible(true);
        } else if (e.getSource() == recToFavButton) {
            addRecToFav();
        }
    }
}