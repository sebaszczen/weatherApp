package sebaszczen.services;

import java.util.Optional;

public interface ConvertBetweenEntityAndResource {

    <T, E> Optional<T> convert(Optional<E> source, Class<T> target);
}
