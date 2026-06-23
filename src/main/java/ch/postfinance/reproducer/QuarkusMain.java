package ch.postfinance.reproducer;

import io.quarkus.cache.runtime.CacheConfig;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@io.quarkus.runtime.annotations.QuarkusMain
public class QuarkusMain {

    static void main() {
        Quarkus.run();
    }

    @Inject
    CacheConfig config;

    void startup(@Observes StartupEvent startupEvent) {
        // application.yaml has config for test-cache
        // .env should override config but created a new cache config named test.cache
        Log.infof(config.caffeine().cachesConfig().keySet().toString());
        // Expected result: [test-cache]
        // Effective esult: [test.cache, test-cache]
    }
}
