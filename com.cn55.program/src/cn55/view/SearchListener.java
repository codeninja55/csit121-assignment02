package cn55.view;

import java.util.EventListener;

interface SearchListener extends EventListener {
    void searchEventOccurred(SearchEvent e);
}
