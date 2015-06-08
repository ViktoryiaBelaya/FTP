package ftpTask;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class functionFTP {
	private static int choice;
	private static Scanner sc = new Scanner(System.in);
	private static boolean work;
	private  FTPClient client = new FTPClient();
	private String path;
	private String file;
	
	public void menu() throws IOException{
		
		work = true;
		while(work){
		System.out.println("Menu");
		System.out.println("1 - connect to server");
		System.out.println("2 - list files&dirs");
		System.out.println("3 - download file");
		System.out.println("4 - go to parent dir");
		System.out.println("5 - exit");
		choice = sc.nextInt();
		switch(choice){
			case 1:
				FTPConnection();
				break;
				
			case 2:
				getList();
				break;
			case 3:
				System.out.println("Enter full path to put file");
				path = sc.next();
				System.out.println("Enter filename on FTP");
				file = sc.next();
				downloadFileFromFTP(path,file);
				break;
			case 5:
				work = false;
				break;
			default:
				System.out.println("Wrong enter");
                return;
		}
		}
	}

	private FTPClient FTPConnection() throws IOException {
		Scanner br = new Scanner(System.in);
		System.out.println("Enter connect url: ");
		String connect = br.next();
		System.out.println("Enter login: ");
		String login = br.next();
		System.out.println("Enter password: ");
		String password = br.next();
		client.connect(connect);
		client.login(login, password);
		return client;
	}
	
	private void getList() throws IOException {

		FTPFile[] files = client.listFiles("");
		for (FTPFile file : files) {
			System.out.println(file.getName());
		}
				
	}
	
	private void downloadFileFromFTP(String FullPathToPutFile,
			String FilenameOnFTP) throws IOException {
		FileOutputStream downloadfolder = null;
		// The remote filename to be downloaded.
		String filename = FullPathToPutFile;
		downloadfolder = new FileOutputStream(filename);
		// Download file from FTP server
		client.retrieveFile(FilenameOnFTP, downloadfolder);
		if (downloadfolder != null) {
			downloadfolder.close();
		}
	}
	    
	

}
