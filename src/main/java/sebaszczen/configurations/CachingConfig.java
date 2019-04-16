package sebaszczen.configurations;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

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
//        net.sf.ehcache.config.BeanFabric config = new net.sf.ehcache.config.BeanFabric();
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