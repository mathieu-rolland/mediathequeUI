package com.perso.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.allocine.model.IMovie;
import com.perso.factory.IMediathequeFactory;
import com.perso.model.ILocalMovie;
import com.perso.model.IMachine;

@Service
public class FTPService {

	private Logger logger = LoggerFactory.getLogger( FTPService.class );
	
	@Autowired
	private IMediathequeFactory factory;
	
	public List<IMovie> listMovieOnFTPServer(IMachine machine){
		
		FTPClient ftpClient = new FTPClient();
		List<IMovie> movies = new ArrayList<IMovie>();
        ftpClient.setAutodetectUTF8(true);
		try {
            ftpClient.connect(machine.getIp(), machine.getPort());
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
            	logger.error("Operation failed. Server reply code: " + replyCode);
                return null;
            }
            boolean success = ftpClient.login(machine.getUser(), machine.getPassword());
            showServerReply(ftpClient);
            if (!success) {
            	logger.error("Could not login to the server");
                return null;
            } else {
            	logger.info("LOGGED IN SERVER " + machine.getName() );
            }
            ftpClient.enterLocalPassiveMode();
            logger.info( "Search file in " + machine.getPath() );
            
            movies = getAllFiles(ftpClient , machine.getPath() );

            ftpClient.logout();
            showServerReply(ftpClient);
            ftpClient.disconnect();
            
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
            return null;
        }
		logger.info("End of FTP process");
		return  movies;
	}

	private List<IMovie> getAllFiles(FTPClient ftpClient, String directory) throws IOException{
		
		ArrayList<IMovie> files = new ArrayList<IMovie>();
		FTPFile[] filesInFTP = ftpClient.listFiles( directory );
		if( filesInFTP != null ){
			for( FTPFile ftpFile : filesInFTP ){
				if ( ftpFile.isDirectory() ){
					logger.debug(ftpFile.getName() + " is a directory");
					files.addAll( getAllFiles(ftpClient, directory + "/" + ftpFile.getName() ) );
				}else{
					logger.debug( "Search info about " + directory + "/" + ftpFile.getName() );
					ILocalMovie movie = factory.createLocalMovie();
					movie.setPath( directory + "/" + ftpFile.getName() );
					movie.setTitle( ftpFile.getName() );
					files.add( movie );
					showServerReply(ftpClient);
				}
			}
		}
		return files;
	}
	
	private void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0){
			for( String str : replies){
				logger.debug("FTP Replay : " + str);
			}
		}
	}
	
	
}
