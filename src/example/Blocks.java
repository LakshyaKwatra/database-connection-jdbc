package example;

/*
* 1. When we create an object of class Blocks (Blocks blocks = new Blocks()), the code inside both static and instance blocks gets executed.
* 2. When we do Class.forName("example.Blocks"), then only the code inside the static block gets executed.
* 3. When we do Class.forName("example.Blocks").newInstance(), then a new instance gets created and both the blocks get executed.
* */
public class Blocks {

    static {
        System.out.println("Inside static block.");
    }

    {
        System.out.println("Inside instance block.");
    }
}
