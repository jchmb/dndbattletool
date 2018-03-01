package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

public class ImagePacker {
	private static final String IMAGES_DIRECTORY = "images";
	
	private final File imageDirectory;
	private final Map<File, String> references;
	
	public ImagePacker(final File workingDirectory) {
		this.imageDirectory = new File(workingDirectory, IMAGES_DIRECTORY);
		this.references = new HashMap<>();
	}
	
	public final String getOrCompute(final File file) {
		return this.references.computeIfAbsent(
			file,
			f -> UUID.randomUUID().toString() +
				"." +
				FilenameUtils.getExtension(file.getName())
		);
	}
	
	public final void pack() throws IOException {
		Files.createDirectory(imageDirectory.toPath());
		for (final Map.Entry<File, String> entry : references.entrySet()) {
			final File file = entry.getKey();
			final String uuid = entry.getValue();
			final File newFile = new File(
				imageDirectory,
				uuid
			);
			if (file.equals(newFile)) {
				continue;
			}
			Files.copy(
				file.toPath(),
				newFile.toPath(),
				StandardCopyOption.REPLACE_EXISTING
			);
		}
	}
}
