import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class MuddyPuddleException extends Exception{
    public MuddyPuddleException(String message){
        super(message);
    }
}

class Dice{
    Random rand = new Random();

    private final int numFaces;
    private int faceValue;
    private void setFaceValue(int value){
        if (value <= numFaces){
            this.faceValue = value;
        }
    }

    public Dice(int numface) {
        this.numFaces = numface;
        roll();
    }

    public void roll(){
        int currFV = 1 + rand.nextInt(numFaces);
        setFaceValue(currFV);
    }
    public int getFaceValue() {
        return faceValue;
    }
}

class list <T> implements Cloneable{
    private ArrayList <T> lst;
    public list(){
        lst = new ArrayList<T>();
    }
    public void add(T o){
        lst.add(o);
    }
    public T get(int i){
        return lst.get(i);
    }
    public int size(){
        return lst.size();
    }
}

class Tile implements Cloneable{
    private String name;
    private int floor;

    public Tile(String _name, int _floor){
        this.name = _name;
        this.floor = _floor;
    }

    public Tile clone() {
        Tile copy = new Tile(this.name, this.floor);
        return copy;
    }

    public String getToy(){
        return name;
    }
    public int getFloor(){
        return floor;
    }
}

class Player implements Cloneable{
    int counter = 0;
    list <Tile> bucket = new list<Tile>();

    public Player(int _counter){
        this.counter = _counter;
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

class Game implements Cloneable{
    Dice dice;
    Player player;
    list <Tile> tiles = new list<Tile>();

    public Game(Dice _dice, Player _player){
        this.dice = _dice;
        this.player = _player;

        tiles.add(new Tile("pancham", 0));
        tiles.add(new Tile("empoleon", 1));
        tiles.add(new Tile("icenroar", 2));
        tiles.add(new Tile("charizard", 3));
        tiles.add(new Tile("charmander", 4));
        tiles.add(new Tile("lugia", 5));
        tiles.add(new Tile("arceus", 6));
        tiles.add(new Tile("metapod", 7));
        tiles.add(new Tile("garchomp", 8));
        tiles.add(new Tile("infernape", 9));
        tiles.add(new Tile("staraptor", 10));
        tiles.add(new Tile("greninja", 11));
        tiles.add(new Tile("quilava", 12));
        tiles.add(new Tile("pikachu", 13));
        tiles.add(new Tile("oshawott", 14));
        tiles.add(new Tile("blaziken", 15));
        tiles.add(new Tile("decidueye", 16));
        tiles.add(new Tile("mew", 17));
        tiles.add(new Tile("grookey", 18));
        tiles.add(new Tile("giratina", 19));
    }
}

class calculator <T>{
    T a;
    T b;
    public calculator(T _a, T _b){
        this.a = _a;
        this.b = _b;
    }

    public int divide(T _a, T _b){
        return ((int)(_b)/(int)(_a));
    }
    public String concatenate(T _a, T _b){
        return ((String) (_a) + (String) (_b));
    }
}

public class Main {

    static String getAl(int n) {
        String alnum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (alnum.length() * Math.random());
            sb.append(alnum.charAt(index));
        }
        return sb.toString();
    }
    //random string generator courtesy of https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/

    public static void main(String[] args) throws CloneNotSupportedException {
        Player player = new Player(0);
        int flag = 0;
        Dice dice = new Dice(20);
        Game game = new Game(dice, player);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hit enter to initialize the game");
        String input;
        input = scanner.nextLine();
        System.out.println("Game is ready\n" +
                "Hit enter for your first hop");
        //Roll 1
        try{
            dice.roll();
            throw new MuddyPuddleException("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
        }
        catch (MuddyPuddleException e){
            if (dice.getFaceValue() == 20){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");
                flag = 1;
            }
        }

        if (flag != 1){
            System.out.println("You landed on tile " + dice.getFaceValue());
            Tile tile1 = (game.tiles.get(dice.getFaceValue())).clone();
            if (dice.getFaceValue() % 2 == 0) {
                player.bucket.add(tile1);
                System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your second hop\n" + "----------------------------");
            } else {
                System.out.println("Question answer round. Integer or strings?");
                String answer = scanner.nextLine();
                if (answer.equals("integer")) {
                    int multiple = 5;
                    Random random = new Random();
                    int a = (random.nextInt(15) + 2) * multiple;
                    int b = (random.nextInt(a) + 1) * multiple;
                    calculator calc = new calculator(a, b);
                    System.out.println("Calculate the result of " + b + " divided by " + a);
                    System.out.println(calc.divide(a, b));
                    int ans = 0;
                    try {
                        ans = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an Integer.");
                        scanner.nextLine();
                        ans = scanner.nextInt();
                    }
                    if (ans == calc.divide(a, b)) {
                        player.bucket.add(tile1);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your second hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your second hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    }
                } else if (answer.equals("string")) {
                    String a = getAl(5);
                    String b = getAl(5);
                    calculator calc = new calculator(a, b);
                    System.out.println("Calculate the concatenation of strings " + a + " and " + b);
                    System.out.println(calc.concatenate(a, b));
                    String ans = scanner.nextLine();
                    if (ans.equals(calc.concatenate(a, b))) {
                        player.bucket.add(tile1);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your second hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your second hop\n" + "----------------------------");
                    }
                }
            }
            input = scanner.nextLine();
        }

        //Roll 2
        try{
            dice.roll();
            throw new MuddyPuddleException("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
        }
        catch (MuddyPuddleException e){
            if (dice.getFaceValue() == 20){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");
                flag = 2;
            }
        }

        if (flag != 2){
            Tile tile2 = (game.tiles.get(dice.getFaceValue())).clone();
            System.out.println("You landed on tile " + dice.getFaceValue());
            if (dice.getFaceValue() % 2 == 0) {
                player.bucket.add(tile2);
                System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your third hop\n" + "----------------------------");
                input = scanner.nextLine();
            } else {
                System.out.println("Question answer round. Integer or strings?");
                String answer = scanner.nextLine();
                if (answer.equals("integer")) {
                    int multiple = 5;
                    Random random = new Random();
                    int a = (random.nextInt(15) + 2) * multiple;
                    int b = (random.nextInt(a) + 1) * multiple;
                    calculator calc = new calculator(a, b);
                    System.out.println("Calculate the result of " + b + " divided by " + a);
                    System.out.println(calc.divide(a, b));
                    int ans = 0;
                    try {
                        ans = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an Integer.");
                        scanner.nextLine();
                        ans = scanner.nextInt();
                    }
                    if (ans == calc.divide(a, b)) {
                        player.bucket.add(tile2);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your third hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your third hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    }
                } else if (answer.equals("string")) {
                    String x = getAl(5);
                    String y = getAl(5);
                    calculator calc = new calculator(x, y);
                    System.out.println("Calculate the concatenation of strings " + x + " and " + y);
                    System.out.println(calc.concatenate(x, y));
                    String answ = scanner.nextLine();
                    if (answ.equals(calc.concatenate(x, y))) {
                        player.bucket.add(tile2);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your third hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your third hop\n" + "----------------------------");
                    }
                }
            }
        }

        //Roll3
        try{
            dice.roll();
            throw new MuddyPuddleException("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
        }
        catch (MuddyPuddleException e){
            if (dice.getFaceValue() == 20){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");
                flag = 3;
            }
        }

        if (flag != 3){
            Tile tile3 = (game.tiles.get(dice.getFaceValue())).clone();
            System.out.println("You landed on tile " + dice.getFaceValue());
            if (dice.getFaceValue() % 2 == 0) {
                player.bucket.add(tile3);
                System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your fourth hop\n" + "----------------------------");
                input = scanner.nextLine();
            } else {
                System.out.println("Question answer round. Integer or strings?");
                String answer = scanner.nextLine();
                if (answer.equals("integer")) {
                    int multiple = 5;
                    Random random = new Random();
                    int a = (random.nextInt(15) + 2) * multiple;
                    int b = (random.nextInt(a) + 1) * multiple;
                    calculator calc = new calculator(a, b);
                    System.out.println("Calculate the result of " + b + " divided by " + a);
                    System.out.println(calc.divide(a, b));
                    int ans = 0;
                    try {
                        ans = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an Integer.");
                        scanner.nextLine();
                        ans = scanner.nextInt();
                    }
                    if (ans == calc.divide(a, b)) {
                        player.bucket.add(tile3);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your fourth hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your fourth hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    }
                } else if (answer.equals("string")) {
                    String x = getAl(5);
                    String y = getAl(5);
                    calculator calc = new calculator(x, y);
                    System.out.println("Calculate the concatenation of strings " + x + " and " + y);
                    System.out.println(calc.concatenate(x, y));
                    String answ = scanner.nextLine();
                    if (answ.equals(calc.concatenate(x, y))) {
                        player.bucket.add(game.tiles.get(dice.getFaceValue()));
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your fourth hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your fourth hop\n" + "----------------------------");
                    }
                }
            }
        }

        //Roll 4
        try{
            dice.roll();
            throw new MuddyPuddleException("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
        }
        catch (MuddyPuddleException e){
            if (dice.getFaceValue() == 20){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");
                flag = 4;
            }
        }

        if (flag != 4){
            Tile tile4 = (game.tiles.get(dice.getFaceValue())).clone();
            System.out.println("You landed on tile " + dice.getFaceValue());
            if (dice.getFaceValue() % 2 == 0) {
                player.bucket.add(tile4);
                System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your fifth hop\n" + "----------------------------");
                input = scanner.nextLine();
            } else {
                System.out.println("Question answer round. Integer or strings?");
                String answer = scanner.nextLine();
                if (answer.equals("integer")) {
                    int multiple = 5;
                    Random random = new Random();
                    int a = (random.nextInt(15) + 2) * multiple;
                    int b = (random.nextInt(a) + 1) * multiple;
                    calculator calc = new calculator(a, b);
                    System.out.println("Calculate the result of " + b + " divided by " + a);
                    System.out.println(calc.divide(a, b));
                    int ans = 0;
                    try {
                        ans = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an Integer.");
                        scanner.nextLine();
                        ans = scanner.nextInt();
                    }
                    if (ans == calc.divide(a, b)) {
                        player.bucket.add(tile4);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your fifth hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your fifth hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    }
                } else if (answer.equals("string")) {
                    String x = getAl(5);
                    String y = getAl(5);
                    calculator calc = new calculator(x, y);
                    System.out.println("Calculate the concatenation of strings " + x + " and " + y);
                    System.out.println(calc.concatenate(x, y));
                    String answ = scanner.nextLine();
                    if (answ.equals(calc.concatenate(x, y))) {
                        player.bucket.add(tile4);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Hit enter for your fifth hop\n" + "----------------------------");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Hit enter for your fifth hop\n" + "----------------------------");
                    }
                }
            }
        }

        //Roll 5
        try{
            dice.roll();
            throw new MuddyPuddleException("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
        }
        catch (MuddyPuddleException e){
            if (dice.getFaceValue() == 20){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");
                flag = 5;
            }
        }

        if (flag != 5){
            Tile tile5 = (game.tiles.get(dice.getFaceValue())).clone();
            System.out.println("You landed on tile " + dice.getFaceValue());
            if (dice.getFaceValue() % 2 == 0) {
                player.bucket.add(tile5);
                System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Game Over\n" + "Soft toys won by you are:\n");
                for (int i = 0; i < player.bucket.size(); i++) {
                    System.out.print(player.bucket.get(i).getToy() + " ");
                }
            } else {
                System.out.println("Question answer round. Integer or strings?");
                String answer = scanner.nextLine();
                if (answer.equals("integer")) {
                    int multiple = 5;
                    Random random = new Random();
                    int a = (random.nextInt(15) + 2) * multiple;
                    int b = (random.nextInt(a) + 1) * multiple;
                    calculator calc = new calculator(a, b);
                    System.out.print("Calculate the result of " + b + " divided by " + a);
                    System.out.println(calc.divide(a, b));
                    int ans = 0;
                    try {
                        ans = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an Integer.");
                        scanner.nextLine();
                        ans = scanner.nextInt();
                    }
                    if (ans == calc.divide(a, b)) {
                        player.bucket.add(tile5);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Game Over\n" + "Soft toys won by you are:\n");
                        for (int i = 0; i < player.bucket.size(); i++) {
                            System.out.print(player.bucket.get(i).getToy() + " ");
                        }
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Game Over\n" + "Soft toys won by you are:\n");
                        for (int i = 0; i < player.bucket.size(); i++) {
                            System.out.print(player.bucket.get(i).getToy() + " ");
                        }
                    }
                } else if (answer.equals("string")) {
                    String x = getAl(5);
                    String y = getAl(5);
                    calculator calc = new calculator(x, y);
                    System.out.println("Calculate the concatenation of strings " + x + " and " + y);
                    System.out.println(calc.concatenate(x, y));
                    String answ = scanner.nextLine();
                    if (answ.equals(calc.concatenate(x, y))) {
                        player.bucket.add(tile5);
                        System.out.println("You won a " + player.bucket.get(player.bucket.size() - 1).getToy() + " soft toy\n" + "Game Over\n" + "Soft toys won by you are:\n");
                        for (int i = 0; i < player.bucket.size(); i++) {
                            System.out.print(player.bucket.get(i).getToy() + " ");
                        }
                    } else {
                        System.out.println("Incorrect Answer\n" + "You didn't win any soft toy\n" + "Game Over\n" + "Soft toys won by you are:\n");
                        for (int i = 0; i < player.bucket.size(); i++) {
                            System.out.print(player.bucket);
                        }
                    }
                }
            }
        }
        else{
            System.out.println("Game Over\n" + "Soft toys won by you are:\n");
            for (int i = 0; i < player.bucket.size(); i++) {
                System.out.print(player.bucket);
            }
        }
    }
}