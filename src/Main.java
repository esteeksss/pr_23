import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String groupName,int posts);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class Group implements Subject {
    private String name;
    private int posts=10;
    private List<Observer> observers = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public void post(){
        this.posts+=1;
        System.out.println("Новый пост");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(name, posts);
        }
    }

}

class User implements Observer {
    private String name;
    private int posts;
    private Subject group;


    public User(Subject group) {
        group.registerObserver(this);
    }

    @Override
    public void update(String groupName,int posts) {
        this.name=name;
        this.posts=posts;
    }

    public int getPosts() {
        return posts;
    }
}

class Main {
    public static void main(String[] args) {
       Group gp=new Group("Паблик");
       User user=new User(gp);
       gp.post();
        System.out.println(user.getPosts());
    }
}
