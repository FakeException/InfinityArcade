package com.infinitymine.arcade.system.files;

import com.infinitymine.arcade.system.resource.ResourceProvider;
import com.infinitymine.arcade.system.OmegaPlugin;
import com.infinitymine.arcade.system.register.Registrable;
import com.infinitymine.arcade.system.resource.Resource;
import com.infinitymine.arcade.system.resource.ResourceReference;

import java.util.stream.Stream;

public class Resources implements Registrable {

    private static final Resources instance = new Resources();

    public static Resources get() {
        return instance;
    }

    @Override
    public void register() {
        Stream.of(ResourceType.values()).forEach(type -> OmegaPlugin.getResourceProvider().loadResource(type.getReference()));
    }

    /**
     * Gets the resource from the provided resource type.
     * <p>
     * The {@link ResourceProvider} will load and cache the resource if
     * it has not already been done.
     *
     * @param reference the resource's reference.
     * @return the resource.
     */
    private Resource getResource(ResourceReference reference) {
        return OmegaPlugin.getResourceProvider().getResource(reference);
    }

    public Resource getResource(ResourceType type) {
        return getResource(type.getReference());
    }
}