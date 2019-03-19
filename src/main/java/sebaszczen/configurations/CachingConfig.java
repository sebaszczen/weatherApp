package sebaszczen.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CachingConfig
//        implements CachingConfigurer
{

//    @Bean(destroyMethod="shutdown")
//    public net.sf.ehcache.CacheManager ehCacheManager() {
//        CacheConfiguration cacheConfiguration = new CacheConfiguration();
//        cacheConfiguration.setName("myCacheName");
//        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
//        cacheConfiguration.setMaxEntriesLocalHeap(1000);
//
//        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
//        config.addCache(cacheConfiguration);
//
//        return net.sf.ehcache.CacheManager.newInstance(config);
//    }
//
//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(ehCacheManager());
//        ehCacheCacheManager.setTransactionAware( true );
//        return ehCacheCacheManager;
//    }
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return null;
//    }
//
//    @Bean
//    @Override
//    public CacheResolver cacheResolver()    {
//        return null;
//    }
//
//    @Bean
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new SimpleCacheErrorHandler();
//    }
}