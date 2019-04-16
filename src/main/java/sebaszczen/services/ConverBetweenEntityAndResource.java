package sebaszczen.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConverBetweenEntityAndResource implements ConvertBetweenEntityAndResource {


    public <T, E> Optional<T> convert(Optional<E> source, Class<T>target) {
         T result = null;

        try {
           T x = target.newInstance();
            if (source.isPresent()) {
                BeanUtils.copyProperties(source.get(),x);
                System.out.println(x);
            }
            else {
                result = null;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return Optional.of(result);
    }
}
