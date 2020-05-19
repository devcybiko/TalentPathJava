public class Dog {
    public String bark() {
        return "woof";
    }

    public static void main(String[] args) {
        Dog a = new Dog();
        Sporting b = new Sporting();
        LabradorRetriever c = new LabradorRetriever();
        FlyingDog d = new FlyingDog();
        Bird bird = new Bird();

        makeSomeNoise(a);
        makeSomeNoise(b);
        makeSomeNoise(c);
        flyer(d);
        flyer(bird);
    }

    public static void makeSomeNoise(Dog dog) {
        System.out.println(dog.bark()); // prints "woof"
    }
    public static void flyer(Flying f) {
        System.out.println(f.wings());
    }
}

interface Flying {
    public int wings();
    public boolean takeOff();
    public boolean land();
}

interface DataBase {
    public int create();
    public boolean read();
    public boolean update();
    public boolean delete();
}

class Sporting extends Dog {
    public String bark() {
        return "howl";
    }
}

class LabradorRetriever extends Sporting {
    public String bark() {
        return "high howl";
    }
}

class Bird implements Flying{
    Bird() {
        //
    }
    public void tweet() {
        //
    }

    @Override
    public int wings() {
        System.out.println("I'm a birdy with wings");
        return 0;
    }

    @Override
    public boolean takeOff() {
        System.out.println("I'm a birdy with takeoff");

        return false;
    }

    @Override
    public boolean land() {
        System.out.println("I'm a birdy with landing");

        return false;
    }
}

class FlyingDog extends Dog implements Flying, DataBase {
    public int wings() {
        System.out.println("I have wings");
        return 0;
    }
    public boolean takeOff() {
        System.out.println("I have takeoff");
        return true;
    }
    public boolean land() {
        System.out.println("I have landing");
        return false;
    }

    @Override
    public int create() {
        return 0;
    }

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}