package com.linkalma.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.linkalma.utils.LinkalmaException;

public interface FileHelper 
{
public void	writeFile(MultipartFile multipartFile ,String strPath) throws FileNotFoundException, IOException,LinkalmaException; 
public void	writeFile(InputStream is ,String strPath) throws FileNotFoundException, IOException,LinkalmaException;
public void	writeFile(List<MultipartFile> multipartFiles,String dir) throws FileNotFoundException, IOException,LinkalmaException;
	
}
