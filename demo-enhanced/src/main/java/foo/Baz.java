package foo;

public class Baz {
    private Bar bar = new Bar();

    public String getQuux() {
        return bar.getQux();
    }

    public void setQuux(String quux) {
        bar.setQux(quux);
    }
}
