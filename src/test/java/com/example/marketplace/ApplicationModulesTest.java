package com.example.marketplace;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModulesTest {

    @Test
    void verifiesModularStructure() {
        ApplicationModules modules = ApplicationModules.of(MarketplaceApplication.class);
        modules.verify();
    }
}
