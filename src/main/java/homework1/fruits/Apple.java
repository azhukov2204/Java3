package homework1.fruits;

public class Apple extends Fruit{
    private static final float WEIGHT_OF_ONE_APPLE = 1.0f;

    public Apple() {
        super(WEIGHT_OF_ONE_APPLE);
    }

    public Apple(float weightOfFruit) {
        super(weightOfFruit);
    }
}
