import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.reactor.FluxRxInvoker;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Downloader {
    public static void main(String[] args) {
        ResteasyProviderFactory instance= ResteasyProviderFactory.getInstance();
        RegisterBuiltin.register(instance);
        instance.registerProvider(ResteasyJackson2Provider.class);

        Client client = ClientBuilder.newClient();

//        WebTarget target = client.target("https://www.learningcontainer.com/wp-content/uploads/2020/04/sample-text-file.txt");
        WebTarget target = client.target("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-zip-file.zip");


        Flux<DataBuffer> dataBufferFlux = (Flux<DataBuffer>) target
                .request()
                .rx(FluxRxInvoker.class).get(DataBuffer.class);

        Path path = Paths.get("file.txt");

        DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE).block();
    }
}