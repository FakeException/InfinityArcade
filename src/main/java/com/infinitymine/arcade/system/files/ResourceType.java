package com.infinitymine.arcade.system.files;

import com.infinitymine.arcade.system.files.yml.Config;
import com.infinitymine.arcade.system.resource.Extension;
import com.infinitymine.arcade.system.resource.ResourceReference;

public enum ResourceType {

    CONFIG("Config.yml", Extension.YML),
    LANGUAGE(Config.LANGUAGE_FILE.getAsString(), Extension.YML);

    private final ResourceReference reference;

    /**
     * @param path      the path resources path and file name, relative to the
     *                  plugins data folder.
     * @param extension the resources file extension.
     */
    ResourceType(String path, Extension extension) {
        this.reference = new ResourceReference(path, extension);
    }

    /**
     * @return the resource's reference.
     */
    public ResourceReference getReference() {
        return reference;
    }
}