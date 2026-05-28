public interface Animatable {
    void update();        // обновление на один тик
    boolean isAnimating(); // нужно ли ещё обновлять
}
