package com.linkalma.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class LinkalmaUtil 
{
	private Properties properties;
	public  void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public  String prepareFileUploadPath(String strFile)
	{
		return getProperty(LinkalmaConstants.Properties.FILE_UPLOAD_PATH)+"//"+strFile;
	}
	public String getProperty(LinkalmaConstants.Properties prop)
	{
		return properties.getProperty(prop.toString());
	}
	public  String getAlbumFileUploadPath(String schoolParentDir,String albumName,String strFile) throws IOException
	{	
		
		String path=this.getAlbumUploadPath(schoolParentDir)+"//"+albumName;
		
		this.createAlbum(path);
		return path+"//"+strFile;
	}
	public  String getAlbumUploadPath(String schoolParentDir)
	{	
		String path=getProperty(LinkalmaConstants.Properties.FILE_UPLOAD_PATH)+"//"+
								schoolParentDir+"//"+LinkalmaConstants.Dirs.ALBUM_DIR;
		
		return path;
	}
	public  String getAlbumServePath(String schoolParentDir)
	{	
		String path=getProperty(LinkalmaConstants.Properties.IMAGE_HOST)+"//"+
								schoolParentDir+"//"+LinkalmaConstants.Dirs.ALBUM_DIR;
		
		return path;
	}
	private void createAlbum(String albumPath) throws IOException 
	{
		File file=new File(albumPath);
		this.createDir(file);
	}
	private void createDir(File file) throws IOException 
	{
		if(!file.exists())
		{
			file.mkdirs();
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
		}
	}
	
	public  String getStaffImageFileUploadPath(String schoolParentDir,String strFile) throws IOException
	{	
		
		String path=this.getStaffImageUploadDir(schoolParentDir);
		
		this.createStaffImgDir(path);
		return path+"//"+strFile;
	}
	public  String getStaffImageUploadDir(String schoolParentDir) 
	{	
		
		String path=getProperty(LinkalmaConstants.Properties.FILE_UPLOAD_PATH)+"//"+
												schoolParentDir+"//"+LinkalmaConstants.Dirs.STAFF_DIR;
		
		return path;
	}
	public  String getStaffImageServePath(String schoolParentDir) 
	{	
		
		String path=getProperty(LinkalmaConstants.Properties.IMAGE_HOST)+"//"+
												schoolParentDir+"//"+LinkalmaConstants.Dirs.STAFF_DIR;
		
		return path;
	}
	private void createStaffImgDir(String staffImgDir) throws IOException 
	{
		File file=new File(staffImgDir);
		this.createDir(file);
	}
}
