package com.example.ghazanfarali.asani;

/**
 * Created by ghazanfarali on 15/08/2016.
 */
public class Menus {

    private String menu_name, image_name,menu_name2, image_name2;

    public Menus(String image_name, String menu_name,String menu_name2, String image_name2) {
        this.menu_name = menu_name;
        this.image_name = image_name;

        this.menu_name2 = menu_name2;
        this.image_name2 = image_name2;

    }


    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }


    public String getMenu_name2() {
        return menu_name2;
    }

    public void setMenu_name2(String menu_name2) {
        this.menu_name2 = menu_name2;
    }

    public String getImage_name2() {
        return image_name2;
    }

    public void setImage_name2(String image_name2) {
        this.image_name2 = image_name2;
    }
}
