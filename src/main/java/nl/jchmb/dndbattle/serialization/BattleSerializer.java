package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import nl.jchmb.dndbattle.core.Battle;

public class BattleSerializer {
	private final File workingDirectory;
	private final File file;
	
	public BattleSerializer(final File workingDirectory, final File file) {
		this.workingDirectory = workingDirectory;
		this.file = file;
	}
	
	public final void write(final Battle battle) {
		FileSystems.newFileSystem(
			URI.create();	
		);
	}
}
