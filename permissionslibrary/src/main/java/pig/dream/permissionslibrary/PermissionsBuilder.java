package pig.dream.permissionslibrary;

/**
 * Created by kzhu on 2016/11/30.
 */
public class PermissionsBuilder {
    private Action1 successListener;
    private Action1 fauilureListener;

    public static PermissionsBuilder CreatePermissionsBuilder() {
        return new PermissionsBuilder();
    }

    public PermissionsBuilder addSuccessListener(Action1 action1) {
        this.successListener = action1;
        return this;
    }

    public PermissionsBuilder addFauilureListener(Action1 action1) {
        this.fauilureListener = action1;
        return this;
    }

    public void builder() {

    }

    public interface Action1 {
        void call();
    }
}
