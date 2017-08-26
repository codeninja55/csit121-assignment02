package cn55.view;

import java.util.EventObject;

public class SearchEvent extends EventObject {
    private String searchID;

    public SearchEvent(Object source) {
        super(source);
    }

    public SearchEvent(Object source, String searchID) {
        super(source);
        this.searchID = searchID;
    }

    public String getSearchID() {
        return searchID;
    }

    public void setSearchID(String searchID) {
        this.searchID = searchID;
    }
}
