package cn55.model;

public interface Observer {

    // Method to getCategoriesUpdate the observer, used by the observable object
    void update();

    // Attach with observable object to observer
    void setSubject(Subject subject);
}
