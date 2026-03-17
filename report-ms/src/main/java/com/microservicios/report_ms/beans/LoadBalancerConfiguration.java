package com.microservicios.report_ms.beans;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;

//@Configuration -> No es necesario ya que se va a inyectar de otra manera
@Slf4j
public class LoadBalancerConfiguration {

    @Builder
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        log.info("Configurando load balance");
        return ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient()
                .build(context);
    }
}
