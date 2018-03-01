package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.util.UUID;

public class ImageReference {
	private final String name;
	private final File file;
	private final String id;
	
	public ImageReference(
			final String name,
			final File file
	) {
		this.name = name;
		this.file = file;
		this.id = UUID.randomUUID().toString();
	}
	
	public String getName() {
		return name;
	}
	
	public File getFile() {
		return file;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ImageReference)) {
			return false;
		}
		final ImageReference that = (ImageReference) o;
		return this.id.equals(that.id);
	}
}
