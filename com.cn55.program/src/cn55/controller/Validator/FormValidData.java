package cn55.controller.Validator;

public class FormValidData {
    private String cardID;
    private String categoryIDStr;
    private String catValueStr;
    private String email;

    /*============================== CONSTRUCTORS ==============================*/
    public FormValidData() {
        this.cardID = null;
        this.categoryIDStr = null;
        this.catValueStr = null;
        this.email = null;
    }

    /*============================== MUTATORS ==============================*/
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setCategoryID(String categoryIDStr) {
        this.categoryIDStr = categoryIDStr;
    }

    public void setCatValueStr(String catValueStr) {
        this.catValueStr = catValueStr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*============================== ACCESSORS ==============================*/
    public String getCardID() {
        return cardID;
    }

    public String getCategoryID() {
        return categoryIDStr;
    }

    public String getCatValueStr() {
        return catValueStr;
    }

    public String getEmail() {
        return email;
    }
}
