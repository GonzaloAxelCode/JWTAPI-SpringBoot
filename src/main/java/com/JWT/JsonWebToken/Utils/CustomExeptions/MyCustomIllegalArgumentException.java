package com.JWT.JsonWebToken.Utils.CustomExeptions;

public class MyCustomIllegalArgumentException  extends IllegalArgumentException {
    private Object customArgument;

    public MyCustomIllegalArgumentException(String message, Object customArgument) {
        super(message);
        this.customArgument = customArgument;
    }

    public Object getCustomArgument() {
        return customArgument;
    }
}
