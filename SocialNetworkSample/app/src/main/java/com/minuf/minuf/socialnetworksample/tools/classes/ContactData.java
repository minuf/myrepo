package com.minuf.minuf.socialnetworksample.tools.classes;

/**
 * Created by jorge on 16/09/15.
 */
public class ContactData {
    private int id_profile, id_user, publications, followers, followed, phone_number;
    private String img_prof_URL, name, last_name, website, biography, mail_address, gender;
    private boolean validation_mail;

    //Constructor
    public ContactData(int id_profile, int id_user, int publications, int followers, int followed,
                       int phone_number, String img_prof_URL, String name, String last_name, String website,
                       String biography, String mail_address, String gender, boolean validation_mail) {
        this.id_profile = id_profile;
        this.id_user = id_user;
        this.publications = publications;
        this.followers = followers;
        this.followed = followed;
        this.phone_number = phone_number;
        this.img_prof_URL = img_prof_URL;
        this.name = name;
        this.last_name = last_name;
        this.website = website;
        this.biography = biography;
        this.mail_address = mail_address;
        this.gender = gender;
        this.validation_mail = validation_mail;
    }

    //Getters

    public int getId_profile() {
        return id_profile;
    }

    public int getId_user() {
        return id_user;
    }

    public int getPublications() {
        return publications;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowed() {
        return followed;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public String getImg_prof_URL() {
        return img_prof_URL;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getWebsite() {
        return website;
    }

    public String getBiography() {
        return biography;
    }

    public String getMail_address() {
        return mail_address;
    }

    public String getGender() {
        return gender;
    }

    public boolean isValidation_mail() {
        return validation_mail;
    }

    //Setters

    public void setId_profile(int id_profile) {
        this.id_profile = id_profile;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setPublications(int publications) {
        this.publications = publications;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public void setImg_prof_URL(String img_prof_URL) {
        this.img_prof_URL = img_prof_URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setValidation_mail(boolean validation_mail) {
        this.validation_mail = validation_mail;
    }


}
