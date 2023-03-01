package Server;

import java.io.Serializable;
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 12345L;
    public String className;
    public String methodName;
    public Class<?>[] parameterTypes;
    public Object[] parameters;

    public RpcRequest() {
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
