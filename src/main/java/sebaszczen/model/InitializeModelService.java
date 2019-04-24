package sebaszczen.model;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sebaszczen.repository.CityRepository;

import java.util.List;

@Service
public class InitializeModelService {

    @Autowired
    private static CityRepository cityRepository;

    @Transactional
    public static  <T>void initializedCollection(T lazy) {
        if (lazy != null) {
            Hibernate.initialize(lazy);
        }
    }
}
