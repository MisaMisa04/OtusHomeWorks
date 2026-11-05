package ru.koshkin.Proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.koshkin.annotations.MagicLogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
@RequiredArgsConstructor
public class MagicLoggerProxy implements InvocationHandler {

    private final Object target;

    @SuppressWarnings("unchecked")
    public static <T> T wrap(T target, Class<T> iface) {
        if (!iface.isInterface())
            throw new IllegalArgumentException(iface.getCanonicalName() + " is not interface");

        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class<?>[]{iface},
                new MagicLoggerProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method implMethod = getImplMethod(method);
        if (implMethod.isAnnotationPresent(MagicLogger.class)) {
            log.info("Called method {} with operandA={}, operandB={}, action={}", implMethod.getName(), args[0], args[1], args[2]);
        }
        return implMethod.invoke(target, args);
    }

    private Method getImplMethod(Method ifaceMethod) {
        try {
            return target.getClass().getMethod(ifaceMethod.getName(), ifaceMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
