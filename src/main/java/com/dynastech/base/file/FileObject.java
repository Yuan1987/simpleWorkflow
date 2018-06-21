package com.dynastech.base.file;

import java.io.IOException;
import java.io.InputStream;

public interface FileObject {

	public String getName();
	public String getPath();
	public long getSize();
	public boolean isFile();
	public boolean isDir();
	public InputStream openInputStream() throws IOException;
}
