package cn55.controller.Validator;

public class FormValidData {
    private String cardID;
    private String catValueStr;
    private String email;

    /*============================== CONSTRUCTORS ==============================*/
    public FormValidData() {
        this.cardID = "";
        this.catValueStr = "";
        this.email = "";
    }

    /*============================== MUTATORS ==============================*/
    public void setCardID(String cardID) {
        this.cardID = cardID;
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

    public String getCatValueStr() {
        return catValueStr;
    }

    public String getEmail() {
        return email;
    }
}
